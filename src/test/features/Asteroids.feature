Feature: Get Trajectory Details of Asteroids

Scenario Outline: User calls a web service to get Asteroid Trajectory
Given a Trajectory exists
When a user retrieves the Trajectory Details by date range "<date_min>" and "<date_max>" and dist-max as "0.01"
Then verify the statusCode and contentType
And response includes the count "<count>"

Examples:
| date_min      | date_max      | count     |
| 1990-01-01    | 1999-12-31    | 50      |
| 2000-01-01    | 2009-12-31    | 373     |
| 2010-01-01    | 2019-12-31    | 1618    |