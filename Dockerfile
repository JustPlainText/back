FROM java:8-jre
VOLUME /tmp
COPY build/libs/back-1.jar app.jar
COPY secure/key.enc key.enc
ENV GOOGLE_APPLICATION_CREDENTIALS key.json
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
CMD openssl enc -aes-256-cbc -base64 -pass $mypass -d -p -in key.enc -out key.json ; java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar