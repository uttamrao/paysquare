FROM tomcat:9
COPY /webapp/target/*.war /usr/local/tomcat/webapps/
