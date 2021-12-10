package springboot_kurs_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller //Bu annotation'i koyunca SpringBoot bu class'in Controller Class oldugunu anlar
@RestController
public class StudentBean01Controller {
	
//	@RequestMapping(method=RequestMethod.GET, path="/getRequest")
//	@ResponseBody//Method'un return ettigi data'yi gormek icin bu annotation kullanilir.
//	public String getMethod1() {
//		return "Get Request Method1 calistirildi...";
//	}
	
	@GetMapping(path="/getString")
	public String getMethod1() {
		return "Get Request Method1 calistirildi...";
	}
	
	//Tight Coupling
	@GetMapping(path="/getObjectTight")
	public StudentBean01 getMethod2() {		
		return new StudentBean01("Ali Can", 13, "AC202113");		
	}

	//Loose Coupling
	@Autowired//@Autowired annotation'i IOC Container'dan objeyi alir ve
	          // "s" container'inin icine koyar. StudentBean03 s = new StudentBean03() deki "=" operatorune benzer
	StudentBean01 s;
	
	@GetMapping(path="/getObjectLoose")
	public StudentBean01 getMethod3() {		
		s.setName("Ali Can");
		s.setAge(13);
		s.setId("AC202113");		
		return s;
	}
	
	//Tight Coupling
	@GetMapping(path="/getObjectParametreli1/{school}")
	public StudentBean01 getMethod4(@PathVariable String school) {			
		return new StudentBean01("Veli Han", 35, String.format("VH2021%s35", school));		
	}

	//Loose Coupling
	@GetMapping(path="/getObjectParametreli2/{school}")
	public StudentBean01 getMethod5(@PathVariable String school) {		
		s.setName("Veli Han");
		s.setAge(35);
		s.setId(String.format("VH2021%s35", school));		
		return s;		
	}
	
	//Tight Coupling
	@GetMapping(path="/getObjectList")
	public List<StudentBean01> getMethod6(){
		
		return List.of(
						new StudentBean01("Veli Han", 35, "VH202135"),
						new StudentBean01("Ayse Kan", 23, "AK202123"),
						new StudentBean01("Talha Tan", 46, "TT202146")
					  );
	}
	
	
	//Loose Coupling
	@Autowired
	StudentBean02 t;//Class ismini yazarak IOC Container'dan istediginiz objeyi alabilirsiniz
		
	@GetMapping(path="/getStudy1")
	public String getMethod7() {
		return t.study();
	}
	
	@Autowired
	@Qualifier(value="studentBean02")//@Autowired normalde data type'a bakar ama @Qualifier(value="studentBean02")
	                                 //yazarak isme bakmasini saglayabilirsiniz.
	StudentInterface u;
		
	@GetMapping(path="/getStudy2")
	public String getMethod8() {
		return u.study();
	}

}
