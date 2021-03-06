package di.uoa.gr.m151.socialapp.repository;

import di.uoa.gr.m151.socialapp.DTO.MessageDTO;
import di.uoa.gr.m151.socialapp.entity.Message;
import di.uoa.gr.m151.socialapp.entity.ThreadPost;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface MessageRepository extends PagingAndSortingRepository<Message, UUID> {

    @Query("select new di.uoa.gr.m151.socialapp.DTO.MessageDTO(m.text, m.sender.username, m.receiver.username, m.timestamp) from Message m where (m.sender.username=:user or m.sender.username=:friend)\n" +
            "and (m.receiver.username=:friend or m.receiver.username=:user)\n" +
            "order by m.timestamp desc ")
    List<MessageDTO> findChatHistory(@Param("user") String user, @Param("friend") String friend, Pageable pageable);

    @Query("select new di.uoa.gr.m151.socialapp.DTO.MessageDTO(m.text, m.sender.username, m.receiver.username, m.timestamp) from Message m where (m.sender.username=:user or m.sender.username=:friend)\n" +
            "and (m.receiver.username=:friend or m.receiver.username=:user) and m.timestamp > :date \n" +
            "order by m.timestamp")
    List<MessageDTO> findChatHistory(@Param("user") String user, @Param("friend") String friend, @Param("date")Date date);


}
