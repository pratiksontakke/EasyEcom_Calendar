
# EasyEcom_Calendar API

EasyEcom_Calendar is a project that connects to the Google Calendar API to perform Create, Update, Read, and Delete (CURD) operations on Google Calendar events. The project likely implements features such as creating new events, updating existing events, retrieving event information, and deleting events from the Google Calendar. It allows users to interact with their Google Calendar directly from the EasyEcom platform, simplifying event management and providing an integrated calendar experience.

## Deployed link of project
- <a href="#"> EasyEcom_Calendar </a>

## Team Members

<ul>
  <li><a href="https://github.com/pratiksontakke">Pratik Sontakke</a></li>
</ul>


## Language
- Java

## Database
- My-SQL

## Deployment

On terminal perform:
```bash
  Open pom.xml with Spring Tool Suite / IntelliJ IDEA and run
```

On Swagger :

```bash
  http://localhost:8888/swagger-ui/#/
```

application.properties : 
```bash
  server.port=9000

  #Google Calendar API Configuration
  google.client.client-id="write your own id"
  google.client.client-secret="write your own secret code"
  google.client.access-token-uri=https://www.googleapis.com/oauth2/v3/token
  google.client.user-authorization-uri=https://accounts.google.com/o/oauth2/auth?access_type=offline&prompt=consent
  google.client.client-authentication-scheme=query
  google.client.scope=profile,email,https://www.googleapis.com/auth/calendar
  google.resource.user-info-uri=https://www.googleapis.com/oauth2/v2/userinfo
  google.resource.prefer-token-info=true
  google.client.redirectUri=http://localhost:9000/login/google
  spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER

  spring.main.allow-circular-references=true
  spring.main.allow-bean-definition-overriding=true

```


## Spring Boot Dependencies
- spring-boot-starter-web
- spring-boot-starter-test
- google-api-client
- google-api-services-calendar
- lombok
- spring-boot-maven-plugin

## 🛠 Tech Stack

- Java
- Spring
- Spring-boot
- Spring-boot-web
- spring-security
- Google calendar API


## Functionality


- Authentication
- Create token
- Perform CURD operations


## Flow of User

- Authentication 
- Create token 
- Perform CURD operations 

## API Presentation Video
<ul>
  <li><a href="https://drive.google.com/file/d/1eO1HviBfz_e6o-orzkAHVtzJyFXR9DPx/view?usp=share_link">Video Link</a></li>
</ul>

## API Presentation 
![API](./images/01.jpg)

![API](./images/02.jpg)

![API](./images/03.jpg)

![API](./images/04.jpg)

## Thank you note
Thank you all who is giving precious time to visit my little creative project which is made with lot of efforts.
