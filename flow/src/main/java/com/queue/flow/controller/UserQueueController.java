package com.queue.flow.controller;

import com.queue.flow.dto.AllowUserResponse;
import com.queue.flow.dto.RegisterUserResponse;
import com.queue.flow.service.UserQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/queue")
@RequiredArgsConstructor
public class UserQueueController {
    private final UserQueueService userQueueService;

    /**
     * Register User
     * @param userId user ID to register in the queue
     * @return Mono of RegisterUserResponse containing the rank of the user in the queue
     */
    @PostMapping("")
    public Mono<RegisterUserResponse> registerUser(
            @RequestParam(name = "queue", defaultValue = "default") String queue,
            @RequestParam(name = "user_id") Long userId) {
        return userQueueService.registerWaitQueue(queue, userId)
                .map(RegisterUserResponse::new);
    }

    /**
     * Allowed user and Deleted Wait
     * @param queue
     * @param count
     * @return
     */
    @PostMapping("/allow")
    public Mono<?> allowUser(
            @RequestParam(name = "queue", defaultValue = "default") String queue,
            @RequestParam(name = "count") Long count){
        return userQueueService.allowUser(queue, count)
                .map(allowed -> new AllowUserResponse(count, allowed));
    }
}
