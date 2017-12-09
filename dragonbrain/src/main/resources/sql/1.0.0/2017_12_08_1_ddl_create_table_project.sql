CREATE TABLE project (
  id          BIGINT       NOT NULL,
  description LONGTEXT,
  image       VARCHAR(255),
  name        VARCHAR(100) NOT NULL,
  small       BIT          NOT NULL,
  source      VARCHAR(255),
  type        VARCHAR(50)  NOT NULL,
  url         VARCHAR(255),
  PRIMARY KEY (id)
);
