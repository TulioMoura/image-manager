From maven
WORKDIR  .

EXPOSE 8000
volume images 

run "mvn spring-boot:run"