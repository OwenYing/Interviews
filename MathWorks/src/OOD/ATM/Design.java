package OOD.ATM;

public class Design {
}


/*
Resources: http://www.math-cs.gordon.edu/courses/cs211/ATMExample/

User Scenario:
    User --> insert card --> debit / credit --> password
    --> deposit / withdraw --> amount --> quit --> eject card

    Admin --> Check total ammount --> Take/Put Money --> update money info

System Requirement:
    1. Insert 1 card
    2. Read card
    3. Verify password
    4. Deposit
        |-- Count Money
        |-- Verify Money
        |-- Store Money --> increase total money amount
    5. Withdraw
        |-- Check available money
        |-- Count Money
        |-- Eject money
    6. Quit account
    7. Eject card

User Type:
    1. Admin : put/take money from ATM
    2. User : deposit/withdraw money

Class Design:
    |--ATM
        |--WellsFargo ATM
    |--Account
        |--Admin
        |--User
    |--Card
        |--Debit
        |--Credit
    |--Money: 5, 10, 100

 */