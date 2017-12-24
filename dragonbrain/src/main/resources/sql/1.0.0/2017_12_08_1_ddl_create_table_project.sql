CREATE TABLE project (
  id          BIGINT       NOT NULL AUTO_INCREMENT,
  description LONGTEXT,
  image       VARCHAR(255),
  name        VARCHAR(100) NOT NULL,
  source      VARCHAR(255),
  type        VARCHAR(50)  NOT NULL,
  url         VARCHAR(255),
  PRIMARY KEY (id)
);
