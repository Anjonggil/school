# school

## 주의 사항

- 과제의 1차 검증을 위해 자체 툴로 자동 테스트를 진행하고 있습니다.  
  따라서 Request, Response   타입을 요구 사항과 동일하게 작성해주실 것을 부탁드립니다.  

---

```
학생의 점수를 관리하는 서버를 만드는 과제입니다.
아래 기능적 요구 사항과 비기능적 요구 사항을 잘 확인하고 제출 부탁드립니다.
```

## 비기능적 요구 사항

* 데이터베이스는 h2를 사용해서 개발해주세요.
* 서버 호스트는 localhost(127.0.0.1), 서버 포트는 8080으로 설정해주세요.
* 다른 사람이 읽기 좋은 코드로 개발해주세요.
* 개발 언어는 [Java](https://www.java.com/), [Kotlin](https://kotlinlang.org/) 중 하나를 사용해서 개발해주세요.
* Spring Boot를 사용해서 개발해주세요.
  * [Spring Boot 2.x](https://spring.io/projecåts/spring-boot) 이상 버전을 사용해서 개발해주세요.

## 기능적 요구 사항

### 공통

- 아래에 정의된 API 형식을 그대로 사용해서 개발해주세요.

- 필수 여부가 `required` 임에도 존재하지 않거나 조건을 지키지 못했을 경우 다음과 같은 `Response Body` 를 내려줍니다.

  ```json
  // BadRequest 400
  {
      "data": null,
      "error": {
          "code": "BAD_REQUEST_BODY",
          "message": "재량껏 작성"
      }
  }
  ```

---

### 학생 추가: POST /students

Request Body

```json
{
    "student": {
        "name": "aAzZ이ㅏㄱ10",
        "age": 19,
        "schoolType": "HIGH",
        "phoneNumber": "010-1234-5678"
    }
}
```

|    이름     |         설명         | 필수 여부 |  타입  |                조건                |
| :---------: | :------------------: | :-------: | :----: | :--------------------------------: |
|   student   |   추가할 학생 정보   | required  |   -    |                 -                  |
|    name     |   추가할 학생 이름   | required  | String |  1~16자, 한글/영어/숫자 포함 가능  |
|     age     |   추가할 학생 나이   | required  | Number |           8~19 값만 유효           |
| schoolType  |  추가할 학생 학교급  | required  | String | ELEMENTARY, MIDDLE, HIGH 값만 유효 |
| phoneNumber | 추가할 학생 전화번호 | required  | String |         000-0000-0000 형식         |

Response Body

- Created 201: 성공 

  ```json
  {
      "data": null,
      "error": null
  }
  ```
  
- BadRequest 400: `phoneNumber` 가 이미 존재하는 경우

  ```json
  {
      "data": null,
      "error": {
          "code": "ALREADY_EXIST_STUDENT",
          "message": "이미 존재하는 학생입니다. [${studentPhoneNumber}]"
      }
  }
  ```
  
  ```
  ${studentPhoneNumber}을 이미 존재하는 학생의 전화번호로 대체합니다.
  ```

주의 사항

```
학생 추가시 이미 존재하는지 확인하는 식별자는 phoneNumber이지만
실제로는 Number 타입의 식별자를 따로 두어야 합니다.
또한 식별자는 학생 추가시 자동 생성되어야 합니다.

학생 추가시 추가한 학생은 등록되어 있는 모든 과목을 수강하여야만 합니다.
```

---

### 학생 조회: GET /students

Request Body

```
-
```

Response Body

- OK 200: 성공

  ```json
  {
      "data": {
          "students": [
              {
                  "id": 1,
                  "name": "aAzZ이ㅏㄱ10",
                  "age": 19,
                  "schoolType": "HIGH",
                  "phoneNumber": "010-1234-5678"
              }
          ]
      },
      "error": null
  }
  ```

  ```
  만약 등록된 학생이 없는 경우 students가 null이 아닌 빈 리스트로 정의되어야 합니다.
  ```

---

### 학생 삭제: DELETE /students/{studentId}

Request Body

```json
-
```

|   이름    |        설명        | 필수 여부 |  타입   | 조건 |
| :-------: | :----------------: | :-------: | :-----: | :--: |
| studentId | 삭제할 학생 식별자 | required  | Numbner |  -   |

Response Body

- NoContent 204: 성공

  ```json
  -
  ```

주의 사항

```
삭제된 학생에게 할당된 점수는 함께 삭제합니다.
```

---

### 과목 추가: POST /subjects

Request Body

```json
{
    "subject": {
        "name": "수학"
    }
}
```

|  이름   |       설명       | 필수 여부 |  타입  |               조건               |
| :-----: | :--------------: | :-------: | :----: | :------------------------------: |
| subject | 추가할 과목 정보 | required  |   -    |                -                 |
|  name   | 추가할 과목 이름 | required  | String | 1~12자, 한글/영어/숫자 포함 가능 |

Response Body

- Created 201: 성공

  ```json
  {
      "data": null,
      "error": null
  }
  ```
  
- Bad Request 400: 추가하려는 과목 중 `name`이 이미 존재하는 경우

  ```json
  {
      "data": null,
      "error": {
          "code": "ALREADY_EXIST_SUBJECT",
          "message": "이미 존재하는 과목입니다. [${subjectName}]"
      }
  }
  ```
  
  ```
  ${subjectName}을 이미 존재하는 과목의 이름으로 대체합니다.
  ```

주의 사항

```
과목 추가시 이미 존재하는지 확인하는 식별자는 name이지만
실제로는 Number 타입의 식별자를 따로 두어야 합니다.
또한 식별자는 과목 추가시 자동 생성되어야 합니다.

과목 추가시 등록되어 있는 학생은 모두 추가한 과목을 수강해야만 합니다.
```

---

### 과목 조회: GET /subjects

Request Body

```json
-
```

Response Body

- OK 200: 성공

  ```json
  {
      "data": {
          "subjects": [
              {
                  "id": 1,
                  "name": "수학"
              }
          ]
      },
      "error": null
  }
  ```

  ```
  만약 등록된 과목이 없는 경우 subjects가 null이 아닌 빈 리스트로 정의되어야 합니다.
  ```

---

### 과목 삭제: DELETE /subjects/{subjectId}

Request Body

```json
-
```

|   이름    |        설명        | 필수 여부 |  타입  | 조건 |
| :-------: | :----------------: | :-------: | :----: | :--: |
| subjectId | 삭제할 과목 식별자 | required  | Number |  -   |

Response Body

- NoContent 204: 성공

  ```json
  -
  ```

주의 사항

```
삭제된 과목으로 점수를 가지고 있던 기록은 삭제합니다.
```

---

### 특정 학생, 특정 과목에 점수 할당: POST /students/{studentId}/subjects/{subjectId}/scores

Request Body

```json
{
    "score": 80
}
```

|   이름    |            설명             | 필수 여부 |  타입  |      조건       |
| :-------: | :-------------------------: | :-------: | :----: | :-------------: |
| studentId | 점수를 할당받을 학생 식별자 | required  | Number |        -        |
| subjectId | 점수를 할당받을 과목 식별자 | required  | Number |        -        |
|   score   |         할당할 점수         | required  | Number | 0~100 값만 유효 |

Response Body

- Created 201: 성공

  ```json
  {
      "data": null,
      "error": null
  }
  ```
  
- NotFound 404: studentId에 해당하는 학생을 찾을 수 없는 경우

  ```json
  {
      "data": null,
      "error": {
          "code": "STUDENT_NOT_FOUND",
          "message": "학생을 찾을 수 없습니다. [${studentId}]"
      }
  }
  ```

  ```
  ${studentId}를 찾지 못한 학생의 식별자로 대체합니다.
  ```

- NotFound 404: subjectId에 해당하는 과목을 찾을 수 없는 경우

  ```json
  {
      "data": null,
      "error": {
          "code": "SUBJECT_NOT_FOUND",
          "message": "과목을 찾을 수 없습니다. [${subjectId}]"
      }
  }
  ```
  
  ```
  ${subjectId}를 찾지 못한 과목의 식별자로 대체합니다.
  ```

---

### 특정 학생, 특정 과목의 점수 수정: PUT /students/{studentId}/subjects/{subjectId}/scores

Request Body

```json
{
    "score": 100
}
```

|   이름    |            설명             | 필수 여부 |  타입  |         조건         |
| :-------: | :-------------------------: | :-------: | :----: | :------------------: |
| studentId | 점수를 수정받을 학생 식별자 | required  | Number |          -           |
| subjectId | 점수를 수정받을 과목 식별자 | required  | Number |          -           |
|   score   |         수정한 점수         | required  | Number | 0~100 정수 값만 유효 |

Response Body

- NoContent 204: 성공

  ```json
  -
  ```
  
- NotFound 404: studentId에 해당하는 학생을 찾을 수 없는 경우

  ```json
  {
      "data": null,
      "error": {
          "code": "STUDENT_NOT_FOUND",
          "message": "학생을 찾을 수 없습니다. [${studentId}]"
      }
  }
  ```

  ```
  ${studentId}를 찾지 못한 학생의 식별자로 대체합니다.
  ```

- NotFound 404: subjectId에 해당하는 과목을 찾을 수 없는 경우

  ```json
  {
      "data": null,
      "error": {
          "code": "SUBJECT_NOT_FOUND",
          "message": "과목을 찾을 수 없습니다. [${subjectId}]"
      }
  }
  ```
  
  ```
  ${subjectId}를 찾지 못한 과목의 식별자로 대체합니다.
  ```

---

### 특정 학생, 특정 과목의 점수 삭제: DELETE /students/{studentId}/subjects/{subjectId}/scores

Request Body

```
-
```

|   이름    |           설명            | 필수 여부 |  타입  | 조건 |
| :-------: | :-----------------------: | :-------: | :----: | :--: |
| studentId | 점수를 삭제할 학생 식별자 | required  | Number |  -   |
| subjectId | 점수를 샂게할 과목 식별자 | required  |  Numb  |      |

Response Body

- NoContent 204: 성공

  ```json
  -
  ```
  
- NotFound 404: studentId에 해당하는 학생을 찾을 수 없는 경우

  ```json
  {
      "data": null,
      "error": {
          "code": "STUDENT_NOT_FOUND",
          "message": "학생을 찾을 수 없습니다. [${studentId}]"
      }
  }
  ```

  ```
  ${studentId}를 찾지 못한 학생의 식별자로 대체합니다.
  ```

- NotFound 404: subjectId에 해당하는 과목을 찾을 수 없는 경우

  ```json
  {
      "data": null,
      "error": {
          "code": "SUBJECT_NOT_FOUND",
          "message": "과목을 찾을 수 없습니다. [${subjectId}]"
      }
  }
  ```
  
  ```
  ${subjectId}를 찾지 못한 과목의 식별자로 대체합니다.
  ```

---

### 특정 학생의 평균 점수 조회: GET /students/{studentId}/average-score

Request Body

```
-
```

|   이름    |              설명              | 필수 여부 |  타입  | 조건 |
| :-------: | :----------------------------: | :-------: | :----: | :--: |
| studentId | 평균 점수를 조회할 학생 식별자 | required  | Number |  -   |

Response Body

- OK 200: 성공

  ```json
  {
      "data": {
          "averageScore": 100
          "subjects": [
              {
                  "id": 1,
                  "name": "수학",
                  "score": 100
              }
          ]
      },
      "error": null
  }
  ```

  ```
  subjects는 점수가 할당된 과목 리스트입니다.
  만약 학생에게 점수가 할당된 과목이 없는 경우 averageScore를 -1로 정의합니다.
  또한 과목이 없을 경우 subjects가 null이 아닌 빈 리스트로 정의해야합니다.
  
  과목은 있으나 점수가 할당되지 않았을 경우 score를 -1로 정의합니다.
  ```

- NotFound 404: studentId에 해당하는 학생을 찾을 수 없는 경우

  ```json
  {
      "data": null,
      "error": {
          "code": "STUDENT_NOT_FOUND",
          "message": "학생을 찾을 수 없습니다. [${studentId}]"
      }
  }
  ```
  
  ```
  ${studentId}를 찾지 못한 학생의 식별자로 대체합니다.
  ```

---

### 특정 과목에 대한 전체 학생들의 평균 점수 조회: GET /subjects/{subjectId}/average-score

Request Body

```json
-
```

|   이름    |              설명              | 필수 여부 |  타입  | 조건 |
| :-------: | :----------------------------: | :-------: | :----: | :--: |
| subjectId | 평균 점수를 조회할 과목 식별자 | required  | Number |  -   |

Response Body

- OK 200: 성공

  ```json
  {
      "data": {
          "averageScore": 50.0,
          "students": [
              {
                  "id": 1,
                  "name": "aAzZ이ㅏㄱ10",
                  "score": 50
              }
          ]
      },
      "error": null
  }
  ```
  ```
  students는 subjectId 과목에 점수가 할당된 학생 리스트입니다.
  subjectId에 해당하는 과목에 점수가 할당된 학생이 없는 경우 students를 null이 아닌 빈 리스트로 정의해야합니다.
  또한 특정 학생이 subjectId에 해당하는 과목에 점수가 할당되지 않았을 경우 averageScore를 -1로 정의합니다.
  ```
  
- NotFound 404: subjectId에 해당하는 과목을 찾을 수 없는 경우

  ```json
  {
      "data": null,
      "error": {
          "code": "SUBJECT_NOT_FOUND",
          "message": "과목을 찾을 수 없습니다. [${subjectId}]"
      }
  }
  ```
  
  ```
  ${subjectId}를 찾지 못한 과목의 식별자로 대체합니다.
  ```

---

## 제출 방법

* README에 실행 방법 및 코드 및 구조에 대한 개요를 포함해 주세요.
* 디비 스키마에 대해서도 제출해 주세요. (ERD 혹은 DDL)


---
