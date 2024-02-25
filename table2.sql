CREATE TABLE products (
                          productId BIGINT AUTO_INCREMENT PRIMARY KEY,
                          userId BIGINT,
                          itemName VARCHAR(255),
                          content TEXT,
                          price INT NOT NULL,
                          createDate DATETIME,
                          updateDate DATETIME
);



CREATE TABLE productStock (
                              productId BIGINT PRIMARY KEY,
                              stockCount INT
                              createDate DATETIME,
                              updateDate DATETIME
);