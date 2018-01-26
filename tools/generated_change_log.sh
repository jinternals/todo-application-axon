#!/usr/bin/env bash


liquibase --driver=com.mysql.jdbc.Driver \
      --classpath=/Users/mradul/.m2/repository/mysql/mysql-connector-java/5.1.21/mysql-connector-java-5.1.21.jar \
      --changeLogFile=../src/main/resources/todo-app-changeset.xml \
      --url="jdbc:mysql://localhost:3306/auth" \
      --username=root \
      --password=machine \
      generateChangeLog