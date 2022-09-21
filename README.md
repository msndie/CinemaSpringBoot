# Status

Wait for my teammate to finish front. I will add screenshots later

# CinemaSpringBoot

This is a site for movie theater, union of my two previous projects (<a href="https://github.com/msndie/FWA">FWA</a> and <a href="https://github.com/msndie/Cinema">Cinema</a>).
- Now users has roles
- remember-me functionality which can be enabled on signIn page
- site has two languages (Russian and English)
- backend validation for signUp page with errors for each field
- email verification for new users

In this project we use:
- Spring Controllers
- Spring security
- Hibernate with Spring Data Jpa (with custom repos. to solve n+1 problem)
- Ajax-requests for live search on films page
- Spring Websockets for chats
- JavaMailSender
- FreeMarker as template engine.

# Launch

<h3>Manual</h3>

You need to create an empty database in postgres.

Go to ```ex02``` folder and write your credentials in <a href="https://github.com/msndie/CinemaSpringBoot/blob/main/ex02/Cinema/src/main/resources/application.properties" target="_blank">```application.properties```</a> file and change path to folder where images will be stored.

Then you can just use the following command to run the application
```
./mvnw clean spring-boot:run
```
<h3>Docker</h3>

Just go to docker folder and run it with the following command

```
docker compose up --build
```
<h3>Voilà</h3>

Now website available at <a href="http://localhost:8080"><b>localhost:8080</b></a>. You can access it through ADMIN account (email - <b>admin@admin.com</b> pass - <b>123QWEasd</b>)
Or you can create new account, it will send an email on address that you provide, but not really, actually email will be available on <a href="https://ethereal.email">this website</a>. You need to put following credentials to get it
```
blanca.marks@ethereal.email
ARMujBpT2y4Cxf95dr
```
Then just click on the link in the email

Available mappings:
- /, /signIn, /signUp
- /profile
- /admin/panel/films     (ADMIN only, create film)
- /admin/panel/halls     (ADMIN only, create hall)
- /admin/panel/sessions  (ADMIN only, create session with available hall and film)
- /sessions              (search available sessions with live search by name of the film)
- /sessions/{id}         (info about particular session)
- /films                 (list of all films)
- /films/{id}            (info about particular film)
- /films/{id}/chat       (multiuser chat about particular film)
