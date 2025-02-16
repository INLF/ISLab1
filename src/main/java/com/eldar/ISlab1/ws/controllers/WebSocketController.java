package com.eldar.ISlab1.ws.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    // Listen for messages from clients at `/app/send`
    @MessageMapping("/send")
    @SendTo("/topic/updates")
    public String handleMessage(String message) {
        log.info("📩 Received WebSocket Message: {}", message);
        return "Server Response: " + message;
    }

    // Manually send updates to all subscribers of `/topic/updates`
    public void sendUpdate(String updateMessage) {
        log.info("🔄 Sending WebSocket Update: {}", updateMessage);
        messagingTemplate.convertAndSend("/topic/updates", updateMessage);
    }
}

