package xyz.infiniteloop.controller;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import xyz.infiniteloop.model.Hello;

/**
 * @author Sava Simic (sava.simic@gmail.com)
 */
@RestController
@RequestMapping("user")
public class UserController extends DefaultController {

    private final AtomicLong userCounter = new AtomicLong();

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Hello user(Authentication authentication) {
        final String value = String.format(configuration.userTemplate(), authentication.getName(), getRoles(authentication.getAuthorities()));
        logger.debug("userMessage({})", value);
        return createResponse(userCounter, value);
    }
}
