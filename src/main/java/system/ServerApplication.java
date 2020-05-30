package system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import system.util.ThreadPoolExecutorUtil;

@PropertySource(value={"file:config/application.yml"},encoding = "utf-8")
@EnableTransactionManagement
@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ServerApplication.class, args);
		ThreadPoolExecutorUtil.getInstance();
	}

}
