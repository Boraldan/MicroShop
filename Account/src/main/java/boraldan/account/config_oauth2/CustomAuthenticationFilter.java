package boraldan.account.config_oauth2;

/**
 *  фильтр для перехвата запросов на предмет содержания JWT и обработки ошибок на более низком уровне
 */


//@Component
//@RequiredArgsConstructor
//public class CustomAuthenticationFilter extends OncePerRequestFilter {
//
//    private final BasicAuthenticationEntryPoint authenticationEntryPoint;
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        try {
//            // Ваша логика проверки наличия и валидности токена доступа здесь
//            if (/* токен недействителен или отсутствует */) {
//                // Перенаправляем пользователя на страницу входа
//                response.sendRedirect("/login");
//            } else {
//                filterChain.doFilter(request, response);
//            }
//        } catch (AuthenticationException authException) {
//            authenticationEntryPoint.commence(request, response, authException);
//        }
//    }

//  еще вариант --------------------

//@Override
//protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//        FilterChain filterChain) throws ServletException, IOException {
//        // Получаем аутентификационный объект из контекста безопасности
//        BearerTokenAuthenticationToken authentication =
//        (BearerTokenAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//
//        // Проверяем, есть ли аутентификационный объект и валиден ли токен доступа
//        if (authentication != null && authentication.isAuthenticated()) {
//        // Если токен доступа валиден, продолжаем выполнение цепочки фильтров
//        filterChain.doFilter(request, response);
//        } else {
//        // Если токен доступа недействителен или отсутствует, перенаправляем пользователя на страницу входа
//        response.sendRedirect("/login");
//        }
//        }


//}