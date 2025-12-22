Feature: Validate Create and Delete Product  Api

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

  @CreateProduct
  Scenario Outline:
    Given Product payload with "<productName>" "<productCategory>"  "<productSubCategory>" "<productPrice>"  "<productDescription>" "<productFor>"  "<productImage>"
    When user calls "CreateProductAPI" with "post" http method
    Then the API call got success with status code 201
    And Create Product response should be parsed correctly
    And a product  should be generated with productId
    And message with "Product Added Successfully" inside Create product response body.
    Examples:
      | productName | productCategory | productSubCategory | productPrice | productDescription  | productFor | productImage |
      | Nirappara   | HomeMade        | Curry Powder       | 500          | Nirrapara Originals | Home       | shirt.jpeg   |

  @DeleteProduct
  Scenario:
    Given Delete Product
    When user calls "DeleteProductAPI" with "delete" http method
    Then the API call got success with status code 200
    And Delete Product response should be parsed correctly
    And message with "Product Deleted Successfully" inside Delete product response body.
