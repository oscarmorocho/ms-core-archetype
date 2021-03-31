@security-checks
Feature:
    Testing Secuirty Policies
        
    @JWT_security    
    Scenario: update a record
        Given I set Content-type header to application/json
        When I POST to /security/jwt
        Then response code should be 200
        And I store the value of body path $.jwt as access token
        And I set bearer token
        When I set body to {"id": 700,"category": {"id": 0,"name": "string"},"name": "cat","photoUrls": ["string"],"tags": [{"id": 0,"name": "string"}],"status": "Active"}
        And I set headers to
          | name          | value            |
          | Content-Type  | application/json |
        When I PUT /petstore/v2/pet
        Then response code should be 200
        And response body path $.name should be cat
    
    @OAuth_security
    Scenario: delete a record which needs oauth access token
        Given I have basic authentication credentials CX5pR4LCymtnwFHtDTxMk7JQUU13py15 and Q4VRq0Gm1c6zIONH
        When I POST to /security/oauth?grant_type=client_credentials
        Then response code should be 200
        And I store the value of body path $.access_token as access token
        And I set bearer token
        When I DELETE /petstore/v2/pet/700
        Then response code should be 200
        And response body path $.message should be 700







