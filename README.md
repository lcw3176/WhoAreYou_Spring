# WhoAreYou_Spring
Microprocessor TeamProject API Server

### 기술 스택, 개발 환경
* Java, Spring Boot
* MySQL, Spring Data JPA
* WebSocket, jackson-json, Json Web Token 
* Jetbrains IntelliJ
### DATABASE TABLE 
- user table DDL
<pre>
<code>
    create table user(
    idx int primary key auto_increment,
    email varchar(40) not null unique,
    password varchar(64) not null
    );
</code>
</pre>

- device table DDL
<pre>
<code>
  create table device(
  idx int primary key auto_increment,
  name varchar(30) not null,
  user_idx int,
  foreign key(user_idx)
  references user(idx)
  on update cascade
  on delete cascade);
</code>
</pre>

- log table DDL
<pre>
<code>
  create table log(
  idx int primary key auto_increment,
  time timestamp 
  on update current_timestamp 
  default current_timestamp not null,
  state int not null,
  device_idx int,
  foreign key(device_idx)
  references device(idx)
  on update cascade
  on delete cascade);
</code>
</pre>


### URL 구조
|Task|Method|Path|Parameter|비고|
|-----------|-----|--------|--------|---|
|회원가입|POST|/users|email,pw||
|로그인|GET|/users/{email}/{pw}|||
|토큰 유효성 확인|GET|/validation/{token}|||
|기기 등록|POST|/auth/devices|email, deviceName|사용되지 않음|
|기기 조회|GET|/auth/devices/{email}|||
|기기 삭제|DELETE|/auth/devices/{email}/{deviceName}|||
|기록 조회|GET|/auth/logs/{email}/{deviceName}|||
|에러 페이지|GET|/error|||
|권한 없는 접근|GET|/error/noAuth|||
|소켓 접속||/auth/socket|||
|소켓 데이터 전달||쉼표로 구분|email, deviceName, state||





