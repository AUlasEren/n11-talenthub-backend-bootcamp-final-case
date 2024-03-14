FROM openjdk:17 as build

WORKDIR /src

ADD . .

RUN chmod +x gradlew

RUN ./gradlew build

FROM openjdk:17 as user-user-review-micro-service

EXPOSE 8085

COPY --from=0 /src/UserAndUserReviewService/build/libs/UserAndUserReviewService-v.0.1.jar .

ENTRYPOINT ["java","-jar","UserAndUserReviewService-v.0.1.jar"]

FROM openjdk:17 as restaurant-micro-service

EXPOSE 8086

COPY --from=0 /src/RestaurantService/build/libs/RestaurantService-v.0.1.jar .

ENTRYPOINT ["java","-jar","RestaurantService-v.0.1.jar"]

FROM openjdk:17 as geotaste-logger-service

EXPOSE 8081

COPY --from=0 /src/GeoTaste-Logger/build/libs/GeoTaste-Logger-v.0.1.jar .

ENTRYPOINT ["java","-jar","GeoTaste-Logger-v.0.1.jar"]

