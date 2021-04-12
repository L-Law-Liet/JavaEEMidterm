CREATE TABLE IF NOT EXISTS books (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` varchar(200),
    `author` varchar(200)
    ) ENGINE=InnoDB DEFAULT CHARSET=UTF8;