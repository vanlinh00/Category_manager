package com.example.CategoryManagement.dtos.Kafkas;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Message {
    private String categoryCode;
    private String categoryGroupCode;
    private String message;
}
