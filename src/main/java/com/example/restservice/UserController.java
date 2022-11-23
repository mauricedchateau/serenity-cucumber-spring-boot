package com.example.restservice;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    private final Map<String, User> availableUsers = Map.of(
            "1", new User("George", "Bluth")
    );

    @GetMapping("/users/{userId}")
    public User greeting(@PathVariable final String userId) {
        return availableUsers.get(userId);
    }
}
