package pe.edu.ltmj.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pe.edu.ltmj")
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
