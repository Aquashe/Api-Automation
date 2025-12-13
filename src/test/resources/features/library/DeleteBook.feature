Feature: Delete a book Api

@DeleteBook
Scenario: Verify a book is being successfully deleted using DeleteApi
  Given Delete Book payload
  When user calls "DeleteBookAPI" with "delete" http method
  Then the API call got success with status code 200
  And DeleteBook response should be parsed correctly
  And Msg in the response body is "book is successfully deleted"
