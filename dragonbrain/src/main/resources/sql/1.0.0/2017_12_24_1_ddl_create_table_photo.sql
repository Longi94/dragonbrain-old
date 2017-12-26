CREATE TABLE photo (
  id       BIGINT       NOT NULL AUTO_INCREMENT,
  date     DATE,
  device   VARCHAR(255),
  location VARCHAR(255),
  path     VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);
