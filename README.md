# library

Instructions on how to build, run and test
1. Clone or download this repository
2. Open it in an IDE(preferably NetBeans) and build
3. Run the project on glassfish server
4. Open glassfish admin panel (localhost:4848)
5. In the left menu select Applications -> LibraryProject
6. Select "View Endpoint" to the corresponding service in the table
7. Click on Tester url
8. Click on a link
9. The methods you see are the methods offered by the web service

Description:
Even though the project is quite small, it has a large number of detail variations to select from while implementing it. But I do not know them, that's why i present my vision on this problem.
I have EJB facade classes inheriting from AbstractFacade. Such a structure, in my opinion, creates a scalable platform for future additions without modifying existing code. 
In the implementation methods I used Criteria Queries instead building sql queries myself. This is because the queries are quite simple. In create method transaction is used because these method changes data (adds it).
The EJB session classes are not used directly. Instead I created two service classes marked by WebService annotation, that are the interface that is an endpoint for the interaction with the system.
Also there is a Converter class to convert java 8 LocalDate to sql.date and vice-versa. The system uses Derby db and the persistence provider is pure eclipselink without Hibernate.
I also had an idea to use spring, but decided not to do it, until I do not clearly understand the use-cases of this framework