package com.example.CategoryManagement.dtos.Kafkas;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryMessage {
    private String categoryCode;
    private String categoryName;
    private String categoryGroupCode;
    private String categoryGroupName;
}
