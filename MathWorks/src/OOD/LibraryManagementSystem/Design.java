package OOD.LibraryManagementSystem;

public class Design {
}

/*
Resources: https://www.educative.io/collection/page/5668639101419520/5692201761767424/5636470266134528

User Scenario:

    1. User --> Borrow Book from Machine / Assistant --> Read ID card --> Read book ID --> check card deposit --> check book price --> deposit >= price
    --> Input time --> Relate book to ID card --> Demagnetize book

    2. User --> Return Book from Machine / Assistant --> Read card --> Read book ID --> check time --> Dis-relate book to ID card --> chick book price
    --> calculate deposit --> return deposit --> Magnetize book

    3. User --> search books by info

    4. Assistant --> Register book --> Delete book --> Register user --> Delete user --> Save deposit to user --> Check deposit of user --> Modify deposit of user

    5. Admin --> Register assistant --> Delete assistant --> do all assistant can do


Entity Type:
    |-- User
        |-- IDCard
            |-- UserType
            |-- Deposit
            |-- BookList
    |-- Assistant
        |-- IDCard
    |-- Admin
        |-- IDCard
    |-- Machine
        |-- BookReader
        |-- MoneyTaker
        |-- CardReader
    |-- Book
        |-- RackNumber
    |-- IDCard

 */