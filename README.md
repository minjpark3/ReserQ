## 😀 ReserQ
 
SNS를 기반으로 사용자가 상품을 등록하고 상품의 재고관리를 효율적으로 할 수있는 프로그램입니다. 일반상품과 특정시간예약구매상품을 관리하여 사용자의 편리성을 증진시켰습니다.

## ❓  기능 엿보기 

### Tech Stack👉
![기술스택](https://github.com/user-attachments/assets/1a44b303-025a-4a14-89f5-7d0183bca19f)
### Architecture👉
![reservesite](https://github.com/user-attachments/assets/d0116be3-9c91-4050-8974-fa4c6ca12867)
### ERD👉
![reserqERD](https://github.com/user-attachments/assets/ae8fcb8c-bcbf-4097-a4fa-948cb654bba7)
## 🛠Step to run 
window 환경에서 구현 및 실행되었습니다.
Local server
1. github에서 해당 프로젝트의 repository를 clone합니다.

       $ git clone https://github.com/minjpark3/ReserQ.git

2. env 파일을 root directory에 생성 후, MYSQL, Redis와 연동을 위한 정보를 저장합니다.
                    
        MYSQL_DATABASE='{local database name}'           
        MYSQL_ROOT_USER='{local database user}'         
        MYSQL_ROOT_PASSWORD='{local database password}'         
        LOCAL_DB_PORT='{local database port}'

        LOCAL_REDIS_HOST_NAME='{local redis host name}'               
        LOCAL_REDIS_PORT='{local redis port}'

3. application-local.yml 파일을 classpath에 생성 후, local 프로젝트 환경 정보를 저장합니다.  

        spring: 
        datasource: 
        url: jdbc:mysql://localhost:{local database port}/{local database name} 
        username: {local database user} 
        password: {local database password} 
        driver-class-name: com.mysql.cj.jdbc.Driver

        redis: host: {local redis host name} port: {local redis port}

        ...
        server: port: {local server port}

        ... 

4. docker에 database 이미지를 생성하고 컨테이너화 합니다. 

        $ sudo docker-compose up -d
  
5. 프로젝트를 빌드합니다.

        $ ./gradlew build

6.local server를 실행합니다.
      
      $ ./gradlew bootRun --spring.profiles.active=dev 
7.local server를 종료하고 database 컨테이너도 종료합니다.

      $ (ctrl + v) Y $ sudo docker-compose down


## Author
All developments : 🙋‍♀️ Minjeong Park
