Feature: Validate an Order can be placed and deleted with Api's

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

  @CreateOrder
  Scenario Outline:
    Given Order payload with "<country>" and "<productId>"
    When user calls "CreateOrderAPI" with "post" http method
    Then the API call got success with status code 201
    And Create Order response should be parsed correctly
    And an orderId should be generated along with productId
    And message with "Order Placed Successfully" inside the Create Order response body.
    Examples:
      | country                         | productId                |
      | American Indian Ocean Territory | 6948dd2632ed865871445c52 |


  @DeleteOrder
  Scenario:
    Given Delete order payload
    When user calls "DeleteOrderAPI" with "delete" http method
    Then the API call got success with status code 200
    And Delete Order response should be parsed correctly
    And message with "Orders Deleted Successfully" inside the Delete Order response body.