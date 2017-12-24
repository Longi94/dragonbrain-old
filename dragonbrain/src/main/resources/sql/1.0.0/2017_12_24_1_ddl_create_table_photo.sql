CREATE TABLE photo (
  id        BIGINT NOT NULL AUTO_INCREMENT,
  device    VARCHAR(255),
  location  VARCHAR(255),
  path      VARCHAR(255),
  timestamp DATETIME,
  PRIMARY KEY (id)
);
