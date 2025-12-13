Feature: Fetch Book details by Id Api

@GetBookDetailsById
Scenario Outline: Verify Book details are coming correctly
  Given Book with Id "<bookId>"
  When user calls "GetBookByIdAPI" with "get" http method
  Then the API call got success with status code 200
  And GetBook response should be parsed correctly
  And shows response with Book details
  Examples:
    | bookId    |
    | dsatr457  |

  @GetBookDetailsByAuthor
Scenario Outline: Verify Book details are coming correctly
  Given Book with author "<authorName>"
  When user calls "GetBookByAuthorNameAPI" with "get" http method
  Then the API call got success with status code 200
  And GetBook response should be parsed correctly
  And shows response with Book details
  Examples:
    | authorName |
    | Thomas   |
