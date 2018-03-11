package io.letitroll.be.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
public class EmitterController {

    @GetMapping("/emitter")
    public SseEmitter getSseEmitter() throws IOException {
        final SseEmitter emitter = new SseEmitter();
        emitter.send(SseEmitter.event().data("Message #1"));
        return emitter;
    }
}
