package com.queue.flow.controller;

import com.queue.flow.service.UserQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

import javax.swing.*;

@Controller
@RequiredArgsConstructor
public class WaitingRoomController {

    private final UserQueueService userQueueService;

    /**
     * @Description
     * @1. Check if queue and userId are in acceptable state
     * @2. Register if not allowed
     * @3. If the rank is not registered, register the rank
     * @param queue
     * @param userId
     * @param redirectUrl
     * @return html
     */
    @GetMapping("/waiting-room")
    Mono<Rendering> waitingRoomPage(@RequestParam(name = "queue", defaultValue = "default") String queue,
                                    @RequestParam(name = "user_id") Long userId,
                                    @RequestParam(name = "redirect_url") String redirectUrl) {
        // 대기 등록
        // 웹페이지 필요한 데이터를 전달
        return userQueueService.isAllowed(queue, userId)
                .filter(allowed -> allowed)
                .flatMap(allowed -> Mono.just(Rendering.redirectTo(redirectUrl).build()))
                .switchIfEmpty(userQueueService.registerWaitQueue(queue, userId)
                        .onErrorResume(ex -> userQueueService.getRank(queue, userId))
                        .map(rank -> Rendering.view("waiting-room.html")
                                .modelAttribute("number", rank)
                                .modelAttribute("userId", userId)
                                .modelAttribute("queue", queue)
                                .build()
                        ));

    }
}
