Because of lack of the time I couldn't write all tests. Also, there are no      
validators for bank's bic value etc. There is minimalistic REST api,    
which does not handle all possible exceptions and statuses. If you have some questions    
you can ask me directly.

For launch this application
1. run ./mvnw clean package -DskipTests
2. run java -jar target/bank-1.0.1-SNAPSHOT.jar

You can overview rest api accessing http://localhost:8080/swagger