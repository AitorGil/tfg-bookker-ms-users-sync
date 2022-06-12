package com.aitorgc.organizations.api.config;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 *
 * @author Aitor Gil Callejo
 */
@Configuration
public class AuditorAwareConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    AuditorAware<String> auditorAware(HttpServletRequest request) {
        return () -> Optional.ofNullable(request.getHeader("x-on-behalf-of")).filter(userName -> StringUtils.isNotBlank(userName))
                .or(() -> Optional.of(appName));
    }

}
