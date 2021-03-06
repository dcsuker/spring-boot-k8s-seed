package com.danielpacak.seed.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
@EnableConfigurationProperties
public class Application {

    @Autowired
    private Config config;

    @Autowired
    private CommandService commandService;

    @RequestMapping("/")
    public String home() {
        return "Hello Docker World!";
    }

    @RequestMapping("/command/output")
    public String executeCommand(@RequestBody  ExecuteCommandInput input) {
        return commandService.execute(input.getCommand());
    }

    @RequestMapping("/env")
    public Map<String, String> env() {
        return System.getenv();
    }

    @RequestMapping("/version")
    public String version() {
        return getClass().getPackage().getImplementationVersion();
    }

    @RequestMapping("/config")
    public Config config() {
        return config;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
