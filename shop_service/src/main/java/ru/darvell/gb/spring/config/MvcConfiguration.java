package ru.darvell.gb.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    public static final String UI_INDEX_PATH = "/ui/index.html";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirName = "data";
        String staticPath = Paths.get(System.getProperty("user.dir"), dirName).toFile().getAbsolutePath();

        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + staticPath + "/");
        registry.addResourceHandler("/ui/**").addResourceLocations("classpath:/ui/");

    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", UI_INDEX_PATH);
        registry.addRedirectViewController("/ui", UI_INDEX_PATH);
        registry.addRedirectViewController("/ui/", UI_INDEX_PATH);
    }
}
