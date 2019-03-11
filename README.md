# SafeBoda

A sample application that validates Promo Codes based on the origin and the destination of the desired trip.

It is structured in a MVC way, with controllers exposed as SERVLETS API endpoints, services exposed as EJB entities that handle the logic of the whole application and store data into the database and models exposed as JPA objects that are stored themselves in the database.

To test the functionality, I have written a JUnit unit tests into the tests packages with using JUnit, Mockito and Hamcrest.

Out of the other libraries, I use JSON for stringifying the Objects and parsing the string response into them.

If you are about to run the application, just install a Glassfish server and deploy the application to it...
