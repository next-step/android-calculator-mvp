## MVP, UI 테스트

### 3단계 - 문자열 계산기
- [x] 입력 값을 공백 기준으로 분리
- [x] 입력 값이 null 또는 공백일 경우 IllegalArgumentException 발생
- [ ] 입력 기호가 사칙연산 기호가 아닌 경우 IllegalArgumentException 발생
- [ ] 덧셈 구현
- [ ] 뺄셈 구현
- [ ] 곱셈 구현
- [ ] 나눗셈 구현

### 2단계 - 모듈 분리
- [x] domain 모듈을 순수 코틀린 라이브러리로 분리
- [x] app 모듈은 domain 모듈에 의존