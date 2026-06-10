-- schema.sql(테이블 정의)
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, --DB에서 자동생성, not null 없어도 됨
  email VARCHAR(100) NOT NULL UNIQUE, --자바에서 
  nickname VARCHAR(50) NOT NULL,-- 자바에서 
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- 자동생성
);

CREATE TABLE posts(
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, --DB에서 자동생성, not null 없어도 됨
  user_id BIGINT NOT NULL,
  title VARCHAR(255) NOT NULL,
  content TEXT,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 자동생성
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);