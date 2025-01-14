Feature: Test communication between Service One and Service Two

  Background:
    # Set the base URLs for the services
    * def serviceOneUrl = 'http://localhost:8080/service2/'

  Scenario: Service One calls Service Two
    Given url serviceOneUrl
    And path '/'
    When method get
    Then status 200
    And match response == 'Hello, I am Java.'
