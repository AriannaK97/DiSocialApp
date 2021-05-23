package di.uoa.gr.m151.socialapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
@Data
@AllArgsConstructor
public class MessageDTO {

    String message;

    String sender;

    String recipient;

    Date timestamp;

}
