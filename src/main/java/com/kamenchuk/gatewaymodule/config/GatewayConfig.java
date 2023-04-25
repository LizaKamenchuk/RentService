package com.kamenchuk.gatewaymodule.config;


import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

//    @Bean
//    @LoadBalanced
//    public WebClient.Builder loadBalancedWebClientBuilder() {
//        return WebClient.builder();
//    }
//
//    @Bean
//   public RouteLocator routes(
//            RouteLocatorBuilder builder,
//            AuthenticationFilter authFilter) {
//        return builder.routes()
//                .route("rent_module", r -> r.path("/rent_module/**")
//                        .filters(f -> f.filter(authFilter.apply(
//                                new AuthenticationFilter.Config())))
//                        .uri("lb://rent_module"))
//                .route("auth_module", r -> r.path("/auth_module/**")
//                        .filters(f -> f.filter(authFilter.apply(
//                                new AuthenticationFilter.Config())))
//                        .uri("lb://auth_module"))
//                .build();
//    }
}
