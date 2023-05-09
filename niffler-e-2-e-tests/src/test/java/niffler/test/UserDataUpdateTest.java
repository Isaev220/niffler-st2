package niffler.test;

import io.qameta.allure.AllureId;
import niffler.api.UserDataService;
import niffler.jupiter.annotation.ClasspathUser;
import niffler.model.UserJson;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDataUpdateTest {

    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .build();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .client(httpClient)
            .baseUrl("http://127.0.0.1:8089")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private final UserDataService userService = retrofit.create(UserDataService.class);

    @ValueSource(strings = {
            "testdata/anton.json",
            "testdata/emma.json"
    })
    @AllureId("107")
    @ParameterizedTest
    void userDataShouldBeUpdated(@ClasspathUser UserJson user) throws IOException {
        userService.updateUserInfo(user)
                .execute();
        UserJson afterUpdate = userService.currentUser(user.getUsername())
                .execute().
                body();
        Assertions.assertEquals(user.getSurname(), afterUpdate.getSurname());
        Assertions.assertEquals(user.getCurrency(), afterUpdate.getCurrency());
    }
}