# Usa una imagen base oficial de Tomcat 9 (con Java 11 por ejemplo)
FROM tomcat:9.0-jdk11

# Copia tu WAR al directorio webapps/ROOT.war
COPY target/contact-crud.war /usr/local/tomcat/webapps/ROOT.war

# Expone el puerto 8080 (Tomcat por defecto)
EXPOSE 8080

# Comando para arrancar Tomcat
CMD ["catalina.sh", "run"]
