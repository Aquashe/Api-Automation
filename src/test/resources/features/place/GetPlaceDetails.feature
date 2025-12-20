Feature: Get Place details Api

  @GetPlace
  Scenario Outline:
    Given Get Place payload with "<placeId>"
    When user calls "GetPlaceAPI" with "get" http method
    Then the API call got success with status code 200
    And GetPlace response should be parsed correctly
    And a place details should show inside responseBody
    Examples:
      | placeId                          |
      | 4a5db91f2ad5988ac994e2de49b75521 |