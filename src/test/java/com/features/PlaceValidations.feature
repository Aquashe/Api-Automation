Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
  Given Add Place Payload with "<name>" "<phoneNumber>" "<Address>" "<language>"
  When user calls "AddPlaceAPI" with "Post" http request
  Then the API call got success with status code 200
  And the "status" in response body is "OK"
  And the "scope" in response body is "APP"
  And verify placeId created maps the "<name>" using "GetPlaceAPI"

Examples:
  |name           |phoneNumber       |Address                  |language  |
  |Frontline house|(+91) 983 893 3937|29, side layout, cohen 09|French-IN |
  |Backline house |(+91) 983 893 3937|29, side layout, cohen 09|Spanish-IN|



@DeletePlace
Scenario:  Verify If Delete Place functionality is working
  Given DeletePlace Payload
  When user calls "DeletePlaceAPI" with "Delete" http request
  Then the API call got success with status code 200
  And the "status" in response body is "OK"
