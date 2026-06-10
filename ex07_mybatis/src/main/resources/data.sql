-- data.sql

-- 사용자 초기 mock 데이터
INSERT INTO users (email, nickname) VALUES
('user1@example.com', 'user1'),
('user2@example.com', 'user2'),
('user3@example.com', 'user3');

-- 게시글 초기 Mock 데이터
INSERT INTO posts (user_id, title, content) VALUES
(1, 'SPRING스터디 모집', '함께 공부하실 분 찾습니다.'),
(1, 'SPRING스터디 추가모집', '사람이 부족해요 지원좀요.'),
(2, 'mybatis 공부 어떻게 하시나요', '봐도봐도 모르겠는데 조언 부탁드립니다. 다들 어떻게 공부하시는지...');



