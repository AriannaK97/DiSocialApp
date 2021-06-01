package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.DTO.MessageDTO;
import di.uoa.gr.m151.socialapp.DTO.UserDTO;

import java.util.Date;
import java.util.List;

public interface ChatService {

    boolean saveAndSendMessage(MessageDTO messageDTO);

    List<MessageDTO> retrieveChatHistory(String user, String friend, int page);

    List<MessageDTO> updateChatHistory(String user, String friend, String date);

    List<UserDTO> findAllUsers(Integer page);

}
