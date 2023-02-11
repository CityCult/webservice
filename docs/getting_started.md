# Getting Started

* prepare JAR package *[City:Cult Data Storage (Java)](https://citycult.github.io/data_storage_java)*
* compile and build WAR package for web service: `mvn clean package`
* prepare docker: `cp target/*.war docker/webservice.war`
* change director to `docker/`
  * use Docker Compose for development, see `docker_compose.yml` for details
  * build a Docker Image for other services: `docker build -t cc_webservice:latest .`
* open `http://localhost:8888/webservice/venue` in a browser
