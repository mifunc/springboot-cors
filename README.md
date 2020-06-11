**跨域错误**

```
Access to XMLHttpRequest at 'http://127.0.0.1:8089/' from origin 'http://rumenz.com' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.

```

- 过滤器 CorsFilter
- 拦截器 addCorsMappings
- 注解 @CrossOrigin

## 过滤器

```java
@Configuration //返回CorsFilter
public class CorsConfig {
     private CorsConfiguration build(){
         CorsConfiguration cf=new CorsConfiguration();
         cf.addAllowedOrigin("*");
         cf.addAllowedHeader("*");
         cf.addAllowedMethod("*");
         return cf;
     }
     @Bean
     public CorsFilter corsFilter(){
         UrlBasedCorsConfigurationSource uc=new UrlBasedCorsConfigurationSource();
         uc.registerCorsConfiguration("/**",this.build());
         return new CorsFilter(uc);

     }
}

```

## 重写 WebMvcConfigurer addCorsMappings方法

```java
@Configuration
public class WebConfigs implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //放行哪些原始域
                .allowedOrigins("*")
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                .allowedHeaders("*");
    }
}

```
>> 如果有自定义拦截器,此方法会失效. 原因是请求经过的先后顺序问题，当请求到来时会先进入拦截器中，而不是进入Mapping映射中，所以返回的头信息中并没有配置的跨域信息。浏览器就会报跨域异常。

## 注解@CrossOrigin

```java
@RestController
@CrossOrigin //1.类上加
public class DemoController {

    @GetMapping("/")
    //@CrossOrigin // 2.方法上加
    public String index(){
        return "{\"code\":200,\"msg\":\"ok\",\"data\":[\"JSON.IM\",\"json格式化\"]}";
    }
}

```
