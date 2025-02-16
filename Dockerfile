FROM tomcat:9.0-jdk11

COPY target/contact-crud.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
