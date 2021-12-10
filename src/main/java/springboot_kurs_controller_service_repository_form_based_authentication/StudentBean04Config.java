package springboot_kurs_controller_service_repository_form_based_authentication;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentBean04Config {
	
	@Bean
	CommandLineRunner commandLineRunner1(StudentBean04Repository studentRepo) {
		
		return args -> {
			
			StudentBean04 aliCan = new StudentBean04(101L, "Ali Can", "ac@gmail.com", LocalDate.of(2002, Month.JANUARY, 21));
			StudentBean04 veliHan = new StudentBean04(102L, "Veli Han", "vh@gmail.com", LocalDate.of(2000, Month.MAY, 2));
			StudentBean04 maryStar = new StudentBean04(103L, "Mary Star", "ms@gmail.com", LocalDate.of(2001, Month.FEBRUARY, 12));
			
			studentRepo.saveAll(List.of(aliCan, veliHan, maryStar));
		};
		
	}

}
