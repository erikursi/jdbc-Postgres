CREATE TABLE users (
    login VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50),
    date TIMESTAMP
);
CREATE TABLE user_emails (
    login VARCHAR(50),
    email VARCHAR(50),
    FOREIGN KEY (login) REFERENCES users (login)
);

CREATE INDEX idx_users_login ON users (login);

DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO users (login, password, date) VALUES
        (CONCAT('user', i), CONCAT('password', i), NOW());
    END LOOP;
END $$;

DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO user_emails (login, email) VALUES
        (CONCAT('user', i), CONCAT('user', i, '@example.com'));
    END LOOP;
END $$;
