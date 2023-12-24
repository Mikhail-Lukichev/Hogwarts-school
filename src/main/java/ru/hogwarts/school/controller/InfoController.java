package ru.hogwarts.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.AvatarService;
import java.util.stream.Stream;


@RestController
@RequestMapping("info")
public class InfoController {

    Logger logger = LoggerFactory.getLogger(AvatarService.class);

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/port")
    @ResponseBody
    public Integer getPort() {
        return port;
    }

    @GetMapping("/parallelStream")
    @ResponseBody
    public Integer parallelStream() {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();

        long startTime = System.nanoTime();

        int sum = Stream.iterate(1, a -> a +1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        logger.info("Call " + className + " " + methodName + ". Execution time: " + duration + "ms");
        return sum;
    }
}
