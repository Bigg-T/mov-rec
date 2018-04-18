# Project repository for team 31

## Contributors
- Thien N. 
- Jean Paul T.
- Amrita V.
- Sajid R.
- Amit P.

----

## [Video for System Demo](https://youtu.be/EPMNCAEIkEQ)
https://youtu.be/EPMNCAEIkEQ

<iframe width="560" height="315" src="https://www.youtube.com/embed/EPMNCAEIkEQ" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>

## [Video for System Setup](https://youtu.be/ictBcBIGBmk)
https://youtu.be/ictBcBIGBmk

<iframe width="560" height="315" src="https://www.youtube.com/embed/ictBcBIGBmk" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>


## [Final Presentation](https://youtu.be/xkDbCfQVKNQ)
https://youtu.be/xkDbCfQVKNQ

<iframe width="560" height="315" src="https://www.youtube.com/embed/xkDbCfQVKNQ" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>

----- 

### Jenkins Endpoint
[Link to Jenkins instance](jenkins.codecastle.org)
```
Username: team31
Password: team31
```

### MOVI3HALL Home Page
[MOVI3HALL Index.html](http://m0vi3h4ll.s3-website.us-east-2.amazonaws.com/)

### SonarQube Endpoint
[Link to SonarQube instance](http://ec2-18-220-143-170.us-east-2.compute.amazonaws.com:9000)

--------

## System Setup

We also have Jenkins for continuous integration. Every time new code are pushed in the code repository, or someone make a PR, it will trigger Jenkins webhook. Sonarqube used to check code quality.

Please follow installation Instruction:

- [Jenkins](https://docs.google.com/document/d/1qdBXKcmEI16SQ9NC6vETsDK8u2h974uiOu0KPFUKNYc/edit) 
- [Sonarqube](
https://docs.google.com/document/d/1r8LWba6zEzjGTrkMr9nUiupRxHlaqJXcYtDt8a5f0TY/edit)

---- 

The technologies for the backends include, Java 8, SpringMVC, MySQL Workbench, Maven and other libraries maven will build based on the pom.xml. The pom.xml listed all the libraries the product is currently depending on to run. It is also the place to add more plugins, libraries in the future. Maven will use it to build the backend. 

Please follow installation Instruction:
- [Java 8, SpringMVC and Maven](https://course.ccs.neu.edu/cs4500/ssl/pdf/spring-boot.pdf)
- [MySQL Workbench](https://course.ccs.neu.edu/cs4500/ssl/pdf/Integrating-Spring-Boot-with-MySQL.pdf) 

-----

The technologies for the frontends include Node Package Manager (NPM), Yarn Package Manager (Yarn), Ecmascript 6, and ReactJS and other library listed in package.json files. NPM or Yarn both have the ability to build the frontend dependencies based on the package.json files.

Installation instructions to run frontend:

- [NPM](https://www.npmjs.com/get-npm)
- [Yarn](https://yarnpkg.com/lang/en/docs/install/#debian-stable)

Once you have the tools install, the rest should be smooth. You will find the the code base in on Github.

-----

#### Clone Github Repository
Install with SSH key
```$xslt
git clone git@github.ccs.neu.edu:CS4500/team-31-spring18.git
```
Install with Github account username and password
```$xslt
git clone https://github.ccs.neu.edu/CS4500/team-31-spring18.git
``` 

-----

#### Install backend 
##### Make sure you are in the top level directory, where ```pom.xml``` is located.

Create a JAR file to  upload to AWS Elastic Bean Stalk
```$xslt
mvn clean install
```
Run the back-end on ```localhost:8081```
```$xslt
mvn spring-boot:run
```
Run the back-end tests
```$xslt
mvm compile
mvn test
```

#### Install frontend
##### When installing/running front-end, you should be in ```<root>/src/main/webapp/```, where there will be a ```package.json``` file.
Create a production build, a ```build``` folder will be created do be deploy on AWS S3.

Install all dependencies
```$xslt
npm install
```
AWS S3 or anything web-host server. Running this will create a ```build``` folder in the ```webapp``` directory.
```$xslt
yarn build_prod
```
Running the frontend on ```localhost:3000```
```$xslt
yarn start
```
Running front-end tests
```$xslt
yarn test
```
If error due to TypeError: environment.setup, then eject, then re-run all 
test suites.

Might needed to run ```yarn install``` again

#### Other Info
- All backend REST endpoints and documentation are located in ```DEV_API_DOCS``` folder
