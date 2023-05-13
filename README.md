### Setting up the environment

* Install and setup Java 1.8.
* Setup MySql database.

### Database Setup

* Create a db with example swi_eventpro_1 or the name you like.
* you should copy database name, username, password.


### Project Setup

* Clone project or if you have in zip format then extract it in a folder.
* Open the project in an IDE preferable Intellij.
* Let the project download all the dependencies. ( it should take less time if you already have gradle )

### Configure Database

* Open file application.properties location in ./src/main/resources/application.properties
* You can replace following code in file to change db, username, password.
```
    spring.datasource.url=jdbc:mysql://localhost:3306/{Your_Db_Name_Here}?useSSL=false&serverTimezone=UTC
    spring.datasource.username={Your_Username_Name_Here}
    spring.datasource.password={Your_Password_Name_Here}
    
```
* update specific values and replace the code in application.properties

### Email Config

* Open file application.properties location in ./src/main/resources/application.properties
* Change spring.mail.username={with your email address} here
* Change spring.mail.password={with app password of you email} (see app passwords section for more details)
* Change app.base_url={base_url} of your app.

### Project Start and Configure

* Start the project.
* After first start call {BASE_URL}/users/initiate to initiate project.
* use username: admin  and   password: 123  to login

### App password

* App password can be generated on your accounts section.
* Open up 2-Step Verification.
* Open App password in last.
* Select Other and give any name of project.
* Add App password.
* Copy the app password.