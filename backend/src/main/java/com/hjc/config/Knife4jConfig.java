package com.hjc.config;

import com.hjc.utils.NetworkUtils;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@Profile({"dev", "test"})
@Slf4j
public class Knife4jConfig {

    @Resource
    private Environment environment;

    @Value("${server.port:8080}")
    private int serverPort;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Bean
    public OpenAPI springShopOpenAPI() throws UnknownHostException {
        return new OpenAPI()
                // 接口文档标题
                .info(new Info().title("API接口文档")
                        // 接口文档简介
                        .description("API接口文档")
                        // 接口文档版本
                        .version("1.0版本")
                        // 开发者联系方式
                        .contact(new Contact().name("hjc")
                                .email("000000000@qq.com")));
//                .externalDocs(new ExternalDocumentation()
//                        .description("API接口文档")
//                        .url("http://"+NetworkUtils.getLocalIp()+":"+serverPort+(contextPath.startsWith("/") ? contextPath : (contextPath.isEmpty() ? "" : "/" + contextPath))));
    }
}
