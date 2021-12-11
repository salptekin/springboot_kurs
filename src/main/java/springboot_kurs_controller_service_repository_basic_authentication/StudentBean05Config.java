package springboot_kurs_controller_service_repository_basic_authentication;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentBean05Config {
	
	@Bean
	CommandLineRunner commandLineRunner1(StudentBean05Repository studentRepo) {
		
		return args -> {
			
			StudentBean05 aliCan = new StudentBean05(101L, "Ali Can", "ac@gmail.com", LocalDate.of(2002, Month.JANUARY, 21));
			StudentBean05 veliHan = new StudentBean05(102L, "Veli Han", "vh@gmail.com", LocalDate.of(2000, Month.MAY, 2));
			StudentBean05 maryStar = new StudentBean05(103L, "Mary Star", "ms@gmail.com", LocalDate.of(2001, Month.FEBRUARY, 12));
			
			studentRepo.saveAll(List.of(aliCan, veliHan, maryStar));
		};
		
	}

}
