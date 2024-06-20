package boraldan.account.config_oauth2;

//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.stereotype.Component;
//
///**
// * пробный компонент для Rabbit MQ
// */
//@RequiredArgsConstructor
//@Component
//public class SecurityContextManager {
//
//    private final JwtDecoder jwtDecoder;
//
//    public void runWithSecurityContext(String token, Runnable action) {
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        Jwt jwt = jwtDecoder.decode(token);
//        JwtAuthenticationToken authentication = new JwtAuthenticationToken(jwt);
//        context.setAuthentication(authentication);
//        SecurityContextHolder.setContext(context);
//
//        try {
//            action.run();
//        } finally {
//            SecurityContextHolder.clearContext();
//        }
//    }
//}