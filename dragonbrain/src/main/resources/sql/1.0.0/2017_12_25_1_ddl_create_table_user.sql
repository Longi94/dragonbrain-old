CREATE TABLE user (
  id       BIGINT       NOT NULL AUTO_INCREMENT,
  hash     VARCHAR(255) NOT NULL,
  root     BIT          NOT NULL,
  username VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);
