package com.infosite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.infosite.controller", "com.infosite.db"})
public class AppConfig extends WebMvcConfigurerAdapter{

        @Bean
        public ViewResolver jspViewResolver(){
                InternalResourceViewResolver resolver = new InternalResourceViewResolver();
                resolver.setViewClass(JstlView.class);
                resolver.setPrefix("/WEB-INF/");
                resolver.setSuffix(".jsp");
                return resolver;
        }
   /*     @Bean(name = "viewResolver")
        public InternalResourceViewResolver getViewResolver() {
                InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
                viewResolver.setPrefix("/WEB-INF/images/");
                viewResolver.setSuffix(".jpg");
                return viewResolver;
        }
*/
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

        }
}
