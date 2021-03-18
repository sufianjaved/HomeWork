Feature: Service Listens on the provided port

Scenario: User calls the Hello service to verify its response

Given Hello service running on localhost port 9090
When a user calls the service with "Sufian"
Then verify the response body text includes "Sufian"