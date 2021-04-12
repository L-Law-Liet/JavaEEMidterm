CREATE TABLE IF NOT EXISTS users (

    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fullname varchar(200),
    email varchar(200),
    password varchar(200),

    role_id int,
    INDEX role_ind (role_id),
    FOREIGN KEY (role_id)
    REFERENCES roles(id)
    ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=UTF8;