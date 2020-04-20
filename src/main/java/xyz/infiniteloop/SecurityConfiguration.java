package xyz.infiniteloop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author Sava Simic (sava.simic@gmail.com)
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    private static final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    private HelloWorldServiceConfiguration configuration;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().cacheControl();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/hello").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        logger.debug("Importing {} users:", configuration.authentication().users().size());

        configuration.authentication().users().forEach(user -> {
            userDetailsManager.createUser(User.builder()
                    .passwordEncoder(encoder::encode)
                    .username(user.username())
                    .password(user.password())
                    .roles(user.roles())
                    .build());
            logger.debug("Imported user {}", user.username());
        });
        return userDetailsManager;
    }
}
