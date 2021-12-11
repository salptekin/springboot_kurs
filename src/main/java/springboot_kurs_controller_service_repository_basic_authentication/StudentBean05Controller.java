package springboot_kurs_controller_service_repository_basic_authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBean05Controller {
	
	private StudentBean05Service studentBean04Service;

	@Autowired
	public StudentBean05Controller(StudentBean05Service studentBean04Service) {
		this.studentBean04Service = studentBean04Service;
	}
	
	@GetMapping(path = "/selectStudentById/{id}")
	public StudentBean05 selectStudentById(@PathVariable Long id) {
		return studentBean04Service.selectStudentById(id);
	}
	
	@GetMapping(path = "/selectAllStudents")
	public List<StudentBean05> selectAllStudents(){
		return studentBean04Service.selectAllStudents();
	}

}
