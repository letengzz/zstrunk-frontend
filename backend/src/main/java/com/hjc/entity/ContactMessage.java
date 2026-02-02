package com.hjc.entity;

import lombok.Data;

/**
 * 联系消息实体
 */
@Data
public class ContactMessage {
    private String name;
    private String email;
    private String phone;
    private String company;
    private String subject;
    private String message;
    private Long timestamp;
}
