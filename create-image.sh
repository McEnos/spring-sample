mvn clean package -DskipTests
docker build -t joshuaenos/spring-boot-k8s .
docker push joshuaenos/spring-boot-k8s