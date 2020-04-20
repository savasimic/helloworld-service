package xyz.infiniteloop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author Sava Simic (sava.simic@gmail.com)
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(HelloWorldServiceConfiguration.class)
public class HelloWorldServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldServiceApplication.class, args);
    }
}
