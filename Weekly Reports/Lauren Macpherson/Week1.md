##Week 1 Report##

This week, Rui and I both worked on the API. We mutually decided that he would work on the Starbucks Cards, and I would work on the orders. 


###My Task Cards###
![My task cards this week](/images/Week1_cards.png)

My tasks were to: 
1. Create a list of expected transactions 
2. Create a price list for menu items 
3. Write API for expected transactions. 

###Task 1: Create a list of expected transactions###

From the project page, there was a list of expected APIs we would need to use. We could edit these as needed, but this provided a good base for what we would be making. 

**POST    /order/register/{regid}**
Create a new order. Set order as "active" for register.

**GET     /order/register/{regid}**
 Request the current state of the "active" Order.

**DELETE  /order/register/{regid}**
Clear the "active" Order.

**POST    /order/register/{regid}/pay/{cardnum}**
Process payment for the "active" Order. 

**GET     /orders**
Get a list of all active orders (for all registers)

**DELETE     /orders**
Delete all Orders (Use for Unit Testing Teardown)

