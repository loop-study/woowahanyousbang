# 우아한유스방 코딩 과제 : [와이어밸리](https://github.com/wirebarley/apply/blob/master/coding_test.md) 의 환율계산기

## 문제
- 송금국가는 미국으로 고정입니다. 통화는 미국달러(USD)입니다.
- 수취국가는 한국, 일본, 필리핀 세 군데 중 하나를 select box로 선택합니다. 각각 통화는 KRW, JPY, PHP 입니다.
- 수취국가를 선택하면 아래 환율이 바뀌어나타나야 합니다. 환율은 1 USD 기준으로 각각 KRW, JPY, PHP의 대응 금액입니다.
- 송금액을 USD로 입력하고 Submit을 누르면 아래 다음과 같이 수취금액이 KRW, JPY, PHP 중 하나로 계산되어서 나와야 합니다.
- 환율과 수취금액은 소숫점 2째자리까지, 3자리 이상 되면 콤마를 가운데 찍어 보여줍니다. 예를 들어 1234라면 1,234.00으로 나타냅니다.
- 환율정보는 https://currencylayer.com/ 의 무료 서비스를 이용해서 실시간으로 가져와야 합니다. 웹 서버가 시작될 때 한번 가져와서 계속 사용해도 되고, 매번 새로운 환율 정보를 가져와도 됩니다.
  - 환율을 미리 계산해서 html/javascript 안에 넣어두고 수취국가를 변경할 때마다 이를 자바스크립트로 바로 가져와서 보여줘도 좋고,
  - 혹은 매번 수취국가를 선택/변경할 때마다 API로 서버에 요청을 해서 환율정보를 가져오게 해도 좋습니다. 
- Submit을 누르면 선택된 수취국가와 그 환율, 그리고 송금액을 가지고 수취금액을 계산해서 하단에 보여주면 됩니다. API를 이용해서 서버에서 계산해서 뿌려도 되고 자바스크립트로 미리 가져온 환율을 계산해서 수취금액을 보여줘도 되고 Submit 버튼으로 폼을 submit해서 화면을 새로 그려도 됩니다.
- 수취금액을 입력하지 않거나, 0보다 작은 금액이거나 10,000 USD보다 큰 금액, 혹은 바른 숫자가 아니라면 “송금액이 바르지 않습니다"라는 에러 메시지를 보여줍니다. 메시지는 팝업, 혹은 하단에 빨간 글씨로 나타나면 됩니다.

## 요구사항 정리
### 환율 정보
- 환율정보를 보여준다.
  - 환율정보는 https://currencylayer.com/ 에서 받아온다.
  - 외부 인프라에 문제가 생길 시 에러처리를 해야한다. 
- 환율 정보는 소수점 2자리, 3자리마다 콤마를 찍는다. 
- [참고사항] 환율 정보를 가져오는 방법은 2가지다.
  1. 애플리케이션 시작할 때 받아온 정보를 재활용한다.
  2. 수취국가 선택 시 매번 재요청한다. 

### 환전
- 송금국가는 미국달러(USD) 고정한다.
- 수취국가를 선택한다.
  - 한국(KRW), 일본(JPY), 필리핀(PHP) 3가지가 있다. 
  - 선택된 국가에 따라 환율 정보가 변경된다. 
- 송금 금액을 입력한다.
  - 숫자만 입력한다. 
  - 송금 금액의 범위는 0 ~ 10,000 이다.  
  - 송금 금액이 잘못되면 "송금액이 바르지 않습니다." 에러 메시지를 보여준다.
- 수취 금액은 소수점 2자리, 3자리마다 콤마를 찍는다.
  
- [참고사항] 계산 방법은 여러 방법 중에서 하나를 선택한다.
  1. API 이용해서 서버에서 계산해서 가져온다. 
  2. 자바스크립트로 계산해서 보여준다.
  3. submit 버튼을 이용해서 새로 페이지를 그려준다. 
  
## 구현 사항

### 화폐 (Currency)
- [x] 화폐를 생성한다.
  - [x] 화폐명을 갖는다.
  - [x] 화폐명은 3글자다.
    - [x] 잘못된 화폐명은 에러를 반환한다. 
  - [x] 환율을 갖는다.  
    - [x] 잘못된 환율은 에러를 반환한다.
    - [x] 환율은 0 이 될 수 없다.
    - [x] 환율은 0보다 작을 순 없다. 
- [x] 환전 금액을 계산한다.
  - [x] 송금액을 받는다.
  

## 코드 피드백 정리 
1. 환율 정보 업데이트 주기(하루 혹은 1분 단위)에 따른 대응이 안되어있다.
2. JPA에서 리스트 저장은 saveAll 혹은 벌크인서트 사용. 
3. System.out.print 대신 로그 프레임워크로 사용하기 
4. InitApplicationService(application 계층)이 CurrncyDTO(ui 계층)에 의존하는데 이런 패키지 구조로 잡은 이유는? 
   - 아키텍처 구조에 대해 고민해볼 것 [링크](https://wikibook.co.kr/article/layered-architecture)
5. lombok 추가했지만 사용을 안한다.
6. Currency의 UUID id 사용했는데 이유는? 
   - UUID를 왜 썼는지 설명이 안된다면 Long 쓰는게 더 낫다. 디비 용량 입장에선 16자리나 잡아먹어서 낭비다. 인덱스나 드로윙 등 Long이 더 좋다. 
7. Currency에서 equals, hashcode 오버라이드한 이유는 무엇인가? equals 에서는 id가 없이 되어있다. 무슨 의도인가? 
8. dateTime에서 LocalDateTime으로 넣고있는데 왜?
9. CurrencyClient와 CurrencyRepository 종류에 대한 설명
10. CurrencyLayerClient에서 RestTemplete을 선택한 이유는 무엇인가?
11. WebClient와 RestTemplete의 차이점은?
12. Spring-Retry 관련된 자동화 테스트 코드가 있는가?
13. accessKey, api url 같은건 자주 바뀌니, 프로퍼티로 관리하는게 좋다.
14. Retry에서 maxAttempts를 3으로 명시한 이유는? 디폴트라면 명시안해도 되지 않는가?
15. RumtimeException이 아닌 Exception을 사용한 이유는?
    - 다양한 Exception에 대해 알아보기
16. ExceptionHandler가 있는가?
17. BigDecimal은 잘 사용하고 있다.
18. infra 계층에 CurrencyRepository 두는 이유가 있는가?
    -> 이해에 따라 정의가 달라지겠지만, 도메인으로 두는지 인프라로 두는지 DDD 다시 공부해볼것
19. CurrencyTest에서 생성에서 isNotNull()로 확인하는데 인스턴스를 생성할 때 Null인 경우가 있는가?
    -> new 연산자를 사용하면 인스턴스는 생성된다. 자바코드 학습코드로 보인다. assertDoesNotThrow 같은걸로 확인하는게 좋다.  
20. 화폐명 3글자에 3글자이면서 화폐가 아닌 경우 "ABC" 이런 경우, 어떻게 예외처리할 것인지. (외부API 없이 검증 방법)
21. ExchangeServiceTest를 보면 ExchangeService를 사용하지 않는다. CurrencyRepository만 테스트한다.
22. InitApplicationServiceTest를 보면 이해하기 쉽지 않다. 사용하는 의존성을 다 파악해야한다.
    -> currencies에 어떤 값이 들어있는지 테스트 코드에 값을 드러내는게 더 좋아보인다.
23. *외부API 여러 문제가 발생되었을 시 고려할 부분을 지킨 다음에 위의 사항 고민해볼 것.     





















