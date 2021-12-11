package springboot_kurs_controller_service_repository_basic_authentication;

import java.util.List;

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
		
		return studentRepo.save(existingStudentById);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
