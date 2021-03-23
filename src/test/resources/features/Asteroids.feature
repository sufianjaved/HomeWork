Feature: Get Trajectory Details of Asteroids

Scenario Outline: User calls a web service to get Asteroid Trajectory
Given a Trajectory exists
When a user retrieves the Trajectory Details by date range "<date_min>" and "<date_max>" and dist-max as "0.01"
Then verify the statusCode and contentType and "<countInResponseBody>"

Examples:
| date_min      | date_max      | countInResponseBody   |
| 1990-01-01    | 1999-12-31    | 50                    |
| 2000-01-01    | 2009-12-31    | 374                   |
| 2010-01-01    | 2019-12-31    | 1618                  |

 # Test cases Example cases details provided below:
  # First Test:   Date Range for "the nineteen-nineties" 1990s ('90s) decade
  # Second Test:  Date Range for "two-thousands" 2000s ('00s) decade
  # Third Test:   Date Range for "twenty-tens" 2010s ('10s) decade