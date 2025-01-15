Feature: Test communication between Service One and Service Two

  Background:
    # Set the base URLs for the services
    * def serviceOneUrl = 'http://localhost:8082/service2/hello'

  Scenario: Service One calls Service Two
    Given url serviceOneUrl
    When method get
    Then status 200
    And match response == 'Hello Service 2'