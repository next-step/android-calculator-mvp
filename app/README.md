# Step3(2023-07-29) #
    * [x] 사용자가 입력한 문자열 값에 따라 사칙 연산을 수행할 수 있는 계산기 구현
    * 피드백
        * [x] 연산자로 시작 or 종료인 경우 테스트 코드 추가
        * [x] List<String>.toCalculate() -> DataClass.toCalculate() (리스트의 경우 협업시 오인의 소지가 발생)
        * [x] List<String>.toCalculate() -> Operators::class.sealedSubclasses (기존 when으로 판별의 경우 연산 종류 증가시 해당 분기도 판별해야 하므로)
        * [x] README.md 파일 추가

# Step2(2023-07-26) #
    * [x] domain 모듈 분리
    * 피드백
        * [x] domain 모듈은 순수 코틀린 모듈
        * [x] JavaVersion.VERSION_1_7 -> JavaVersion.VERSION_1_8

# Step1(2023-07-25) #
    * [x] 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다.


