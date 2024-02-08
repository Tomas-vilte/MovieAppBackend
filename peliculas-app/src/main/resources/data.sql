INSERT INTO user_entity (username, password, email, created_at, updated_at) VALUES
    ('joan', 'joan123', 'joanseso@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('ali', 'ali123', 'aliseso@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('alexis', 'alexis123', 'alexisseso@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('emilse', 'emilse123', 'emilseseso@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('belen', 'belen123', 'belenseso@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('lucas', 'lucas123', 'lucasseso@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO content_entity (title, release_date, genre, content_type) VALUES
    ('rapido y furiosos', "2003-02-21", "accion", "movie"),
    ('rapido y furiosos 9', "2022-05-22", "accion", "movie"),
    ('casados con hijos', "2005-04-22", "comedia", "serie");

INSERT INTO review_entity (user_id, content_id, rating, comment, created_at, updated_at) VALUES
    (1, 1, 9.50, "tremenda peli", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 1, 5.0, "una kkkkk", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 1, 8.0, "seso", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 2, 9.0, "buena peli", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 3, 10.0, "las mejor serie", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 3, 9.1, "aguante racing", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 3, 10, "recuerdos de chico", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 3, 9.4, "vuelvan!!", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 3, 8.5, "cokiiii", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 3, 8.5, "que buenos actores", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
