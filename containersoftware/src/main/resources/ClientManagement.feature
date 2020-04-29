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
Feature: Manage Client aspects as Logistic company admin

  @tag1
  Scenario: Find Client based on Name and Email
    Given A client account with the name "John Johnson"
    And the same account has the email "jjohnson@gmail.com"
    When Enter a valid name or email in name lookup
    Then Display message that the client account is selected for view
    
    Scenario: No client with entered Name and Email
    Given A client account with the name "John Johnson"
    And the same account has the email "jjohnson@gmail.com"
    When Enter an invalid name or email in name lookup
    Then Display message that the client was not found

		Scenario: End Container Journey
    Given A container with transit status of true
    When Choose to end the journey
    Then Login status is false and Container is removed from client