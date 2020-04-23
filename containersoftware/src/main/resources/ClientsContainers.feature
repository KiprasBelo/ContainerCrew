
Feature: Container managing by clients

Scenario: Accessing a container owned by me as a client
	Given a valid client account
	And login status is true
	And a <list> of containers with matching client id 6
	When I search for a container using port of origin 'New York' and destinaltion 'Oslo'
	Then the containers matching the search will be displayed
