package models;
import com.peliculas.peliculasapp.infrastructure.entities.UserEntity;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
     void testUserBuilder() {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        UserEntity user = new UserEntity.Builder()
                .setUser_id(1)
                .setUsername("joan")
                .setPassword("elsabaleroexample")
                .setEmail("joanseso@example.com")
                .setCreatedAt(now)
                .setUpdateAt(now)
                .build();

        assertEquals(1, user.getUserId());
        assertEquals("joan", user.getUsername());
        assertEquals("elsabaleroexample", user.getPassword());
        assertEquals("joanseso@example.com", user.getEmail());
        assertEquals(now, user.getCreatedAt());
        assertEquals(now, user.getUpdatedAt());
    }

}
