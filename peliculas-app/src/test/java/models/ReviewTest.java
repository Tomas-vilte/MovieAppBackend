package models;
import com.peliculas.peliculasapp.domain.models.Content;
import com.peliculas.peliculasapp.domain.models.Review;
import com.peliculas.peliculasapp.domain.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

public class ReviewTest {

    @Test
    void testReviewBuilder() {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Content content = new Content.Builder()
                .setContentId(5)
                .build();


        User user = new User.Builder()
                .setUser_id(1)
                .build();

        Review review = new Review.Builder()
                .setUser(user)
                .setContent(content)
                .setRating(4.5f)
                .setComment("Buena peli")
                .setCreatedAt(now)
                .setUpdatedAt(now)
                .build();

        assertNotNull(review.getId());
        assertEquals(user, review.getUser());
        assertEquals(content, review.getContent());
        assertEquals(4.5f, review.getRating(), 0.001);
        assertEquals("Buena peli", review.getComment());
        assertEquals(now, review.getCreatedAt());
        assertEquals(now, review.getUpdatedAt());
    }
}
