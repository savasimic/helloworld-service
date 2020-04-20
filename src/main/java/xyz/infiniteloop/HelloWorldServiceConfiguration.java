package xyz.infiniteloop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import xyz.infiniteloop.model.Authentication;

/**
 * @author Sava Simic (sava.simic@gmail.com)
 */
@ConstructorBinding
@ConfigurationProperties
public record HelloWorldServiceConfiguration(
        String email,
        String template,
        String defaultName,
        String userTemplate,
        String adminTemplate,
        Authentication authentication
) {
}
