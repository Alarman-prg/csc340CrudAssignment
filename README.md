## CRUD application

---
### What packages and frameworks does it use?
This application uses the terminal for interaction to create and view queries to the backend implementation of a persistent
database using a combination of JPA, Hibernate, and Mariadb Client.
---
### Instructions to run -
1. Clone repo
2. Have mariadb locally installed and create a database
3. Edit persistence.xml
   - rename jakarta.persistence.jdbc.url value from, 'value = _jdbc:mariadb://localhost:3306/cruddemo_', to'value= _jdbc:mariadb://localhost:3306/your-database-schema-name_'
   - setup correct username and password
4. Clean, Build, and Run
