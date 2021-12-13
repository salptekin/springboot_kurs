package springboot_kurs_controller_service_repository_basic_authentication;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBean05Controller {
	
	private StudentBean05Service studentBean05Service;

	@Autowired
	public StudentBean05Controller(StudentBean05Service studentBean05Service) {
		this.studentBean05Service = studentBean05Service;
	}
	
	@GetMapping(path = "/selectStudentById/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')") 
	public StudentBean05 selectStudentById(@PathVariable Long id) {
		return studentBean05Service.selectStudentById(id);
	}
	
	@GetMapping(path = "/selectAllStudents")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')") 
	public List<StudentBean05> selectAllStudents(){
		return studentBean05Service.selectAllStudents();
	}
	
	@DeleteMapping(path="/deleteById/{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public String deleteStudentById(@PathVariable Long id) {		
		return studentBean05Service.deleteStudentById(id);		
	}
	
	@PutMapping(path="/updateFullyById/{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public StudentBean05 updateFullyById(@PathVariable Long id, @Validated @RequestBody StudentBean05 newStudent) {		
		return studentBean05Service.updateStudentFully(id, newStudent);		
	}
	
	@PatchMapping(path="/updatePartiallyById/{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public StudentBean05 updatePartiallyById(@PathVariable Long id, @Validated @RequestBody StudentBean05 newStudent) {		
		return studentBean05Service.updateStudentPartially(id, newStudent);		
	}
	
	@PostMapping(path="/addNewStudent")
	@PreAuthorize("hasAuthority('student:write')")
	public StudentBean05 addNewStudent(@Validated @RequestBody StudentBean05 newStudent) throws ClassNotFoundException, SQLException {		
		return studentBean05Service.addNewStudent(newStudent);	
	}

}
