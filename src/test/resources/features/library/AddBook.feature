Feature: Create a Book Api

@CreateBook
Scenario Outline: Verify if book is being successfully added using AddBookApi
  Given Add Book Payload with "<name>" "<isbn>" "<aisle>" "<authorName>"
  When user calls "AddBookAPI" with "post" http method
  Then the API call got success with status code 200
  And AddBook response should be parsed correctly
  And the Msg in the in response body is "successfully added"
  And a bookId should be created inside in responseBody

  Examples:
    | name                                 | isbn   | aisle   | authorName |
    | Learn Man Automation with Java       | oireur  | 007     | Thomas   |


