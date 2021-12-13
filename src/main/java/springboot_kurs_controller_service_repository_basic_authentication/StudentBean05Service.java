package springboot_kurs_controller_service_repository_basic_authentication;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentBean05Service {
	
	private StudentBean05Repository studentRepo;
	
	@Autowired
	public StudentBean05Service(StudentBean05Repository studentRepo) {
		this.studentRepo = studentRepo;
	}

    //Bu get metod ogrenciyi id'si ile secer
	public StudentBean05 selectStudentById(Long id) {		
		if(studentRepo.findById(id).isPresent()) {
			return studentRepo.findById(id).get();
		}		
		return new StudentBean05();		
	}
	
	//Bu get metod tum ogrencileri dondurur
	public List<StudentBean05> selectAllStudents(){		
		return studentRepo.findAll();		
	}
	
							//Bu metod ogrencileri id'si ile silecek
	public String deleteStudentById(Long id) {		
		if(!studentRepo.existsById(id)) {
			throw new IllegalStateException("Id'si " + id + " olan ogrenci yok...");
		}		
		studentRepo.deleteById(id);		
		return "Id'si " + id + " olan ogrenci silindi...";		
	}
	
							//Bu metod varolan ogrencinin tum(PUT) bilgilerini degistirecek
	public StudentBean05 updateStudentFully(Long id, StudentBean05 newStudent) {
		
		StudentBean05 existingStudentById = studentRepo.
				                                 findById(id).
				                                 orElseThrow(()->new IllegalStateException("Id'si " + id + " olan ogrenci yok..."));
		//Student name degistirilecek
		if(newStudent.getName()==null) {
			existingStudentById.setName(null);
		}else if(existingStudentById.getName()==null) {
			existingStudentById.setName(newStudent.getName());
		}else if(!existingStudentById.getName().equals(newStudent.getName())) {
			existingStudentById.setName(newStudent.getName());
		}
		
		//Student email degistirilecek 
		//1)email tekrarli olamaz ==> Exception
		//2)email olmadan update olamaz ==> Exception
		//3)email gecerli formatta olmali (@ icermeli) ==> Exception
		//4)Yeni email eski email ile ayni ise update etmekle ugrasma, yani yeni email eskisinden farkli ise update et
		Optional<StudentBean05> existingStudentByEmail = studentRepo.findStudentBean05ByEmail(newStudent.getEmail());
		
		if(existingStudentByEmail.isPresent()) {
			throw new IllegalStateException(newStudent.getEmail() + " alindi, baska email seciniz...");
		}else if(newStudent.getEmail()==null) {
			throw new IllegalStateException("email olmadan update yapilamaz...");
		}else if(!newStudent.getEmail().contains("@")) {
			throw new IllegalStateException("email gecerli formatta olmali...");
		}else if(!newStudent.getEmail().equals(existingStudentById.getEmail())) {
			existingStudentById.setEmail(newStudent.getEmail());
		}

		//Student dob degistirilecek
		//1)Dogum tarihi gecmis tarih olmali ==> Exception
		//2)Yeni dogum tarihi eski dogum tarihi ile ayni ise update etme
		if(Period.between(newStudent.getDob(), LocalDate.now()).isNegative()) {
			throw new IllegalStateException(newStudent.getDob() + " dogum tarihi olamaz cunku gelecege ait bir tarih...");
		}else if(!newStudent.getDob().equals(existingStudentById.getDob())) {
			existingStudentById.setDob(newStudent.getDob());
		}
		
		//Varolan ogrencini yasini yeni ogrencinin yasi ile update ediniz
		existingStudentById.setAge(newStudent.getAge());
		
		//Error message degistirilecek
		existingStudentById.setErrMsg("Update basarili bir sekilde yapildi...");
		
		return studentRepo.save(existingStudentById);		
	}
	
						//Ogrenciyi kismi olarak update et(PATCH)
	public StudentBean05 updateStudentPartially(Long id, StudentBean05 newStudent) {
		
		StudentBean05 existingStudentById = studentRepo.
													findById(id).
													orElseThrow(()-> new IllegalStateException("Id'si " + id + " olan ogrenci yok..."));
		
		//PATCH'de kullanici bir kisim ile ilgili data yollamazsa, o kisma dokunulmaz.
		//Bu yuzden tum update'lerimizi null olmayan kisimlar icin yapariz.
		if(newStudent.getName()!=null) {
			existingStudentById.setName(newStudent.getName());
		}
		
		//Student email degistirilecek 
		//1)email tekrarli olamaz ==> Exception
		//2)email gecerli formatta olmali (@ icermeli) ==> Exception		
		if(newStudent.getEmail()==null) {
			newStudent.setEmail("");
		}
	
		Optional<StudentBean05> existingStudentByEmail = studentRepo.findStudentBean05ByEmail(newStudent.getEmail());		
		if(existingStudentByEmail.isPresent()) {
			throw new IllegalStateException(newStudent.getEmail() + " alindi, baska email seciniz...");
		}else if(!newStudent.getEmail().contains("@") && newStudent.getEmail()!="") {
			throw new IllegalStateException("email gecerli formatta olmali...");
		}else if(newStudent.getEmail()!="") {
			existingStudentById.setEmail(newStudent.getEmail());
		}
		
		if(newStudent.getDob()!=null) {
			existingStudentById.setDob(newStudent.getDob());
		}
		
		//Varolan ogrencinin yasini yeni ogrencinin yasi ile update ediniz
		existingStudentById.setAge(newStudent.getAge());
		
		//Error message degistirilecek
		existingStudentById.setErrMsg("Update basarili bir sekilde yapildi...");
		
		return studentRepo.save(existingStudentById);
	}

								//Datatbase'de yeni ogrenci olusturmak(POST)
	public StudentBean05 addNewStudent(StudentBean05 newStudent) throws ClassNotFoundException, SQLException {
		
		//email tekrarsiz olmali
		Optional<StudentBean05> existingStudentByEmail = studentRepo.findStudentBean05ByEmail(newStudent.getEmail());
		
		if(existingStudentByEmail.isPresent()) {
			throw new IllegalStateException("Varolan email kullanilamaz, yeni bir email seciniz...");
		}
		
		//Yeni ogrenci olusturulurken isim mutlaka girilmeli
		if(newStudent.getName()==null) {
			throw new IllegalStateException("Isim girilmeden yeni ogrenci database'e kaydedilemez...");
		}
		
		//Unique Id olusturulmali her yeni ogrenci icin...
		//Logic: Database'de varolan maximum Id'yi al ve yeni ogrenci Id'sini maximum Id'nin bir fazlasi yap.
		
		//Database'e JDBC ile baglan
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");		
		Statement st = con.createStatement();
		
		//Maximum Id'yi almak icin SQL Query yazalim
		String sqlQueryForMaxId = "SELECT MAX(id) FROM students";
		ResultSet result = st.executeQuery(sqlQueryForMaxId);
		
		Long maxId = 0L;
		
		while(result.next()) {
			maxId = result.getLong(1);
		}
		//Yeni ogrencinin Id'si maximum Id'nin bir fazlasi oldu
		newStudent.setId(maxId+1);
		newStudent.setAge(newStudent.getAge());
		newStudent.setErrMsg("Yeni ogrenci kaydi basarili bir sekilde olusturuldu...");
	
		return studentRepo.save(newStudent);

	}

}
