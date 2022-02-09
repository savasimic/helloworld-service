package xyz.infiniteloop.controller;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import xyz.infiniteloop.model.Hello;

/**
 * @author Sava Simic (sava.simic@gmail.com)
 */
@RestController
public final class HelloWorldController extends DefaultController {

    private final AtomicLong helloCounter = new AtomicLong();

    @GetMapping("/hello")
    public Hello hello(@RequestParam("name") Optional<String> maybeName) {
        meterRegistry.counter(
                "hello_endpoint_call_counter",
                List.of(Tag.of("name", maybeName.orElse(configuration.defaultName()))))
                .increment();
        final String value = String.format(configuration.template(), maybeName.orElse(configuration.defaultName()), configuration.email());
        logger.debug("sayHello({})", value);
        return createResponse(helloCounter, value);
    }
}
