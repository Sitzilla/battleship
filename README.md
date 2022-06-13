# Battleship game

- CLI interface
- Single-player game
- 10x10 grid
- Computer places ships for human and opponent randomly
- Computer chooses shots randomly
- Standard battleship rules


### Run locally

#### Option 1, use image from Dockerhub
- `docker pull sitzilla/battleship:v1.1`
- `docker run -it sitzilla/battleship:v1.1`

#### Option 2, compile and run locally
Compile
- `mvn clean compile package`
- Note: configured to compile for java 13 JRE

Run with Java
- `java -jar target/battleship-1.1.jar`

Run with Docker
- `docker build -t battleship-evan .`
- `docker run -it --rm battleship-evan`