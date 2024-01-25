INSERT INTO user (username, password, email, created_at, updated_at) VALUES
    ('joan', 'joan123', 'joanseso@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('ali', 'ali123', 'aliseso@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO content (title, release_date, genre, content_type) VALUES
    ('rapido y furiosos', "2003-02-21", "accion", "movie"),
    ('rapido y furiosos 9', "2022-05-22", "accion", "movie");

INSERT INTO review (user_id, content_id, rating, coment, created_at, updated_at) VALUES
    (1, 1, 9.50, "tremenda peli", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 2, 5.0, "una kkkkk", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
