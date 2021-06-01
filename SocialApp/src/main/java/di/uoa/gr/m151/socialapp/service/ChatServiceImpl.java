package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.DTO.MessageDTO;
import di.uoa.gr.m151.socialapp.DTO.UserDTO;
import di.uoa.gr.m151.socialapp.entity.Message;
import di.uoa.gr.m151.socialapp.entity.User;
import di.uoa.gr.m151.socialapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    public List<MessageDTO> retrieveChatHistory(String user, String friend, int page) {
        Pageable pageable = PageRequest.of(page,20);
        List<MessageDTO> messageList =  messageRepository.findChatHistory(user, friend, pageable);
        Collections.reverse(messageList);
        return messageList;

    }

    @Override
    public List<MessageDTO> updateChatHistory(String user, String friend, String date)  {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS");

        try {
            Date checkDate = format.parse(date);
            LocalDateTime localDateTime = Instant.ofEpochMilli(checkDate.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            localDateTime = localDateTime.plusHours(3);
            checkDate = java.util.Date.from(localDateTime
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
            return  messageRepository.findChatHistory(user, friend, checkDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<UserDTO> findAllUsers(Integer page) {

        List<User> userList = userService.findAllUsers(page);
        List<UserDTO> dtoList = new ArrayList<UserDTO>();

        for (User user : userList) {
            UserDTO dto = userService.fillUserDTO(user);
            dtoList.add(dto);
        }

        return dtoList;
    }

}
