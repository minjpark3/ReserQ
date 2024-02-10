CREATE TABLE Users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       profileImageUrl VARCHAR(255) NOT NULL,
                       greeting VARCHAR(255) NOT NULL,
                       createDate DATETIME,
                       updateData DATETIME,
                       verified BOOLEAN DEFAULT FALSE,
                       verificationToken VARCHAR(255),
                       role VARCHAR(255)
);

CREATE TABLE Posts (
                       postsId BIGINT AUTO_INCREMENT PRIMARY KEY,
                       userId BIGINT NOT NULL,
                       title VARCHAR(255) NOT NULL,
                       content VARCHAR(255) NOT NULL,
                       createDate DATETIME,
                       updateData DATETIME
);
CREATE TABLE Comment (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         content VARCHAR(200) NOT NULL,
                         userId BIGINT,
                         postsId BIGINT,
                         createDate DATETIME,
                         FOREIGN KEY (postsId) REFERENCES Posts (postsId)
);

CREATE TABLE Follower (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          fromUserId BIGINT NOT NULL,
                          toUserId BIGINT NOT NULL,
                          createDate DATETIME,
                          UNIQUE KEY subscribe_uk (fromUserId, toUserId)
);


CREATE TABLE PostsLikes (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            postsId BIGINT,
                            userId BIGINT,
                            createDate DATETIME,
                            UNIQUE KEY likes_uk (postsId, userId),
                            FOREIGN KEY (postsId) REFERENCES Posts(postsId)
);