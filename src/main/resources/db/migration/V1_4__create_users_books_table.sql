CREATE TABLE IF NOT EXISTS users_books (

    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id int,
    INDEX user_ind (user_id),
    FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    book_id int,
    INDEX book_ind (book_id),
    FOREIGN KEY (book_id)
        REFERENCES books(id)
        ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=UTF8;