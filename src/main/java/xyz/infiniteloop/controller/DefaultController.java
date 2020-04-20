package xyz.infiniteloop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import xyz.infiniteloop.HelloWorldServiceConfiguration;
import xyz.infiniteloop.model.Hello;

/**
 * @author Sava Simic (sava.simic@gmail.com)
 */
public class DefaultController {
    protected static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    protected HelloWorldServiceConfiguration configuration;

    protected Hello createResponse(AtomicLong counter, String content) {
        return new Hello(counter.incrementAndGet(), content);
    }

    protected String getRoles(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
    }
}
