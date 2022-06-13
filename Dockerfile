FROM adoptopenjdk/openjdk13:ubi
RUN mkdir /opt/app
COPY target/battleship-1.0.jar /opt/app
CMD ["java", "-jar", "/opt/app/battleship-1.0.jar"]