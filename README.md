Pull this repository to your local drive: 
   https://github.com/githubplayer007/lightfeather

Build jar:
…\ lightfeather\mvn clean package

Build docker image:
...\ lightfeather\docker build --tag=lightfeather .

Run docker image in a container:
docker run -p 8080:8080 --name lightfeather lightfeather

Test the two end-points with Postman:

http://localhost:8080/api/supervisors 

http://localhost:8080/api/submit 

{
  "firstName" : "Mark",
  "lastName" : "Smith",
  "supervisor" : "AAA",
  "phoneNumber" : "121212121",
  "email" : "sfdsf@gmail.com"
}
