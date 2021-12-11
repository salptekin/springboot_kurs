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


    //Bu metod ogrenciyi id'si ile secer
	public StudentBean05 selectStudentById(Long id) {		
		if(studentRepo.findById(id).isPresent()) {
			return studentRepo.findById(id).get();
		}		
		return new StudentBean05();		
	}
	
	//bu metod tum ogrencileri dondurur
	public List<StudentBean05> selectAllStudents(){		
		return studentRepo.findAll();		
	}
}
