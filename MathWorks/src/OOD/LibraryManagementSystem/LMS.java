package OOD.LibraryManagementSystem;

public class LMS {
    private List<Book> bookList;
    private List<Account> accountList;
}

abstract class Account {
    private String username;
    private String password;
    private String accountType;
    private IDCard card;

}

class User extends Account {
    private Address address;
    private PhoneNumber number;
    private Email email;
    private Money deposit;
    public boolean borrowBook(Book book){
        return true;
    }
    public Money saveDeposit(Money amount) {
        deposit.add(amount);
        return deposit;
    }
    public Money checkDeposit() {
        return deposit;
    }
    public boolean returnBook(Book book) {
        return true;
    }
}

class Assistant extends Account {
    public User createUser(String username, String password, Address address ...) {

    }
    public IDCard registerUser(User user) {

    }
    public boolean deleteUser(User user) {

    }
    public boolean registerBook(Book book) {

    }
    public boolean deleteBook(Book book) {

    }
    public Money modifyUserDeposit(User user, Money amount) {

    }
}

class Admin extends Account {  // almost same with Assistant

}

class Machine {
    private boolean isOccupied;
    private IDCard insertedCard;
    private CardReader cardReader;
    private BookReader bookReader;
    private MoneyTaker moneyTaker;
    public boolean verifyUser(String username, String password, IDCard card) {

    }
    public CardInfo readCard(IDCard card) {

    }
    public BookInfo readBook(Book book) {

    }
    public Money takeMoney(Money amount) {

    }
    public boolean borrowBook(Book book) {

    }
    public boolean returnBook(Book book) {

    }
}

class BookReader {
    public void demagnetizeBook(Book book) {

    }
    public void magnetizeBook(Book book) {

    }
}

class Book {
    private String bookname;
    private String bookID;
    private String bookType;
    private String lendingTime;
    private String bookStatus;
}

class IDCard {
    private Stripe stripe;
    private String cardNumber;
    private User user;
}