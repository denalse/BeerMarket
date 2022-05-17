package nus.project.FlowerMarket;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nus.project.FlowerMarket.filters.LoginFilter;

@Configuration
public class AppConfig {

  @Bean
  public FilterRegistrationBean<LoginFilter> registerFilters() {
      // Create an instance of authentication filter
      LoginFilter authFilter = new LoginFilter();

      // Create an instance of registration filter
      FilterRegistrationBean<LoginFilter> regFilter = new FilterRegistrationBean<>();
      regFilter.setFilter(authFilter);
      regFilter.addUrlPatterns("/protected/*");
      
      return regFilter;
  }
        
    // @Bean
    // public FilterRegistrationBean<LoginFilter> loggingFilter(){
    //     FilterRegistrationBean<LoginFilter> regFilter 
    //       = new FilterRegistrationBean<>();
            
    //     regFilter.setFilter(new LoginFilter());
    //     regFilter.addUrlPatterns("/users/*");
    //     // regFilter.setOrder(2);
            
    //     return regFilter;    
    // }
}
