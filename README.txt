Notes from Craig MacDougall
=====================================

I have moved the application to spring boot and added an MVC controller for the human interactions, with a REST
controller for interactions via a client like Postman.
I used JPA as a repository instead of the existing Hibernate class.
I have added unit tests for the controllers and the service class.

I run the server using 'mvnw clean spring-boot:run' in a terminal (I am on Windows). It can also be run by directly running the CakeMgrApplication class.

Going to http://localhost:8080/ will give the list of cakes. There is a button for adding a new cake.
Going to http://localhost:8080/cakes or issuing a GET request will give the list of cakes in JSON format.
Sending a POST request to http://localhost:8080/cakes with correct JSON for a cake will add that cake, or give the appropriate error.

Cake Manager Micro Service (fictitious)
=======================================

A summer intern started on this project but never managed to get it finished.
The developer assured us that some of the above is complete, but at the moment accessing the /cakes endpoint
returns a 404, so getting this working should be the first priority.

Requirements:
* By accessing the root of the server (/) it should be possible to list the cakes currently in the system. This must be presented in an acceptable format for a human to read.

* It must be possible for a human to add a new cake to the server.

* By accessing an alternative endpoint (/cakes) with an appropriate client it must be possible to download a list of
the cakes currently in the system as JSON data.

* The /cakes endpoint must also allow new cakes to be created.

Comments:
* We feel like the software stack used by the original developer is quite outdated, it would be good to migrate the entire application to something more modern.
* Would be good to change the application to implement proper client-server separation via REST API.

Bonus points:
* Tests
* Authentication via OAuth2
* Continuous Integration via any cloud CI system
* Containerisation


Original Project Info
=====================

To run a server locally execute the following command:

`mvn jetty:run`

and access the following URL:

`http://localhost:8282/`

Feel free to change how the project is run, but clear instructions must be given in README
You can use any IDE you like, so long as the project can build and run with Maven or Gradle.

The project loads some pre-defined data in to an in-memory database, which is acceptable for this exercise.  There is
no need to create persistent storage.


Submission
==========

Please provide your version of this project as a git repository (e.g. Github, BitBucket, etc).

Alternatively, you can submit the project as a zip or gzip. Use Google Drive or some other file sharing service to
share it with us.

Please also keep a log of the changes you make as a text file and provide this to us with your submission.

Good luck!
