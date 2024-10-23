package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"app", "services", "repositories", "impls"})
@EnableJpaRepositories("repositories")
@EntityScan({"user", "finance"})
public class ArkadFinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArkadFinanceApplication.class, args);
	}

}
