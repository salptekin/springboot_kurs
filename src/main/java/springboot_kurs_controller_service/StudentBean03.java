package springboot_kurs_controller_service;

import org.springframework.stereotype.Component;

@Component
public class StudentBean03{

	private String name;
	private int age;
	private String id;

	public StudentBean03() {
		System.out.println("Constructor without parameter is used");
	}

	public StudentBean03(String name, int age, String id) {
		this.name = name;
		this.age = age;
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "StudentBean03: name=" + name + ", age=" + age + ", id=" + id;
	}
}
