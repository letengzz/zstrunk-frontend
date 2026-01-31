package com.hjc.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjc.common.response.ResponseEntity;
import com.hjc.entity.CarouselItem;
import com.hjc.entity.Solution;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/carousel")
    public ResponseEntity<List<CarouselItem>> getCarouselConfig() {
        try {
            ClassPathResource resource = new ClassPathResource("carousel.json");
            List<CarouselItem> carouselItems = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<CarouselItem>>() {}
            );
            return ResponseEntity.success(carouselItems);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.success(List.of());
        }
    }

    @GetMapping("/solutions")
    public ResponseEntity<List<Solution>> getSolutionsConfig() {
        try {
            ClassPathResource resource = new ClassPathResource("solutions.json");
            List<Solution> solutions = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<Solution>>() {}
            );
            return ResponseEntity.success(solutions);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.success(List.of());
        }
    }
}
