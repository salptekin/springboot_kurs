package springboot_kurs_controller_service_repository_form_based_authentication;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class StudentBean04 {
	
	@Id
	private Long id;
	private String name;
	private String email;
	private LocalDate dob;
	private Integer age;
	private String errMsg;
	
	public StudentBean04() {
		this.errMsg = "There is no any student like that...";
		this.age = getAge();
	}

	public StudentBean04(Long id, String name, String email, LocalDate dob) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.age = getAge();
		this.errMsg = "No error...";
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public Integer getAge() {
		if(this.dob==null) {
			return null;
		}
		return Period.between(this.dob, LocalDate.now()).getYears();
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		return "StudentBean05 [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", age=" + age
				+ ", errMsg=" + errMsg + "]";
	}

}
