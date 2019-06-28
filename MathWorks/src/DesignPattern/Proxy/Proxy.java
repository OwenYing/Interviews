


/*

resource:       https://java-design-patterns.com/patterns/proxy/


Proxy:
    1. There is an interface
    2. Use a bass class to implement the interface
    3. Use a proxy class to implement the interface, and obtains the bass class
    4. Thus, when other users uses it, firstly it should pass the proxy and then enter the bass class

 */


// The wizard tower and its object
public interface WizardTower {

    void enter(Wizard wizard);
}

public class IvoryTower implements WizardTower {

    private static final Logger LOGGER = LoggerFactory.getLogger(IvoryTower.class);

    public void enter(Wizard wizard) {
        LOGGER.info("{} enters the tower.", wizard);
    }

}

// The wizad class, which will enter the tower
public class Wizard {

    private final String name;

    public Wizard(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}



// The proxy, which wraps a Wizard Tower, and provide enter access control
public class WizardTowerProxy implements WizardTower {

    private static final Logger LOGGER = LoggerFactory.getLogger(WizardTowerProxy.class);

    private static final int NUM_WIZARDS_ALLOWED = 3;

    private int numWizards;

    private final WizardTower tower;

    public WizardTowerProxy(WizardTower tower) {
        this.tower = tower;
    }

    @Override
    public void enter(Wizard wizard) {                  //1. Here provides the outside access control
        if (numWizards < NUM_WIZARDS_ALLOWED) {
            tower.enter(wizard);                        //2. After passing the control, it can really enter the tower
            numWizards++;
        } else {
            LOGGER.info("{} is not allowed to enter!", wizard);
        }
    }
}



WizardTowerProxy proxy = new WizardTowerProxy(new IvoryTower());
proxy.enter(new Wizard("Red wizard")); // Red wizard enters the tower.
proxy.enter(new Wizard("White wizard")); // White wizard enters the tower.
proxy.enter(new Wizard("Black wizard")); // Black wizard enters the tower.
proxy.enter(new Wizard("Green wizard")); // Green wizard is not allowed to enter!
proxy.enter(new Wizard("Brown wizard")); // Brown wizard is not allowed to enter!
