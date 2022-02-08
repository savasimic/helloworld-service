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
@RequestMapping("admin")
public final class AdminController extends DefaultController {

    private final AtomicLong adminCounter = new AtomicLong();

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Hello admin(Authentication authentication) {
        final String value = String.format(configuration.adminTemplate(), authentication.getName(), getRoles(authentication.getAuthorities()));
        logger.debug("adminMessage({})", value);
        return createResponse(adminCounter, value);
    }
}
