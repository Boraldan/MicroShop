package boraldan.account.config_oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.stream.Stream;

//import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAuthority;
//import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;
//import static org.springframework.security.authorization.AuthorizationManagers.allOf;

@Configuration
public class SecurityConfig {

    //  для ресурса -------------  работает всё ----------------------
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(configurer -> configurer
//                                .requestMatchers("/h2", "/h2/**").permitAll()
                                .requestMatchers("/**").hasRole("CUSTOMER")
//                                .anyRequest().permitAll()
                                .anyRequest().authenticated()
//                                .anyRequest().hasRole("CUSTOMER")
//                        .anyRequest().access(allOf(hasRole("CUSTOMER"), hasAuthority("SCOPE_view_catalog")))
                )
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(CsrfConfigurer::disable)
                .oauth2ResourceServer(configurer -> configurer.jwt(jwt -> {
                    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
                    jwtAuthenticationConverter.setPrincipalClaimName("preferred_username");
                    jwt.jwtAuthenticationConverter(jwtAuthenticationConverter);

                    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

                    JwtGrantedAuthoritiesConverter customJwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
                    customJwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("groups");
                    customJwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

                    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(token ->
                            Stream.concat(jwtGrantedAuthoritiesConverter.convert(token).stream(),
                                    customJwtGrantedAuthoritiesConverter.convert(token).stream()).toList());
                }))

                .build();
    }


    //   для ресурса, заточенная под scope авторизацию     -------------------------

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
////                                .anyRequest().permitAll()  // работает
////                                .requestMatchers("/**").hasAuthority("SCOPE_view_catalog")  // работает

////                        .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
////                        .requestMatchers(HttpMethod.POST, "/catalogue-api/products")
////                        .hasAuthority("SCOPE_edit_catalogue")
////                        .requestMatchers(HttpMethod.PATCH, "/catalogue-api/products/{productId:\\d}")
////                        .hasAuthority("SCOPE_edit_catalogue")
////                        .requestMatchers(HttpMethod.DELETE, "/catalogue-api/products/{productId:\\d}")
////                        .hasAuthority("SCOPE_edit_catalogue")
////                        .requestMatchers("/actuator/**").hasAuthority("SCOPE_metrics")
////                        .requestMatchers(HttpMethod.GET).hasAuthority("SCOPE_view_catalogue")
////                        .anyRequest().denyAll()
//                )
//                .csrf(CsrfConfigurer::disable)
//                .sessionManagement(sessionManagement -> sessionManagement
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
//                        .jwt(Customizer.withDefaults()))
////                .oauth2Client(Customizer.withDefaults())
//                .build();
//    }


    //    для клиента   ------------------
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(customizer -> customizer
////                        .requestMatchers("/addcar").hasRole("CUSTOMER")
////                        .requestMatchers("/account/**").hasRole("CUSTOMER")
////                        .anyRequest().hasRole("CUSTOMER")
////                        .anyRequest().authenticated()
//                                .anyRequest().permitAll()
//                )
//                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//                .oauth2Login(Customizer.withDefaults())
//                .oauth2Client(Customizer.withDefaults())
//                .build();
//    }
//
//    @Bean
//    public OAuth2UserService<OidcUserRequest, OidcUser> oAuth2UserService() {
//        OidcUserService oidcUserService = new OidcUserService();
//        return userRequest -> {
//            OidcUser oidcUser = oidcUserService.loadUser(userRequest);
//            List<GrantedAuthority> authorities =
//                    Stream.concat(oidcUser.getAuthorities().stream(),
//                                    Optional.ofNullable(oidcUser.getClaimAsStringList("groups"))
//                                            .orElseGet(List::of)
//                                            .stream()
//                                            .filter(role -> role.startsWith("ROLE_"))
//                                            .map(SimpleGrantedAuthority::new)
//                                            .map(GrantedAuthority.class::cast))
//                            .toList();
//            return new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo(), "preferred_username");
//        };
//    }


}
