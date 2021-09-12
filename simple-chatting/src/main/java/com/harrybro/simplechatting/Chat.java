package com.harrybro.simplechatting;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document("chat")
public class Chat implements Serializable {

    @Id
    private String id;

    private String message;

    private String sender;

    private String receiver;

    private final LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public Chat(String message, String sender, String receiver) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }

}
