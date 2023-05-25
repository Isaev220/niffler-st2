package niffler.jupiter.extension;

import com.github.javafaker.Faker;
import niffler.db.dao.NifflerUsersDAO;
import niffler.db.dao.NifflerUsersDAOHibernate;
import niffler.db.dao.NifflerUsersDAOJdbc;
import niffler.db.dao.NifflerUsersDAOSpringJdbc;
import niffler.db.entity.Authority;
import niffler.db.entity.AuthorityEntity;
import niffler.db.entity.UserEntity;
import niffler.jupiter.annotation.GenerateRandomUserEntity;
import org.junit.jupiter.api.extension.*;

import java.util.Arrays;


public class GenerateRandomUserEntityExtension implements ParameterResolver, BeforeEachCallback, AfterEachCallback {
    public static ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace
            .create(GenerateRandomUserEntityExtension.class);




    static Faker faker = new Faker();
    public static String userPassword = faker.internet().password();

    @Override
    public void beforeEach(ExtensionContext context) {
        GenerateRandomUserEntity annotation = context.getRequiredTestMethod()
                .getAnnotation(GenerateRandomUserEntity.class);
        System.out.println(annotation.dbType().toString());
        NifflerUsersDAO usersDAO = annotation.dbType().equals(DBType.JDBC) ? new NifflerUsersDAOJdbc() :
                annotation.dbType().equals(DBType.SPRING) ? new NifflerUsersDAOSpringJdbc() : new NifflerUsersDAOHibernate();


            UserEntity createdUserEntity = new UserEntity();
            createdUserEntity.setUsername(
                    (faker.name().username()));
            createdUserEntity.setPassword(
                    (userPassword));
            createdUserEntity.setEnabled(true);
            createdUserEntity.setAccountNonExpired(true);
            createdUserEntity.setAccountNonLocked(true);
            createdUserEntity.setCredentialsNonExpired(true);
            createdUserEntity.setAuthorities(Arrays.stream(Authority.values()).map(
                    a -> {
                        AuthorityEntity ae = new AuthorityEntity();
                        ae.setAuthority(a);
                        ae.setUser(createdUserEntity);
                        return ae;
                    }
            ).toList());
            usersDAO.createUser(createdUserEntity);
            createdUserEntity.setPassword(userPassword);
            context.getStore(NAMESPACE).put("userEntity", createdUserEntity);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(UserEntity.class);
    }

    @Override
    public UserEntity resolveParameter(ParameterContext parameterContext,
                                       ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get("userEntity", UserEntity.class);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        UserEntity ue = context.getStore(NAMESPACE).get("userEntity", UserEntity.class);
        GenerateRandomUserEntity annotation = context.getRequiredTestMethod()
                .getAnnotation(GenerateRandomUserEntity.class);
        NifflerUsersDAO usersDAO = annotation.dbType().equals(DBType.JDBC) ? new NifflerUsersDAOJdbc() :
                annotation.dbType().equals(DBType.SPRING) ? new NifflerUsersDAOSpringJdbc() : new NifflerUsersDAOHibernate();

            usersDAO.removeUser(ue);

    }
}