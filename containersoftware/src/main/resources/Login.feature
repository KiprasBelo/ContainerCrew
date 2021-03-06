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
Feature: Login to account

  @tag1
  Scenario: Successful login
    Given a client with a valid account
    And login status is false
    When enter correct username and password
    Then Displays message about successful login
    
   Scenario: Incorrect username or password
    Given a client with a valid account
    And login status is false
    When enter incorrect username and password
    Then Displays message about unsuccessful login

    Scenario: Register Client
    Given An entered username 'john123'
    And An entered password 'pass'
    When Hit register button
    Then Display message about successful register
    