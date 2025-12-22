Feature: Validate user login Api

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