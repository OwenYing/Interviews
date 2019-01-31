package OOD.ATM;

import java.util.Map;

public abstract class ATM {
    private double TOTAL_AMOUNT;
    private boolean occupied;
    private Card insertedCard;
    private Map<Integer, Double> moneyTypeAmount;

    public abstract boolean insertCard(Card card);
    public abstract boolean readCard(Card card);
    public abstract boolean verifyPassword(String inputPassword);
    public abstract boolean deposit(double amount);
    public abstract boolean withdraw(double amount);
    public abstract boolean takeMoney(Money money, double amount);
    public abstract boolean ejectCard();

}


class Card {
    private CardType type;
    private CardInfo info;
}

enum CardType{
    Credit,
    Debit
}
class CardInfo {
    private CardType type;
    private String password;
}


class Money {
    public enum type{Quarters, One, Five, Ten, OneHundred}
    private int piece;
}