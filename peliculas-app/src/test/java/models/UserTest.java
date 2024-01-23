package models;
import com.peliculas.peliculasapp.models.Users;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
     void testUserBuilder() {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Users user = new Users.Builder()
                .setUser_id(1)
                .setUsername("joan")
                .setPassword("elsabalero")
                .setEmail("joanseso@gmail.com")
                .setCreatedAt(now)
                .setUpdateAt(now)
                .build();

        assertEquals(1, user.getUser_id());
        assertEquals("joan", user.getUsername());
        assertEquals("elsabalero", user.getPassword());
        assertEquals("joanseso@gmail.com", user.getEmail());
        assertEquals(now, user.getCreated_at());
        assertEquals(now, user.getUpdated_at());
    }

}
