FROM openjdk:11
COPY ./target/digidoc-0.0.1-SNAPSHOT.jar digidoc-0.0.1-SNAPSHOT.jar
EXPOSE 8181
CMD ["java","-jar","digidoc-0.0.1-SNAPSHOT.jar"]