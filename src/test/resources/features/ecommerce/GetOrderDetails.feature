Feature: Validate fetching of Order Details using Api

  @UserLogin
  Scenario Outline:
    Given Login payload with "<userEmail>" and "<userPassword>"
    When user calls "LoginAPI" with "post" http method
    Then the API call got success with status code 200
    And Login response should be parsed correctly
    And token should be generated with userId
    And message with "Login Successfully" inside response body.
    Examples:
      | userEmail               | userPassword |
      | thomasukutty6@gmail.com | Pass@1234    |

  @GetOrder
  Scenario Outline:
    Given GetOrder payload with "<OrderId>"
    When user calls "GetOrderAPI" with "get" http method
    Then the API call got success with status code 200
    And Get Order response should be parsed correctly
    And message with "Orders fetched for customer Successfully" inside Get Order response body.
    Examples:
      | OrderId                  |
      | 6948e90732ed865871447c64 |