
package edu.ulima.validator;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
 
    @Bean
    public MessageSource messageSource() {
        System.out.println("===> Cargando messageSource ....");
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:validation");
        ms.setDefaultEncoding("UTF-8");
        
        return ms;
    }
}
