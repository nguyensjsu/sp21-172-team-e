# Team Project Reop

https://github.com/nguyensjsu/sp21-172-team-e

## Team Name
Team E

## Individual reports will be under the folder "Weekly Reports" 

## Architecture Diagram

* ![System_Architecture](images/System_Architecture.png)


Users connect to app using a browser. The browser connects to the Bootstrap frontend app which is running on GCP through a load balancer. The frontend app run on a GKE Kubernetes cluster that can be auto scaled.

The Bootstrap frontend app connects to backend deployed on GCP through Kong API gateway. The API gateway has 10 routes, one for each of the microservices deployed on individual requirement.

## Cashier's App
What features were implemented?

## Backoffice Help Desk App
What features were implemented?

## Online Store 
What features were implemented?

## REST API 
Final design with sample request/response

## Integrations 
Which integrations were selected?

## Cloud Deployments
Design Notes on GitHub an Architecture Diagram of the overall Deployment.
How does your Team's System Scale?  Can it handle > 1 Million Mobile Devices?

## Technical Requirements
Discussion with screenshot evidence of how each technical requirement is meet.


