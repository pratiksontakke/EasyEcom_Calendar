
# EasyEcom_Calendar API

EasyEcom_Calendar is a project that connects to the Google Calendar API to perform Create, Update, Read, and Delete (CURD) operations on Google Calendar events. The project likely implements features such as creating new events, updating existing events, retrieving event information, and deleting events from the Google Calendar. It allows users to interact with their Google Calendar directly from the EasyEcom platform, simplifying event management and providing an integrated calendar experience.


## API Presentation Video
<ul>
  <li><a href="https://drive.google.com/file/d/1eO1HviBfz_e6o-orzkAHVtzJyFXR9DPx/view?usp=share_link">Video Link</a></li>
</ul>


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
API Endpoints : 
```bash

AUTHENTICATION :
- http://localhost:9000/login/google

GET:

- http://localhost:9000/getEvents

POST:
- http://localhost:9000/addEvent

PUT:
- http://localhost:9000/updateEvent

DELETE:
- http://localhost:9000/deleteEvent

```

Testing links of endpoints : 
```bash

GET : 
  - http://localhost:9000/getEvents?startDate=2023-01-17T00:00:00.000Z&endDate=2023-01-17T23:59:00.000Z&calendarId1=primary

POST :
  - http://localhost:9000/addEvent

  EventDTO BODY :
  {
    "summary": "Vicky DSA",
    "location": "Online",
    "description": "Practice DSA",
    "attendees": [
      "pratikasss488@gmail.com"
    ],
    "startTime": "2023-01-17T14:00:00.000+05:30",
    "endTime": "2023-01-17T15:30:00.000+05:30"
  }


PUT : 
  - http://localhost:9000/updateEvent?eventId=52u1irtr7idcs5rtq95tvca41cdfdf

  EventDTO BODY :
  {
    "summary": "Ritik DSA",
    "location": "Offline",
    "description": "Practice 2 DSA",
    "attendees": [
      "pratikasWs488@gmail.com",
      "pratikasss488@gmail.com",
      "pratikassss488@gmail.com"
    ],
    "startTime": "2023-01-17T12:00:00.000+05:30",
    "endTime": "2023-01-17T13:30:00.000+05:30"
  }

DELETE :
  - http://localhost:9000/deleteEvent?eventId=52u1irtr7idcs5rtq95tvca41c

```


## Spring Boot Dependencies
- google-api-services-calendar
- spring-boot-starter-validation
- spring-boot-starter-web
- spring-boot-maven-plugin
- spring-boot-starter-test
- google-api-client
- lombok

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

## Thank you note
Thank you all who is giving precious time to visit my little creative project which is made with lot of efforts.
