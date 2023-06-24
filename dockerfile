from tomcat:9-alpine
copy /target/SpringHibernate.war /usr/local/tomcat/webapps/
expose 8080
cmd ["catalina.sh", "run"]