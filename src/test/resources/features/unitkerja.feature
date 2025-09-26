Feature: Unit Kerja Management
  As a user
  I want to manage Unit Kerja
  So that I can add, edit, delete, and search units

  Background:
    Given User is logged in and on Unit Kerja page

  Scenario: Add a new Unit Kerja
    When User adds a unit with name "Finance35", code "F-35", and active "true"
    #Then Unit with name "Finance35" should appear in the list

  #Scenario: Edit an existing Unit Kerja
    #When User edits unit "Finance" to have name "Finance Dept", code "FIN2", and active "false"
    #Then Unit with name "Finance Dept" should appear in the list

  #Scenario: Delete an existing Unit Kerja
    #When User deletes unit with name "Finance Dept"
    #Then Unit with name "Finance Dept" should not appear in the list

  #Scenario: Search for a Unit Kerja
    #When User searches unit with keyword "Finance"
    #Then Unit with name "Finance" should appear in the list
