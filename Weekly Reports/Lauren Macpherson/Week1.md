[Link to Pull Request]()

## Week 4 Report ##

Along with my task cards, I also added the base apps for backoffice and cashier's app, based on a clarification on how these parts of the project should be structured. I copied some of the code from our spring starbucks app into the backoffice as I believed that code would be useful. Because I changed the location of the files though, there may be package statement issues to resolve. 

## My Task Cards ##

![My task cards this week](images/Week4_cards.png)

1. Implement rewards points as part of user table 
2. Create 'add rewards points' API for use in backoffice
3. Associate starbucks card with user using user id 
4. Create separate app for back office 

## Implement Rewards Points ##

I added the rewards points designation to the user model, assigned setters and getters, etc. When a new user is made they are initialized with 0 reward points. 

## Create 'Add Rewards Points' API 

After evaluating how reward points would be added, I first added some lines to the order API to make sure that purchases resulted in reward points being added. 

Then, I added the 'add reward points' API to the backoffice. We would only be using that specific API (inputting a user and an amount of reward points to add) in the backoffice itself, as a way for employees to add missing points. 

## Associate Starbucks Card with User ##


