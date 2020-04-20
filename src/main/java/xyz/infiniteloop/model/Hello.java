package xyz.infiniteloop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Sava Simic (sava.simic@gmail.com)
 */
public record Hello(
        @JsonProperty long id,
        @JsonProperty String content
) {
    public Hello() {
        this(0, "");
    }
}
