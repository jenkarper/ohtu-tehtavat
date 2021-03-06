Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new user is selected
        When  a valid username "liisa" and password "salainen1" and matching password confirmation are entered
        Then  new user is created
    
    Scenario: creation fails with too short username and valid password
        Given command new user is selected
        When  invalid username "li" and valid password "salainen2" and matching password confirmation are entered
        Then user is not created and error "username should have at least 3 characters and contain only letters between a-z" is reported

    Scenario: creation fails with correct username and too short password
        Given command new user is selected
        When  valid username "alice" and invalid password "short" and matching password confirmation are entered
        Then user is not created and error "password should have at least 8 characters and should not contain only letters" is reported

    Scenario: creation fails when password and password confirmation do not match
        Given command new user is selected
        When  valid username "alice" and valid password "wonderland99" and not-matching confirmation are entered
        Then user is not created and error "password and password confirmation do not match" is reported

    Scenario: user can login with successfully generated account
        Given user with username "lea" with password "salainen1" is successfully created
        And   login is selected
        When  correct username "lea" and password "salainen1" are given
        Then  user is logged in

    Scenario: user can not login with account that is not successfully created
        Given creating user with username "aa" and password "bad" is attempted
        And   login is selected
        When  invalid username "aa" and invalid password "bad" and matching password confirmation are entered
        Then  user is not logged in and error message is given