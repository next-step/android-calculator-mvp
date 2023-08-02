# Step5(2023-08-02) #
    * [x] 이전 단계의 기능 요구 사항을 만족해야 한다.
    * [x] MVP 패턴으로 구현한다.
    * [x] Presenter의 모든 로직에 대한 단위 테스트를 구현한다.

# Step4(2023-07-31) #
    * [x] 사용자가 클릭한 값에 따라서 사칙 연산을 수행하는 계산기 구현 및 테스트 코드 추가
        * [x] 사용자가 입력한 숫자를 화면에 보여줘야 한다.
        * [x] 숫자를 연속해서 입력한 경우에는 연속된 숫자를 보여줘야 한다. 
        * [x] 숫자를 입력하지 않은 경우, 연산자 +, -, ×, ÷ 버튼을 누르면 화면에 아무런 변화가 없어야 한다.
        * [x] 숫자를 입력한 경우, 연산자 +, -, ×, ÷ 버튼을 누르면 해당 기호가 화면에 보여야 한다.
        * [x] 입력된 수식이 없을 때, 사용자가 지우기 버튼을 누르면 화면에 아무런 변화가 없어야 한다.
        * [x] 입력된 수식이 있을 때, 지우기 버튼을 누르면 마지막으로 입력된 값이 지워져야 한다.
        * [x] 입력된 수식이 완전할 때, = 을 누르면 계산한 결과가 나와야 한다.
        * [x] 입력된 수식이 완전하지 않을 때, = 을 누르면 토스트 메세지가 나와야 한다.
    * 피드백
        * [x] runCatching 로직 함수로 추출
        * [x] operands 변수명 수정 & private set 적용
        * [x] removeOperands() 메서드명 수정(마지막 값을 지우는 메서드인데, 전체를 지우는 메서드로 잘못 해석할 여지 존재)
        * [x] 버튼 ClickListener 중복되는 코드 간결화(버튼 클릭의 경우 로직이 같으므로 코드 간결화 필요)
    * 2차 피드백
        * [x] 테스트 코드 내부 CalculatorExpression 변수명 수정
        * [x] 요구사항에 따른 테스트 코드 수정(숫자 미 입력시 사칙연산 클릭시 반응X)
        * [x] Expressions 클래스 내부 변수명, 함수명, 파라미터 기능에 맞도록 네이밍 수정
        * [x] View.OnClickListener 내부 Button에 대해서 의존적이던 내용 수정
    * 3차 피드백
        * [x] Expression이 Calculator에 대한 의존성을 가지던 문제 수정
        * [x] View.OnClickListener -> 기능 함수로 수정

# Step3(2023-07-29) #
    * [x] 사용자가 입력한 문자열 값에 따라 사칙 연산을 수행할 수 있는 계산기 구현
    * 피드백
        * [x] 연산자로 시작 or 종료인 경우 테스트 코드 추가
        * [x] List<String>.toCalculate() -> DataClass.toCalculate() (리스트의 경우 협업시 오인의 소지가 발생)
        * [x] List<String>.toCalculate() -> Operators::class.sealedSubclasses (기존 when으로 판별의 경우 연산 종류 증가시 해당 분기도 판별해야 하므로)
        * [x] README.md 파일 추가
    * 2차 피드백
        * [x] CalculationExpression 클래스 -> Object 수정(함수만 사용하므로)
        * [x] operatorItems 데이터 클래스 제거 & toCalculate 파라미터 추가


# Step2(2023-07-26) #
    * [x] domain 모듈 분리
    * 피드백
        * [x] domain 모듈은 순수 코틀린 모듈
        * [x] JavaVersion.VERSION_1_7 -> JavaVersion.VERSION_1_8

# Step1(2023-07-25) #
    * [x] 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다.


