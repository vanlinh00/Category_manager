package com.example.CategoryManagement.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class CategoryGroupDto {
    private Long groupId;
    private String groupName;
    private List<CategoryTreeDto> categories;
}