@error-scenarios
Feature:
    Testing the Error Scenarios

    @Error_missing_phone    
    Scenario: error check
        Given I set body to {"id": "x","category": {"id": 0,"name": "string"},"name": "doggie","photoUrls": ["string"],"tags": [{"id": 0,"name": "string"}],"status": "Active"}
        And I set headers to
	      | name          | value 			 |
	      | Content-Type  | application/json |
        When I POST to /petstore/v2/pet
        Then response code should be 500
        And response body path $.message should be something bad happened


    @Error_invalid_id
    Scenario: Invalid ID returns 404 code response
        Given I set Content-type header to application/json
        When I GET /petstore/v2/pet/invalid_id
        Then response code should be 404
        And response body path $.message should be java.lang.NumberFormatException: For input string: \"invalid_id\"

    @Error_fault_rule
    Scenario: Invalid String length returns custom error response
        Given I set body to {"id": x,"category": {"id": 0,"name": "string"},"name": "doggie","photoUrls": ["string"],"tags": [{"id": 0,"name": "string"}],"status": "Active"}
        And I set headers to
	      | name          | value 			 |
	      | Content-Type  | application/json |
        When I POST to /petstore/v2/pet
        Then response code should be 400
        And response body path $.message should be bad input