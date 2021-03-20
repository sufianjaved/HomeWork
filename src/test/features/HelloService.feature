Feature: Service Listens on the provided port

  Scenario Outline: User calls the Hello service to verify its response
    Given Hello service running on localhost port 9090
    When a user calls the service with "<inputText>"
    Then verify the response code <expectedCode> and content type and body contains welcome message for "<inputText>"
    Examples:
      | inputText       | expectedCode  |
      | Sufian Javed    | 200           |
      | L'Oreal Paris   | 200           |
      |                 | 200           |

# Test cases Example cases details provided below:
  # First Test:   Plain Text
  # Second Test:  Text with Apostrophe
  # Third Test:   Blank