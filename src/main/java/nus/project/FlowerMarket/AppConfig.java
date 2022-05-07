package nus.project.FlowerMarket;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nus.project.FlowerMarket.filters.LoginFilter;

@Configuration
public class AppConfig {
        
    @Bean
    public FilterRegistrationBean<LoginFilter> loggingFilter(){
        FilterRegistrationBean<LoginFilter> regFilter 
          = new FilterRegistrationBean<>();
            
        regFilter.setFilter(new LoginFilter());
        regFilter.addUrlPatterns("/users/*");
        // regFilter.setOrder(2);
            
        return regFilter;    
    }
}
