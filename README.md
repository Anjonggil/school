# 학생성적관리 시스템

## 실행 방법

- git clone을 통해 project를 받습니다.
- scholApplication 실행
- [postman](https://www.postman.com/) 혹은 [swagger](http://localhost:8080/swagger-ui/index.html#/)를 이용

__참고__ 
- 테스트의 편의성을 위해 내장 h2데이터베이스를 사용했습니다.
- 내장 데이터베이스 사용하지 않는다고 가정하에 h2데이터베이스를 local에 구축하여 개발했습니다.
- jpa 자동 테이블 생성 기능을 참고만 하여 테이블을 따로 생성하였습니다.   
- 테이블 자동 생성 시 뜻하지 않은 방향의 테이블이 생성될 위험성이 있다고 판단하였습니다.


## 개요
### 코드
- 해당 서비스의 경우 실시간 서비스의 형태를 띄고 있어 jpa를 사용했습니다.
- OSIV = off 옵션을 가정하여 service layer에서 Transaction을 반환하는 개발방식으로 진행되었습니다.
- 코드의 유지보수성을 생각하여 DTO와 Builder 패턴을 적극 활용했습니다.


### 구조
```bash
├─java
│  └─com
│      └─test
│          └─school
│              ├─common
│              │  ├─annotation
│              │  │  └─validator
│              │  └─error
│              ├─config
│              ├─controller
│              │  └─api
│              ├─domain
│              │  ├─entity
│              │  ├─request
│              │  └─response
│              ├─repository
│              └─service
│                  └─impl
└─resources

``` 

#### common(공통으로 사용되는 class를 모아두는 패키지)
| package | description |
|:--------|:--------:|
| annotation |custom annotation을 관리하는 패키지|
| error | custom error handlering을 위한 패키지 |

#### config(설정파일을 관리하는 패키지)

#### controller(controller class를 관리하는 패키지)
| package | description |
|:--------|:--------:|
| api |Rest Controller를 관리하는 패키지|

#### domain(Entity,Dto class를 관리하는 패키지)
| package | description |
|:--------|:--------:|
| request |request Dto를 관리하는 패키지|
| response |response Dto를 관리하는 패키지|
| entity |entity를 관리하는 패키지|

#### repository(Repository class를 관리하는 패키지)

#### service(Service class를 관리하는 패키지)
| package | description |
|:--------|:--------:|
| impl |service 인터페이스의 구현체를 관리하는 패키지|

## ERD
![image](https://user-images.githubusercontent.com/42487599/169690068-76033fbc-bb5b-4528-a1ec-7aea4b7844a0.png)

####table.STUDENT 

| Column | type | constraint | description |
|:--------|:--------:|:--------:|:--------:|
| student_id | bigint | primarykey,unique,notnull | 학생테이블 기본키 |
| age|integer|notNull|학생나이
| student_name|varchar(50)|notnull|학생이름|
| phone_number|varchar(15)|unique,notnull|전화번호|
| school_type|varchar(10)|notnull|학교급|

####table.SUBJECT 

| Column | type | constraint | description |
|:--------|:--------:|:--------:|:--------:|
| subject_id | bigint | primarykey,unique,notnull | 과목테이블 기본키 |
| subject_name|varchar(50)|unique,notnull|과목이름|


####table.SCORE 

| Column | type | constraint | description |
|:--------|:--------:|:--------:|:--------:|
| score_id | bigint | primarykey,unique,notnull | 테이블 기본키 |
| score|integer|notnull|점수|
| subject_id | bigint | foreign key,notnull | 과목아이디 |
| student_id | bigint | foreign key,notnull | 학생아이디 |







