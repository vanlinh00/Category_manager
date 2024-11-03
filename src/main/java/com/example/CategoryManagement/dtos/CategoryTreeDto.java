package com.example.CategoryManagement.dtos;

import lombok.*;

import java.util.List;


@Data
@Builder
@Getter
@Setter
public class CategoryTreeDto {
    private Long categoryId;
    private String categoryName;
    private List<CategoryTreeDto> subCategories;
}
