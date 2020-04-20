package xyz.infiniteloop.model;

import java.util.List;

import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * @author Sava Simic (sava.simic@gmail.com)
 */
@ConstructorBinding
public record Authentication(
        List<User> users
) {
}
