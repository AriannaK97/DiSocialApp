package di.uoa.gr.m151.socialapp.controller;

import di.uoa.gr.m151.socialapp.DTO.MessageDTO;
import di.uoa.gr.m151.socialapp.entity.Page;
import di.uoa.gr.m151.socialapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chat/")
public class ChatController {

    @Autowired
    ChatService chatService;

    @PostMapping("/send")
    public Boolean sendMessage(@RequestBody MessageDTO message) {
        return chatService.saveAndSendMessage(message);
    }


    @GetMapping("/chatHistory")
    public List<MessageDTO> retrieveChat(@RequestParam String user, @RequestParam String friend) {
        return chatService.retrieveChatHistory(user, friend);
    }


}
