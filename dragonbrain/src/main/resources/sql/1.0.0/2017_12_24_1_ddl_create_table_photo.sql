CREATE TABLE photo (
  id        BIGINT       NOT NULL AUTO_INCREMENT,
  device    VARCHAR(255),
  location  VARCHAR(255),
  order_by  INTEGER      NOT NULL,
  path      VARCHAR(255) NOT NULL,
  timestamp DATETIME,
  PRIMARY KEY (id)
);
