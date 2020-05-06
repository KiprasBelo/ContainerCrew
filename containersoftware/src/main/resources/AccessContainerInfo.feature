#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Access and edit features of containers

  @tag1
  Scenario: Add container to account
    And A destination of 'New York'
    When I assign the container to the client
    Then Display message that the Container has been added
    
   Scenario: No containers availabe
    Given No available containers in the containerLog
    And An order with a destination of 'New York'
    When I try to assign the container to the client
    Then Display message that a container is not available
    
    Scenario: Access container order history
    Given A container in the containerLog
    And at least one order behind current order in the container histroy
    When I call the history for a container
    Then Display order History
    
    Scenario: Get container temperature data
    Given A container in the containerLog
    And Todays date along with Container start date
    When Check dates of container access
    Then A number of hours of temperature are generated
    
    Scenario: Get container temperature data for login less than 48 hours ago
    Given A container in the containerLog
    And Todays date along with Container start or last login date
    When Check dates of login
    Then A number of hours of new temperature are generated
    
    Scenario: Get container temperature data for Contianer Creation less than 48 hours ago
    Given A container in the containerLog
    And Todays date along with Container start as todays date
    When Check dates of Container start
    Then A number of hours of new temperature are created
    
    Scenario: Edit Container data as Admin
    Given A container in the containerLog
    When Edit humidity and atmospheric pressure
    Then The values are changed in the database

