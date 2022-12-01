package com.example.backend.controller;

import com.example.backend.common.controller.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
@RestController
@RequestMapping()
@Slf4j
@RequiredArgsConstructor
public class SendingController extends BaseController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    @GetMapping("socket")
    public void greeting(String message) throws Exception {
        log.error(message);
        Message<String> data = new Message<>() {
            @Override
            public String getPayload() {
                return "hello";
            }

            @Override
            public MessageHeaders getHeaders() {
                return null;
            }
        };
        simpMessagingTemplate.send("/topic/greetings",data);
    }

}