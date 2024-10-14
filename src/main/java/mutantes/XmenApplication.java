package mutantes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XmenApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmenApplication.class, args);
		System.out.println("DETECTOR DE MUTANTES ACTIVADO");
	}

}
