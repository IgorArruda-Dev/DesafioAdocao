CREATE TABLE animals
(
    `id`          BIGINT(20) not null AUTO_INCREMENT,
    `name`        VARCHAR(100) NOT NULL,
    `description` VARCHAR(255),
    `url_image`    VARCHAR(255),
    `category`    ENUM('CAT', 'DOG'),
    `birth_date`   DATETIME,
    `status`      ENUM('ADOPTED', 'AVAILABLE'),
    PRIMARY KEY (`id`)
)