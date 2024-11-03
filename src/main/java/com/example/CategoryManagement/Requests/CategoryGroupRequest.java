package com.example.CategoryManagement.Requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryGroupRequest {
    private String groupName;
    private List<CategoryRequest> categories;
}