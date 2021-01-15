package grocer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class GrocerApplication {
	
    
    public static void main(String[] args) {
        SpringApplication.run(GrocerApplication.class,args);
    }
}
