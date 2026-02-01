package com.hjc.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjc.common.response.ResponseEntity;
import com.hjc.entity.CarouselItem;
import com.hjc.entity.Solution;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/config")
@CrossOrigin(origins = "*")
public class ConfigController {

    @Value("${app.data-path:./data}")
    private String dataPath;

    private static final String CAROUSEL_JSON_PATH = "carousel.json";
    private static final String SOLUTIONS_JSON_PATH = "solutions.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<CarouselItem> carouselItems;
    private String carouselHash;
    private List<Solution> solutions;
    private String solutionsHash;

    @PostConstruct
    public void init() {
        checkAndReloadCarousel();
        checkAndReloadSolutions();
    }

    private File getCarouselFile() {
        return new File(dataPath, CAROUSEL_JSON_PATH);
    }

    private File getSolutionsFile() {
        return new File(dataPath, SOLUTIONS_JSON_PATH);
    }

    private String calculateHash(FileInputStream fis) throws IOException {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } finally {
            fis.close();
        }
    }

    private void checkAndReloadCarousel() {
        File file = getCarouselFile();
        if (!file.exists()) {
            carouselItems = List.of();
            return;
        }
        try {
            String currentHash = calculateHash(new FileInputStream(file));
            if (!currentHash.equals(carouselHash)) {
                carouselHash = currentHash;
                FileSystemResource resource = new FileSystemResource(file);
                carouselItems = objectMapper.readValue(
                        resource.getInputStream(),
                        new TypeReference<List<CarouselItem>>() {}
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            carouselItems = List.of();
        }
    }

    private void checkAndReloadSolutions() {
        File file = getSolutionsFile();
        if (!file.exists()) {
            solutions = List.of();
            return;
        }
        try {
            String currentHash = calculateHash(new FileInputStream(file));
            if (!currentHash.equals(solutionsHash)) {
                solutionsHash = currentHash;
                FileSystemResource resource = new FileSystemResource(file);
                solutions = objectMapper.readValue(
                        resource.getInputStream(),
                        new TypeReference<List<Solution>>() {}
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            solutions = List.of();
        }
    }

    @GetMapping("/carousel")
    public ResponseEntity<List<CarouselItem>> getCarouselConfig() {
        checkAndReloadCarousel();
        return ResponseEntity.success(carouselItems != null ? carouselItems : List.of());
    }

    @GetMapping("/solutions")
    public ResponseEntity<List<Solution>> getSolutionsConfig() {
        checkAndReloadSolutions();
        return ResponseEntity.success(solutions != null ? solutions : List.of());
    }
}
