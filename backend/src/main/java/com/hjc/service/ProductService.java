package com.hjc.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjc.entity.CategoryTreeNode;
import com.hjc.entity.Product;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {

    private List<Product> products;

    @PostConstruct
    public void init() {
        try {
            ClassPathResource resource = new ClassPathResource("data/products.json");
            ObjectMapper mapper = new ObjectMapper();
            products = mapper.readValue(resource.getInputStream(), new TypeReference<List<Product>>() {});
            log.info("成功加载 {} 个产品数据", products.size());
        } catch (IOException e) {
            log.error("加载产品数据失败", e);
            products = List.of();
        }
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(Integer id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(p -> category == null || p.getCategory() == null || 
                           p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsBySubCategory(String subCategory) {
        return products.stream()
                .filter(p -> subCategory == null || p.getSubCategory() == null ||
                           p.getSubCategory().equalsIgnoreCase(subCategory))
                .collect(Collectors.toList());
    }

    public List<Product> getLatestProducts(int limit) {
        return products.stream()
                .sorted(Comparator.comparing(Product::getId).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<String> getAllCategories() {
        return products.stream()
                .map(Product::getCategory)
                .filter(category -> category != null)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public Map<String, List<Product>> getProductsGroupedByCategory() {
        return products.stream()
                .filter(p -> p.getCategory() != null)
                .collect(Collectors.groupingBy(Product::getCategory));
    }

    public List<Product> searchProducts(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return products;
        }
        String lowerKeyword = keyword.toLowerCase().trim();
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(lowerKeyword) ||
                           p.getDesc().toLowerCase().contains(lowerKeyword) ||
                           (p.getCategory() != null && p.getCategory().toLowerCase().contains(lowerKeyword)) ||
                           (p.getSubCategory() != null && p.getSubCategory().toLowerCase().contains(lowerKeyword)))
                .collect(Collectors.toList());
    }

    public long getProductCount() {
        return products.size();
    }

    public long getProductCountByCategory(String category) {
        return getProductsByCategory(category).size();
    }

    public List<CategoryTreeNode> getCategoryTree() {
        try {
            ClassPathResource resource = new ClassPathResource("data/category-tree.json");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(resource.getInputStream(), new TypeReference<List<CategoryTreeNode>>() {});
        } catch (IOException e) {
            log.error("加载分类树数据失败", e);
            return List.of();
        }
    }
}
