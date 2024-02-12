package com.cji.citas.dto;

import lombok.Data;

@Data
public class ContactFormDTO {
    private String to;
    private String subject;
    private String message;
}
