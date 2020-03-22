FROM java:8
RUN mkdir temperature
COPY /target/temperature-0.1.0.jar /temperature
WORKDIR /temperature
CMD ["java","-jar","/temperature-0.1.0.jar"]