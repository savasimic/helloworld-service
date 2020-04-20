package xyz.infiniteloop.model;

import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * @author Sava Simic (sava.simic@gmail.com)
 */
@ConstructorBinding
public record User(
        String username,
        String password,
        String[] roles) {
}
