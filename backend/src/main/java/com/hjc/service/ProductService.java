package com.hjc.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjc.entity.CategoryTreeNode;
import com.hjc.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {

    @Value("${app.data-path:}")
    private String dataPath;

    private volatile List<Product> products;

    private volatile List<CategoryTreeNode> categoryTree;

    private volatile String productsHash = "";

    private volatile String categoryTreeHash = "";

    private static final String PRODUCTS_JSON_PATH = "products.json";

    private static final String CATEGORY_TREE_JSON_PATH = "category-tree.json";

    private File getProductsFile() {
        if (dataPath != null && !dataPath.isEmpty()) {
            File file = new File(dataPath, PRODUCTS_JSON_PATH);
            if (file.exists()) {
                return file;
            }
        }
        return null;
    }

    private File getCategoryTreeFile() {
        if (dataPath != null && !dataPath.isEmpty()) {
            File file = new File(dataPath, CATEGORY_TREE_JSON_PATH);
            if (file.exists()) {
                return file;
            }
        }
        return null;
    }

    private String calculateHash(InputStream inputStream) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
            byte[] hash = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    private List<Product> loadProductsFromFile(File file) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            log.error("加载产品数据失败: {}", file.getAbsolutePath(), e);
            return List.of();
        }
    }

    private List<CategoryTreeNode> loadCategoryTreeFromFile(File file) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, new TypeReference<List<CategoryTreeNode>>() {});
        } catch (IOException e) {
            log.error("加载分类树数据失败: {}", file.getAbsolutePath(), e);
            return List.of();
        }
    }

    private void checkAndReloadProducts() {
        File file = getProductsFile();
        if (file == null) {
            return;
        }
        try {
            String currentHash = calculateHash(new java.io.FileInputStream(file));
            if (!currentHash.equals(productsHash)) {
                products = loadProductsFromFile(file);
                productsHash = currentHash;
                log.info("产品数据已重新加载，共 {} 个产品", products.size());
            }
        } catch (IOException e) {
            log.warn("检查产品数据失败，继续使用缓存数据", e);
        }
    }

    private void checkAndReloadCategoryTree() {
        File file = getCategoryTreeFile();
        if (file == null) {
            return;
        }
        try {
            String currentHash = calculateHash(new java.io.FileInputStream(file));
            if (!currentHash.equals(categoryTreeHash)) {
                categoryTree = loadCategoryTreeFromFile(file);
                categoryTreeHash = currentHash;
                log.info("分类树数据已重新加载");
            }
        } catch (IOException e) {
            log.warn("检查分类树数据失败，继续使用缓存数据", e);
        }
    }

    public List<Product> getAllProducts() {
        checkAndReloadProducts();
        return products != null ? products : List.of();
    }

    public Optional<Product> getProductById(Integer id) {
        checkAndReloadProducts();
        if (products == null) return Optional.empty();
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<Product> getProductsByCategory(String category) {
        checkAndReloadProducts();
        if (products == null) return List.of();
        return products.stream()
                .filter(p -> category == null || p.getCategory() == null || 
                           p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsBySubCategory(String subCategory) {
        checkAndReloadProducts();
        if (products == null) return List.of();
        return products.stream()
                .filter(p -> subCategory == null || p.getSubCategory() == null ||
                           p.getSubCategory().equalsIgnoreCase(subCategory))
                .collect(Collectors.toList());
    }

    public List<Product> getLatestProducts(int limit) {
        checkAndReloadProducts();
        if (products == null) return List.of();
        return products.stream()
                .sorted(Comparator.comparing(Product::getId).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<String> getAllCategories() {
        checkAndReloadProducts();
        if (products == null) return List.of();
        return products.stream()
                .map(Product::getCategory)
                .filter(category -> category != null)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public Map<String, List<Product>> getProductsGroupedByCategory() {
        checkAndReloadProducts();
        if (products == null) return Map.of();
        return products.stream()
                .filter(p -> p.getCategory() != null)
                .collect(Collectors.groupingBy(Product::getCategory));
    }

    public List<Product> searchProducts(String keyword) {
        checkAndReloadProducts();
        if (products == null) return List.of();
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
        checkAndReloadProducts();
        return products != null ? products.size() : 0;
    }

    public long getProductCountByCategory(String category) {
        checkAndReloadProducts();
        return getProductsByCategory(category).size();
    }

    public List<CategoryTreeNode> getCategoryTree() {
        checkAndReloadCategoryTree();
        return categoryTree != null ? categoryTree : List.of();
    }
}
