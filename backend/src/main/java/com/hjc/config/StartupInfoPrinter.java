package com.hjc.config;

import com.hjc.utils.NetworkUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 打印启动信息
 */
@Component
public class StartupInfoPrinter implements CommandLineRunner {

    @Value("${server.port:8080}")
    private int serverPort;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Value("${spring.application.name:Spring Boot App}")
    private String appName;

    @Override
    public void run(String... args) {
        String ip = NetworkUtils.getLocalIp();
        String context = contextPath.startsWith("/") ? contextPath : (contextPath.isEmpty() ? "" : "/" + contextPath);
        String baseUrl = "http://" + ip + ":" + serverPort + context;
        String localUrl = "http://localhost:" + serverPort + context;
        String swaggerUrl = baseUrl + "/doc.html";

        // ANSI 颜色代码
        String GREEN = "\u001b[32;1m";
        String YELLOW = "\u001b[33;1m";
        String WHITE = "\u001b[37;1m";
        String BLUE = "\u001b[34;1m";
        String RESET = "\u001b[0m";

        System.out.println("""
                
                %s=================================================%s
                %s🚀  %s Started Successfully!%s
                %s-------------------------------------------------%s
                %sApp Name:   %s%-40s%s
                %sLocal:      %s%-40s%s
                %sExternal:   %s%-40s%s
                %sSwagger UI: %s%-40s%s
                %sActuator:   %s%-40s%s
                %s=================================================%s
                """.formatted(
                GREEN, RESET,
                YELLOW, appName, RESET,
                WHITE, RESET,
                BLUE, WHITE, appName, RESET,
                BLUE, WHITE, localUrl, RESET,
                BLUE, WHITE, baseUrl, RESET,
                BLUE, WHITE, swaggerUrl, RESET,
                BLUE, WHITE, baseUrl + "/actuator", RESET,
                GREEN, RESET
        ));
    }
}
