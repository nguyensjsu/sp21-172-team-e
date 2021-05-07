[Link to Pull Request](https://github.com/nguyensjsu/sp21-172-team-e/pull/11)

## Week 3 Report ##

### My Task Cards ###

![My task cards this week](images/Week3_cards.png)

My tasks were to: 
1. Convert H2 Database to MySQL 
2. Backoffice Help Desk (Front-End) Prototype 
3. Backoffice Help Desk (Back-End) 

### Convert from H2 Database to MySQL ###

My version from last week wasn't working. I decided to roll back to main to see if it would build correctly without the MySQL changes, and it worked. I worked on this using the same premise from last time but with additional reference material. I used this to start: [accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/). 

Some steps taken: removed 'runtimeOnly 'com.h2database:h2'' from dependencies in build.gradle. Changed application properties file to include properties for MySQL. 

I then created a MySQL database for testing: 
[Creating the test database](images/Week3_testDB.png)

And I was eventually able to get it working. However I noticed that the api names appeared to cause some errors, possibly due to the api mapping names being the same. This caused issues with curl. After trying to comment out the code I was still receiving issues, but I consulted my team about them and continued on to the backoffice work. 

### Backoffice Help Desk (Front-End) Prototype ###

The objective of me creating this was primarily to have a way to test the backend I planned to make for the Backoffice while my front-end team members finished the more customer-facing portions of the code. 

The objectives for the backoffice as they were listed in the project assignment are: 

"We need a Backoffice Help Desk App and Cashier's App to be using SSO with one of the following SSO Service.  **A Back Office Help Desk Service Rep should have the ability to see any Customer's Rewards and fix any issues (i.e, missing rewards credit).**  The Back Office Help Desk App should be a separate App from the Online Store and should have SSO support with Barista App."

My goal was to create the following functionalities (focus was on the backend, but I would be using this frontend for testing):
* See a list of all cards (may be removed from final project, as this would cause issues in real use with millions of cards)
* Search and view a card by card number
* Add missing points/dollars to an account

This is in-progress as I continue to add to it for form submissions. 

### Backoffice Help Desk (Back-End) 

From reviewing the requirements for the backoffice I decided to add an API to StarbucksCardController for adding points to a card. It uses the same card validation as the card activation API. We already have an API for finding the card by card number and returning all of the cards. I expected more work to be needed for the backend part of this, but I realize that making use of these APIs is probably something that needs to be done on the frontend instead. 

With the backend I think it will be helpful to review the PaymentsController from the midterm and see how the "postAction" method works. It handled a full form submission, which may be needed especially with the parameters. 

### Added Cards for this Coming Week 

* Check mysql api issues 
* Complete Backoffice Front-End final version
* Add new APIs for backoffice functions 

### Potentially Useful Resources 
[Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

[Basic Steps for MySQL Server Deployment with Docker](https://dev.mysql.com/doc/refman/5.7/en/docker-mysql-getting-started.html)


