package boraldan.account.keycloak;

//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.jwt.Jwt;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//// класс конвертер из данных JWT в роли spring security
//public class KCRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
//
//    @Override
//    public Collection<GrantedAuthority> convert(Jwt jwt) {
//
//        // получаем доступ к разделу JSON
//        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
//
//        // если раздел JSON не будет найден - значит нет ролей
//        if (realmAccess == null || realmAccess.isEmpty()) {
//            return new ArrayList<>(); // пустая коллекция - нет ролей
//        }
//
//        // функц. код - для примера (кто уже возможно использовал его)
//        Collection<GrantedAuthority> returnValue = ((List<String>) realmAccess.get("roles"))
//                .stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//
//        return returnValue;
//    }
//
//}
