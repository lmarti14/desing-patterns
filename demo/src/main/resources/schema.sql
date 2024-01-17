DROP TABLE IF EXISTS round CASCADE;
CREATE TABLE round (
    id SERIAL PRIMARY KEY,
    active BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS player CASCADE;
CREATE TABLE player (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    machine BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS game CASCADE;
CREATE TABLE game (
    id SERIAL PRIMARY KEY,
    active BOOLEAN NOT NULL,
    roundId INT,
    CONSTRAINT fk_game_round FOREIGN KEY (roundId) REFERENCES round(id)
);

DROP TABLE IF EXISTS card CASCADE;
CREATE TABLE card (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    points INT NOT NULL
);

DROP TABLE IF EXISTS game_player CASCADE;
CREATE TABLE game_player (
    game_id INT,
    player_id INT,
    PRIMARY KEY (game_id, player_id),
    CONSTRAINT fk_game_player_game FOREIGN KEY (game_id) REFERENCES game(id),
    CONSTRAINT fk_game_player_player FOREIGN KEY (player_id) REFERENCES player(id)
);

DROP TABLE IF EXISTS player_card CASCADE;
CREATE TABLE player_card (
    player_id INT,
    card_id INT,
    PRIMARY KEY (player_id, card_id),
    CONSTRAINT fk_player_card_player FOREIGN KEY (player_id) REFERENCES player(id),
    CONSTRAINT fk_player_card_card FOREIGN KEY (card_id) REFERENCES card(id)
);
