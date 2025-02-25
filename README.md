## 앱 실행
> 💡 docker, docker-compose 가 설치되어 있고 실행 가능한 환경이어야 합니다.

1. 프로젝트 clone

2. clone 받은 폴더로 이동하여 아래 명령어 입력

  ```bash
  docker-compose up -d
  ```
3. 입력 시 도커 이미지와 컨테이너 생성 및 실행

* mysql: mysql 5.7 db 컨테이너

  DB 설치 후 sql/initdb.d/ddl.sql 자동 실행하여 ddd_basic 데이터베이스 내 테이블 생성

* app : 프로젝트가 실행되는 컨테이너

  jdk17과 maven 이 설치되며, 프로젝트가 빌드되고 실행.
## 개발환경
* Language : Java(JDK17)
* Framework : Spring Boot(3.3.5), Spring Data Jpa, Spring Security
* Database : MySQL 5.7
