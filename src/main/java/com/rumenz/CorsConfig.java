package com.rumenz;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration //返回CorsFilter
public class CorsConfig {
     private CorsConfiguration build(){
         CorsConfiguration cf=new CorsConfiguration();
         cf.addAllowedOrigin("*");
         cf.addAllowedHeader("*");
         cf.addAllowedMethod("*");
         return cf;
     }
     //@Bean
     public CorsFilter corsFilter(){
         UrlBasedCorsConfigurationSource uc=new UrlBasedCorsConfigurationSource();
         uc.registerCorsConfiguration("/**",this.build());
         return new CorsFilter(uc);

     }
}
