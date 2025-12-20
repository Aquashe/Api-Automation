Feature: Update Place details Api

  @UpdatePlace
  Scenario Outline:
    Given Update Place payload with "<placeId>" "<address>" "<key>"
    When user calls "PutPlaceAPI" with "put" http method
    Then the API call got success with status code 200
    And UpdatePlace response should be parsed correctly
    And a msg with "Address successfully updated" should show inside responseBody
    Examples:
      | placeId                          | address                   | key        |
      | 4a5db91f2ad5988ac994e2de49b75521 | 70 Summer walk, Florida   | qaclick123 |
      | 4a5db91f2ad5988ac994e2de49b75521 | 29, side layout, cohen 10 | qaclick123 |
