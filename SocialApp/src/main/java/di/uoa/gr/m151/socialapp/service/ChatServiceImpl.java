package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.DTO.MessageDTO;
import di.uoa.gr.m151.socialapp.DTO.UserDTO;
import di.uoa.gr.m151.socialapp.entity.Message;
import di.uoa.gr.m151.socialapp.entity.User;
import di.uoa.gr.m151.socialapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserService userService;

    @Override
    public boolean saveAndSendMessage(MessageDTO messageDTO) {
        Message message = new Message();

        if (messageDTO.getMessage() == null || messageDTO.getMessage().isEmpty()
        || messageDTO.getSender() == null || messageDTO.getSender().isEmpty()
        || messageDTO.getRecipient() == null || messageDTO.getSender().isEmpty()
        || !userService.userExists(messageDTO.getSender())
        || !userService.userExists(messageDTO.getRecipient())) {
            return false;
        }

        message.setText(messageDTO.getMessage());
        message.setSender(userService.findByUserName(messageDTO.getSender()));
        message.setReceiver(userService.findByUserName(messageDTO.getRecipient()));
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return messageRepository.save(message) != null;


    }

    @Override
    public List<MessageDTO> retrieveChatHistory(String user, String friend) {

        return messageRepository.findChatHistory(user, friend);

    }

    public List<UserDTO> findAllUsers() {

        List<User> userList = userService.findAllUsers();
        List<UserDTO> dtoList = new ArrayList<UserDTO>();

        for (User user : userList) {
            UserDTO dto = userService.fillUserDTO(user);
            dtoList.add(dto);
        }

        return dtoList;

    }


}
