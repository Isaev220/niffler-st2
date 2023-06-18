package guru.qa.niffler.test;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.db.dao.NifflerUsersDAO;
import guru.qa.niffler.db.dao.NifflerUsersDAOHibernate;
import guru.qa.niffler.db.entity.Authority;
import guru.qa.niffler.db.entity.AuthorityEntity;
import guru.qa.niffler.db.entity.UserEntity;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class HomeworkTest extends BaseWebTest{

    private static final String TEST_PWD = "12345";
    LoginPage page = new LoginPage();
    private NifflerUsersDAO usersDAO = new NifflerUsersDAOHibernate();
    private UserEntity ue;

    @BeforeEach
    void createUserForTest() {
        ue = new UserEntity();
        ue.setUsername("valentin3");
        ue.setPassword(TEST_PWD);
        ue.setEnabled(true);
        ue.setAccountNonExpired(true);
        ue.setAccountNonLocked(true);
        ue.setCredentialsNonExpired(true);
        ue.setAuthorities(Arrays.stream(Authority.values()).map(
                a -> {
                    AuthorityEntity ae = new AuthorityEntity();
                    ae.setAuthority(a);
                    ae.setUser(ue);
                    return ae;
                }
        ).toList());
        usersDAO.createUser(ue);
    }

    @AfterEach
    void cleanUp() {
        usersDAO.removeUser(ue);
    }

    @Test
    void loginTest(){
        page.signIn(ue.getUsername(), TEST_PWD)
                .checkThatPageLoaded();

    }
    @Test
    void goToProfileTest(){
        page.signIn(ue.getUsername(), TEST_PWD)
                .getHeader()
                .goToProfilePage()
                .checkThatPageLoaded();

    }
    @Test
    void closeAvatarIconTest(){
        page.signIn(ue.getUsername(), TEST_PWD)
                .getHeader()
                .goToProfilePage()
                .checkThatPageLoaded()
                .CloseAvatarIconIsOnPage();

    }
    @Test
    void goToProfileAndCreateNewCategoryTest(){
        page.signIn(ue.getUsername(), TEST_PWD)
                .getHeader()
                .goToProfilePage()
                .addNewCategory("abracadabra");

    }
    @Test
    void goToProfileAndGoToMainTest(){
        page.signIn(ue.getUsername(), TEST_PWD)
                .getHeader()
                .goToProfilePage()
                .checkThatPageLoaded()
                .getHeader()
                .goToMainPage()
                .checkThatPageLoaded();

    }
    @Test
    void goToFriendsTest(){
        page.signIn(ue.getUsername(), TEST_PWD)
                .getHeader()
                .goToFriendsPage()
                .checkThatPageLoaded();

    }
    @Test
    void goToFriendsAndGoToMainTest(){
        page.signIn(ue.getUsername(), TEST_PWD)
                .getHeader()
                .goToFriendsPage()
                .checkThatPageLoaded()
                .getHeader()
                .goToMainPage()
                .checkThatPageLoaded();

    }
}
