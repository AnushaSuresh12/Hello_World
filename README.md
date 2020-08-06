# Hello-World-API

REQUIRMENTS:
    Maven
    Spring-boot
    REST Controller

To run this application follow these steps:-
1. First build the docker image by mentioning the following Command
docker build -f Dockerfile -t docker-hello .
2. After the Build is Successful Run the docker image
docker run -p 8080:8080 docker-hell0
3. Once the run is successful
go to the following websites
localhost:8080
localhost:8080/healthz


How would you automate the build/test/deploy process for this application?
I would use Jenkins to build/test and deploy the process in this application.
The various stages in the pipeline are:
1. Generating a Docker file and commiting the file to the github
2. Run the code against the test cases
3. Based on the errors debug and create new branch in the github to keep count of previous changes in the code.
