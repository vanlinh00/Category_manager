package com.example.CategoryManagement.Requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubCategoryRequest {
    private String categoryName;
    private List<SubCategoryRequest> subCategories; // Nếu bạn muốn hỗ trợ cấp độ sâu
}