FROM java:8
RUN mkdir rbk
COPY /target/rbk-0.1.0.jar /rbk
WORKDIR /rbk
CMD ["java","-jar","/rbk-0.1.0.jar"]