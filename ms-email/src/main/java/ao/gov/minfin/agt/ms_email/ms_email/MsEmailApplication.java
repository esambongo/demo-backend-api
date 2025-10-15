package ao.gov.minfin.agt.ms_email.ms_email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEmailApplication.class, args);
		System.err.println("RODANDO O SERVIÇO E-MAIL...");
	}

}
