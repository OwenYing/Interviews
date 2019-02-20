package DesignPattern.Decorator;

public class Decorator {
}

/*
resources:    https://java-design-patterns.com/patterns/decorator/


Decorator:
    1. Also known as Wrapper
    2. Decorator pattern lets you dynamically change the behavior of an object at run time by wrapping them in an object of a decorator class.
    3. Have an original one inside a decorator --> call original --> add additional behavior
    4. They implement the same interface ; Enhanced one have Simple one's instance


 */


// Scenario
public interface Troll {
    void attack();
    int getAttackPower();
    void fleeBattle();
}

public class SimpleTroll implements Troll {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTroll.class);

    @Override
    public void attack() {
        LOGGER.info("The troll tries to grab you!");
    }

    @Override
    public int getAttackPower() {
        return 10;
    }

    @Override
    public void fleeBattle() {
        LOGGER.info("The troll shrieks in horror and runs away!");
    }
}

public class ClubbedTroll implements Troll {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClubbedTroll.class);

    private Troll decorated;

    public ClubbedTroll(Troll decorated) {
        this.decorated = decorated;
    }

    @Override
    public void attack() {
        decorated.attack();
        LOGGER.info("The troll swings at you with a club!");
    }

    @Override
    public int getAttackPower() {
        return decorated.getAttackPower() + 10;
    }

    @Override
    public void fleeBattle() {
        decorated.fleeBattle();
    }
}


// How to use ?
// simple troll
Troll troll = new SimpleTroll();
troll.attack(); // The troll tries to grab you!
troll.fleeBattle(); // The troll shrieks in horror and runs away!

// change the behavior of the simple troll by adding a decorator
Troll clubbedTroll = new ClubbedTroll(troll);
clubbedTroll.attack(); // The troll tries to grab you! The troll swings at you with a club!
clubbedTroll.fleeBattle(); // The troll shrieks in horror and runs away!







