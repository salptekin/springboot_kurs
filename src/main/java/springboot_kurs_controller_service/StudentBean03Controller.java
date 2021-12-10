package springboot_kurs_controller_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBean03Controller {
	
	private StudentBean03Service std;

	@Autowired
	public StudentBean03Controller(StudentBean03Service std) {
		this.std = std;
	}

	@GetMapping(path="/getStudentById/{id}")
	public StudentBean03 getStudentById(@PathVariable Long id) {
		return std.getStudentById(id);
	}
	
	@GetMapping(path="/getAllStudents")
	public List<StudentBean03> getAllStudents(){
		
		return std.getAllStudents();
		
	}

}
