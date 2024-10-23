package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = {"services"})
public class ArkadFinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArkadFinanceApplication.class, args);
	}

}
