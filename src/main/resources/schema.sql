CREATE TABLE IF NOT EXISTS app_user (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
    dob TIMESTAMP(6)
    );

CREATE TABLE post (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      description VARCHAR(255) NOT NULL,
                      user_id BIGINT,
                      CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES app_user(id)
);



