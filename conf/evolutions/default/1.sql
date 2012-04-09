# --- First database schema
# --- !Ups
CREATE TABLE myuser (
  id                            INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  email                         VARCHAR(255) NOT NULL,
  password                      VARCHAR(255) NOT NULL,
  fullname                      VARCHAR(255) ,
  isAdmin                       BOOLEAN NOT NULL
);
CREATE TABLE posts(
  title                         VARCHAR(255) NOT NULL,
  posted                        DATE NOT NULL,
  content                       VARCHAR(2550) NOT NULL,
  authorId                      INT NOT NULL,
  FOREIGN KEY                   (authorId) REFERENCES myuser(id)
);

# --- !Downs
DROP TABLE IF EXISTS myuser;
DROP TABLE IF EXISTS posts;