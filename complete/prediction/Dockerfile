FROM java:8
RUN mkdir prediction
COPY /target/prediction-0.1.0.jar /prediction
RUN ls
WORKDIR /prediction
CMD ["java","-jar","/prediction-0.1.0.jar"]