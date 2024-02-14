CREATE TABLE Users (
                       userId BIGINT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       profileImageUrl VARCHAR(255) NOT NULL,
                       greeting VARCHAR(255) NOT NULL,
                       createDate DATETIME,
                       updateData DATETIME,
                       verified BOOLEAN NOT NULL DEFAULT FALSE,
                       verificationToken VARCHAR(255),
                       role VARCHAR(255),
                       CONSTRAINT unique_email UNIQUE (email)
);
CREATE TABLE Posts (
                       postsId BIGINT AUTO_INCREMENT PRIMARY KEY,
                       userId BIGINT NOT NULL,
                       title VARCHAR(255) NOT NULL,
                       content TEXT NOT NULL,
                       createDate DATETIME ,
                       FOREIGN KEY (userId) REFERENCES Users(userId)
);


CREATE TABLE Comment (
                         commentId BIGINT AUTO_INCREMENT PRIMARY KEY,
                         content VARCHAR(200) NOT NULL,
                         userId BIGINT,
                         postsId BIGINT,
                         createDate DATETIME,
                         FOREIGN KEY (userId) REFERENCES Users(userId),
                         FOREIGN KEY (postsId) REFERENCES Posts(postsId)
);


CREATE TABLE Follower (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          fromUserId BIGINT,
                          toUserId BIGINT,
                          createDate DATETIME,
                          UNIQUE KEY Follower (fromUserId, toUserId),
                          FOREIGN KEY (fromUserId) REFERENCES Users(userId),
                          FOREIGN KEY (toUserId) REFERENCES Users(userId)
);


CREATE TABLE PostsLikes (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            userId BIGINT NOT NULL,
                            activityId BIGINT NOT NULL,
                            status BOOLEAN NOT NULL DEFAULT TRUE,
                            createDate DATETIME,
                            FOREIGN KEY (userId) REFERENCES Users(userId),
                            FOREIGN KEY (activityId) REFERENCES Posts(postsId)
);


CREATE TABLE NewsFeed (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          userId BIGINT NOT NULL,
                          activityId BIGINT,
                          type VARCHAR(20) NOT NULL,
                          createDate DATETIME,
                          FOREIGN KEY (userId) REFERENCES Users(userId),

);
