Feature: Add a place and Delete a place Apis

  @AddPlace
  Scenario Outline:
    Given Add Place payload with "<lat>" "<lng>" "<accuracy>" "<name>" "<phone>" "<address>" "<website>" "<types>"  "<language>"
    When user calls "AddPlaceAPI" with "post" http method
    Then the API call got success with status code 200
    And AddPlace response should be parsed correctly
    And after added the place status in the response body is "OK"
    And a place_id should be generated inside responseBody

    Examples:
      | lat        | lng       | accuracy | name            | phone              | address                   | website           | types          | language  |
      | -38.383494 | 33.427362 | 50       | Frontline house | (+91) 983 893 3937 | 29, side layout, cohen 09 | http://google.com | shoe park,shop | French-IN |

  @DeletePlace
  Scenario:
    Given Delete Place Payload
    When user calls "DeletePlaceAPI" with "delete" http method
    Then the API call got success with status code 200
    And DeletePlace response should be parsed correctly
    And after deleted the place status in the response body is "OK"

