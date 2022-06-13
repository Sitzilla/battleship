# Battleship game

- CLI interface
- Single-player game
- 10x10 grid
- Computer places ships for human and opponent randomly
- Computer chooses shots randomly
- Standard battleship rules


### Run locally
#### With Java
- `mvn clean compile package`
- `java -jar target/battleship-1.0.jar`

Note: configured to compile for java 13 JRE

#### With Docker
- `docker build -t battleship-evan .`
- `docker run -it --rm battleship-evan`