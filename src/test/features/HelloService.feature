Feature: Service Listens on the provided port

  Scenario Outline: User calls the Hello service to verify its response
    Given Hello service running on localhost port 9090
    When a user calls the service with <inputText>
    Then verify the response is <expectedCode>
    Examples:
      | inputText       |expectedCode  |
      | "Sufian"        | 200          |
      | "NegativeTest%" | 400          |