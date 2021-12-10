package springboot_kurs_controller_service_repository_form_based_authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBean04Controller {
	
	private StudentBean04Service studentBean04Service;

	@Autowired
	public StudentBean04Controller(StudentBean04Service studentBean04Service) {
		this.studentBean04Service = studentBean04Service;
	}
	
	@GetMapping(path = "/selectStudentById/{id}")
	public StudentBean04 selectStudentById(@PathVariable Long id) {
		return studentBean04Service.selectStudentById(id);
	}
	
	@GetMapping(path = "/selectAllStudents")
	public List<StudentBean04> selectAllStudents(){
		return studentBean04Service.selectAllStudents();
	}

}
