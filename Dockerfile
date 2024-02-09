# Use an official OpenJDK runtime as a parent image
FROM adoptopenjdk:8-jre-hotspot


# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY target/grocerybooking-0.0.1-SNAPSHOT.jar /app/

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application when the container launches
CMD ["java", "-jar", "grocerybooking-0.0.1-SNAPSHOT.jar"]