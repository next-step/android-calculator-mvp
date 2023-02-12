## MVP, UI 테스트

### 4단계 - UI 테스트
- [x] 1차 피드백 반영
- [x] 입력된 피연산자가 없는 경우 숫자 버튼 누를 경우 해당 숫자가 화면에 표시
- [x] 입력된 피연산자가 있는 경우 기존 숫자 뒤에 해당 숫자가 화면에 표시
- [x] 입력된 피연산자가 없을 경우 연산자 버튼 누르면 화면에 아무런 변화 없도록 처리
- [x] 입력된 피연산자가 있을 경우 연산자 버튼 누르면 화면에 해당 연산자 표시
- [x] 입력된 수식이 없을 경우 지우기 버튼 누르면 화면에 아무런 변화 없도록 처리
- [x] 입력된 수식이 있을 경우 지우기 버튼 누르면 수식 마지막 연산자 또는 피연산자 삭제
- [x] 입력된 수식이 완전할 경우 = 버튼 누르면 수식의 결과 화면에 표시
- [x] 입력된 수식이 불완전할 경우 = 버튼 누르면 토스트 메시지 표시

### 3단계 - 문자열 계산기
- [x] 입력 값을 공백 기준으로 분리
- [x] 입력 값이 null 또는 공백일 경우 IllegalArgumentException 발생
- [x] 입력 기호가 사칙연산 기호가 아닌 경우 IllegalArgumentException 발생
- [x] 덧셈 구현
- [x] 뺄셈 구현
- [x] 곱셈 구현
- [x] 나눗셈 구현

### 2단계 - 모듈 분리
- [x] domain 모듈을 순수 코틀린 라이브러리로 분리
- [x] app 모듈은 domain 모듈에 의존