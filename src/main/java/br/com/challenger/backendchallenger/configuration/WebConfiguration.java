package br.com.challenger.backendchallenger.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(getPageableConfig());
        super.addArgumentResolvers(argumentResolvers);
    }

    private PageableHandlerMethodArgumentResolver getPageableConfig() {
        return new PageableHandlerMethodArgumentResolver();
    }
}
