package boraldan.account.keycloak;


import boraldan.entitymicro.account.dto.PersonDTO;
import boraldan.entitymicro.account.dto.SingUpDto;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.AbstractUserRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class KeycloakService {

    @Value("${keycloak.auth-server-url}")
    private String serverURL;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientID;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    private static Keycloak keycloak; // сылка на единственный экземпляр объекта KC
    private static RealmResource realmResource; // доступ к API realm
    private static UsersResource usersResource;   // доступ к API для работы с пользователями

    @PostConstruct
    public Keycloak initKeycloak() {
        if (keycloak == null) {
            keycloak = KeycloakBuilder.builder()
                    .realm(realm)
                    .serverUrl(serverURL)
                    .clientId(clientID)
                    .clientSecret(clientSecret)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
//                    .resteasyClient(new ResteasyClientBuilderImpl().connectionPoolSize(10).build())
                    .build();
            realmResource = keycloak.realm(realm);
            usersResource = realmResource.users();
        }
        return keycloak;
    }

    // создание пользователя для KC
    public Response createKeycloakUser(SingUpDto singUpDto) {

        // данные пароля - специальный объект-контейнер CredentialRepresentation
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(singUpDto.getPassword());

        // данные пользователя (можете задавать или убирать любые поля - зависит от требуемого функционала)
        // специальный объект-контейнер UserRepresentation
        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(singUpDto.getUsername());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setEmail(singUpDto.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);
        kcUser.setGroups(Collections.singletonList("Customer"));

        // Установка дополнительных полей, которые заданы как атрибуты keycloak
//        kcUser.singleAttribute("company", userDTO.getCompany());

        //  можно выбрать любой realm в который будет сохраняться User
        //  Response response = keycloak.realm(realm).users().create(kcUser);

        // вызов KC (всю внутреннюю кухню за нас делает библиотека - формирует REST запросы, заполняет параметры и пр.)
        return usersResource.create(kcUser);
    }





    // поиск уникального пользователя
    public UserRepresentation getUserById(String userId) {
        return usersResource.get(userId).toRepresentation();
    }

    // поиск уникального пользователя
    public List<String> getAllUser() {
        return usersResource.list().stream().map(AbstractUserRepresentation::getUsername).toList();
    }

    public  List<UserRepresentation> getListByUsername(String username) {
       return  usersResource.searchByUsername(username, true);
    }

    public  List<UserRepresentation> getListByEmail(String email) {
        return  usersResource.searchByEmail(email, true);
    }

    public Integer checkByUsernameAndEmail(String username, String email){
        return usersResource.count(null, null, username, email);
    }


    // поиск пользователя по любым атрибутам (вхождение текста)
    public List<UserRepresentation> getByAttributes(String text) {
        return usersResource.searchByAttributes(text);
    }

    public void updateKeycloakUser(PersonDTO personDTO) {
        // данные пароля - специальный объект-контейнер CredentialRepresentation
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(personDTO.getPassword());

        // какие поля обновляем
        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(personDTO.getUsername());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setEmail(personDTO.getEmail());

        UserResource uniqueUserResource = usersResource.get(personDTO.getId());
        uniqueUserResource.update(kcUser);
    }

    // добавление роли пользователю
    public void addRoles(String userId, List<String> roles) {

        // список доступных ролей в Realm
        List<RoleRepresentation> kcRoles = new ArrayList<>();

        // преобразуем тексты в спец. объекты RoleRepresentation, который понятен для KC
        for (String role : roles) {
            RoleRepresentation roleRep = realmResource.roles().get(role).toRepresentation();
            kcRoles.add(roleRep);
        }

        // получаем пользователя
        UserResource uniqueUserResource = usersResource.get(userId);

        // и добавляем ему Realm-роли (т.е. роль добавится в общий список Roles)
        uniqueUserResource.roles().realmLevel().add(kcRoles);

    }

    // удаление пользователя для KC
    public void deleteKeycloakUser(String userId) {

        // получаем пользователя
        UserResource uniqueUserResource = usersResource.get(userId);
        uniqueUserResource.remove();

    }


    // данные о пароле
    private CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false); // не нужно будет менять пароль после первого входа
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }


}
