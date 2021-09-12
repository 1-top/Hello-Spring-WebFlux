package com.harrybro.simplechatting;

import lombok.Setter;

@Setter
public class ChatDto {
    private String message;

    private String sender;

    private String receiver;

    public Chat toEntity() {
        return Chat.builder()
                .message(this.message)
                .sender(this.sender)
                .receiver(this.receiver)
                .build();
    }
}
