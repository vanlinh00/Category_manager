package com.example.CategoryManagement.controllers;

import com.example.CategoryManagement.Repositories.CategoryGroupRepository;
import com.example.CategoryManagement.Repositories.CategoryRepository;
import com.example.CategoryManagement.Requests.CategoryGroupRequest;
import com.example.CategoryManagement.Requests.CategoryRequest;
import com.example.CategoryManagement.Requests.SubCategoryRequest;
import com.example.CategoryManagement.models.Category;
import com.example.CategoryManagement.models.CategoryGroup;
import com.example.CategoryManagement.models.CategoryGroupCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryGroupRepository categoryGroupRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // 1. Thêm CategoryGroup
    @PostMapping("/addGroup")
    public ResponseEntity<String> addCategoryGroup(@RequestBody String groupName) {
        CategoryGroup categoryGroup = new CategoryGroup();
        categoryGroup.setName(groupName);
        categoryGroupRepository.save(categoryGroup); // Lưu vào DB
        return ResponseEntity.ok("Đã thêm CategoryGroup: " + groupName);
    }

    // 2. Thêm Category
    @PostMapping("/addCategory/{groupId}")
    public ResponseEntity<String> addCategory(@PathVariable Long groupId, @RequestBody String categoryName) {
        CategoryGroup categoryGroup = categoryGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("CategoryGroup không tồn tại!"));

        Category category = new Category();
        category.setName(categoryName);
        category.setParentCategory(null); // không có danh mục cha
        category = categoryRepository.save(category); // Lưu vào DB

        // Lưu CategoryGroupCategory để thiết lập mối quan hệ
        CategoryGroupCategory categoryGroupCategory = new CategoryGroupCategory();
        categoryGroupCategory.setCategoryGroup(categoryGroup);
        categoryGroupCategory.setCategory(category);
        // Giả sử bạn có repository cho CategoryGroupCategory
        // categoryGroupCategoryRepository.save(categoryGroupCategory); // Lưu vào DB nếu cần

        return ResponseEntity.ok("Đã thêm Category: " + categoryName + " vào CategoryGroup ID: " + groupId);
    }

    // 3. Thêm SubCategory
    @PostMapping("/addSubCategory/{categoryId}")
    public ResponseEntity<String> addSubCategory(@PathVariable Long categoryId, @RequestBody String subCategoryName) {
        Category parentCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category không tồn tại!"));

        Category subCategory = new Category();
        subCategory.setName(subCategoryName);
        subCategory.setParentCategory(parentCategory); // Thiết lập danh mục cha
        categoryRepository.save(subCategory); // Lưu vào DB

        return ResponseEntity.ok("Đã thêm SubCategory: " + subCategoryName + " vào Category ID: " + categoryId);
    }

    // Phương thức để thêm CategoryGroup và các Category/SubCategory
    @PostMapping("/addAll")
    public ResponseEntity<String> addAllCategories(@RequestBody CategoryGroupRequest request) {
        // 1. Thêm CategoryGroup
        CategoryGroup categoryGroup = new CategoryGroup();
        categoryGroup.setName(request.getGroupName());
        categoryGroupRepository.save(categoryGroup);

        // 2. Thêm các Category và SubCategory
        for (CategoryRequest categoryRequest : request.getCategories()) {
            Category category = new Category();
            category.setName(categoryRequest.getCategoryName());
            category.setParentCategory(null); // Thiết lập danh mục cha
            category = categoryRepository.save(category); // Lưu vào DB

            // Thêm SubCategory nếu có
            for (SubCategoryRequest subCategoryRequest : categoryRequest.getSubCategories()) {
                Category subCategory = new Category();
                subCategory.setName(subCategoryRequest.getCategoryName());
                subCategory.setParentCategory(category); // Thiết lập danh mục cha
                categoryRepository.save(subCategory); // Lưu vào DB
            }
        }

        return ResponseEntity.ok("Đã thêm CategoryGroup và các Category/SubCategory thành công.");
    }
}
/*

2. Gửi Yêu Cầu từ Postman
Thêm CategoryGroup
Mở Postman và tạo yêu cầu mới.
Chọn phương thức: POST.
Nhập URL: http://localhost:8080/api/categories/addGroup
Chọn tab "Body", chọn "raw" và định dạng JSON.
Nhập dữ liệu JSON như sau:
json
Copy code
"Sản phẩm phổ biến"
Nhấn "Send" để gửi yêu cầu.
Thêm Category
Tạo yêu cầu mới trong Postman.
Chọn phương thức: POST.
Nhập URL: http://localhost:8080/api/categories/addCategory/{groupId}
Thay {groupId} bằng ID của CategoryGroup mà bạn đã thêm ở bước trước (ví dụ: 1).
Chọn tab "Body", chọn "raw" và định dạng JSON.
Nhập dữ liệu JSON như sau:
json
Copy code
"Điện thoại"
Nhấn "Send" để gửi yêu cầu.
Thêm SubCategory
Tạo yêu cầu mới trong Postman.
Chọn phương thức: POST.
Nhập URL: http://localhost:8080/api/categories/addSubCategory/{categoryId}
Thay {categoryId} bằng ID của Category mà bạn đã thêm ở bước trước (ví dụ: 2).
Chọn tab "Body", chọn "raw" và định dạng JSON.
Nhập dữ liệu JSON như sau:
json
Copy code
"Nokia"
Nhấn "Send" để gửi yêu cầu.
 */