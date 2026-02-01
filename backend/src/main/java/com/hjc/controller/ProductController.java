package com.hjc.controller;

import com.hjc.common.response.ResponseEntity;
import com.hjc.entity.CategoryTreeNode;
import com.hjc.entity.Product;
import com.hjc.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "产品管理")
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Resource
    private ProductService productService;

    @Operation(summary = "获取所有产品")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.success(productService.getAllProducts());
    }

    @Operation(summary = "根据ID获取产品")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @Parameter(description = "产品ID") @PathVariable Integer id) {
        return productService.getProductById(id)
                .map(ResponseEntity::success)
                .orElse(ResponseEntity.failure(404, "产品不存在"));
    }

    @Operation(summary = "根据分类获取产品")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(
            @Parameter(description = "产品分类") @PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.success(products);
    }

    @Operation(summary = "根据子分类获取产品")
    @GetMapping("/subcategory/{subCategory}")
    public ResponseEntity<List<Product>> getProductsBySubCategory(
            @Parameter(description = "产品子分类") @PathVariable String subCategory) {
        List<Product> products = productService.getProductsBySubCategory(subCategory);
        return ResponseEntity.success(products);
    }

    @Operation(summary = "获取最新产品")
    @GetMapping("/latest")
    public ResponseEntity<List<Product>> getLatestProducts(
            @Parameter(description = "返回数量限制") @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.success(productService.getLatestProducts(limit));
    }

    @Operation(summary = "获取所有分类")
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        return ResponseEntity.success(productService.getAllCategories());
    }

    @Operation(summary = "搜索产品")
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @Parameter(description = "搜索关键词") @RequestParam String keyword) {
        return ResponseEntity.success(productService.searchProducts(keyword));
    }

    @Operation(summary = "按分类分组获取产品")
    @GetMapping("/grouped")
    public ResponseEntity<Map<String, List<Product>>> getProductsGroupedByCategory() {
        return ResponseEntity.success(productService.getProductsGroupedByCategory());
    }

    @Operation(summary = "获取产品总数")
    @GetMapping("/count")
    public ResponseEntity<Long> getProductCount() {
        return ResponseEntity.success(productService.getProductCount());
    }

    @Operation(summary = "根据分类获取产品数量")
    @GetMapping("/count/category/{category}")
    public ResponseEntity<Long> getProductCountByCategory(
            @Parameter(description = "产品分类") @PathVariable String category) {
        return ResponseEntity.success(productService.getProductCountByCategory(category));
    }

    @Operation(summary = "获取分类树")
    @GetMapping("/category-tree")
    public ResponseEntity<List<CategoryTreeNode>> getCategoryTree() {
        return ResponseEntity.success(productService.getCategoryTree());
    }

    @Operation(summary = "获取热门产品")
    @GetMapping("/hot")
    public ResponseEntity<List<Product>> getHotProducts() {
        return ResponseEntity.success(productService.getHotProducts());
    }
}
