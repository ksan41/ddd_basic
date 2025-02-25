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


## 프로젝트 소개
근래 DDD 개발론에 관심을 갖고 학습하기 시작했다.

그러면서 보게 된 '도메인 주도 설계 철저 입문'이라는 책을 보게 됐다.

책의 예제는 C# 으로 작성되었으며 사용자 그룹이라는 주제로 실무에 가까운 내용들을 담고있었다.

이를 JPA 도 연습할 겸 Java 프로젝트로 만들어보기로 했다. 

코드가 단편적으로 보여져있어 생략된 내용이나 직접 생각해야 할 부분들을 추가하거나 재해석하여 프로젝트를 구성하였다.




<img src="https://github.com/user-attachments/assets/e707b929-1e8e-4117-8eed-84afa2ae632a" width="150" height="200">

<도메인 주도 설계 철저 입문 - 나루세 마사노부>

## 개발환경
* Language : Java(JDK17)
* Framework : Spring Boot(3.3.5), Spring Data Jpa, Spring Security
* Database : MySQL 5.7
