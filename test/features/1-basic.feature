@basic-operations
Feature:
    Testing the CRUD Operations
        
    @POST_create_record    
    Scenario: Create a User Record
        Given I set body to {"id": 700,"category": {"id": 0,"name": "string"},"name": "doggie","photoUrls": ["string"],"tags": [{"id": 0,"name": "string"}],"status": "Active"}
        And I set headers to
	      | name          | value 			 |
	      | Content-Type  | application/json |
        When I POST to /petstore/v2/pet
        Then response code should be 200
        And response body path $.id should be 700
        And response body path $.name should be doggie
        And response body path $.status should be Active
    
    @GET_specific_record    
    Scenario: retrieve a specific user record
        Given I set Content-type header to application/json
        When I GET /petstore/v2/pet/700
        Then response code should be 200


