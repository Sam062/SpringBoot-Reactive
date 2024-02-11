package base.problemSolution;

import base.model.DemoUser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class Controller {
    public static void sleepThread(int input) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /*
      This API takes N seconds to respond and blocks the request till then
      By Using this approach, performance is reduced
     */
    @GetMapping("/problemUsers")
    public List<DemoUser> getALlUsers() {
        return IntStream.rangeClosed(1, 50)
                .peek(Controller::sleepThread)
                .peek(input -> System.out.println("Processing : " + input))
                .mapToObj(index -> new DemoUser(index, "User-" + index))
                .collect(Collectors.toList());
    }

    /*
    This API doesn't take N seconds to respond or blocks the request meanwhile it responds immediately.
    Its fast and good at performance
    */
    @GetMapping(value = "/solutionUsers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<DemoUser> getALlUsers_Solution() {
        return Flux.range(1, 50)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(input -> System.out.println("Processing-" + input))
                .map(input -> new DemoUser(input, "User-" + input));
    }
}
