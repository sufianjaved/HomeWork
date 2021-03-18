Feature: Get Trajectory Details of Asteroids

Scenario: User calls a web service to get Asteroid Trajectory

Given a Trajectory exists
When a user retrieves the Trajectory Details by daterange "1990-01-01" and "1999-12-31" and dist-max as "0.01"
Then verify the statusCode and contentType
And response includes the count "50"