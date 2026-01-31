package com.hjc.entity;

import lombok.Data;

@Data
public class ChatTranscriptMessage {
    private String type;
    private String content;
    private String time;

    public ChatTranscriptMessage() {}

    public ChatTranscriptMessage(String type, String content, String time) {
        this.type = type;
        this.content = content;
        this.time = time;
    }
}
