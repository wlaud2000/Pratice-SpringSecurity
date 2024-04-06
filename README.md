# Spring Security

## 새롭게 알게 된 것들
### mustache
- 스프링 부트에서 공식으로 지원하는 템플릿 엔진(Template Engine)이다.
- 로직 구현이 안된다. ( Login-less-templates )View 역활에만 충실하다.
- Java뿐만 아니라 현존하는 대부분의 언어를 지원한다.      //JSP는 Java언어만 지원

### 인가
특정한 경로에 요청이 오면 Controller 클래스에 도달하기 전 필터에서 Spring Security가 검증을 한다.
1. 해당 경로의 접근은 누구에게 열려있는지?
2. 로그인이 완료된 사용자인지?
3. 해당되는 role을 가지고 있는지?
