package DesignPattern.Adapter;

public class Adapter {
}

/*
resources:    https://java-design-patterns.com/patterns/adapter/


Adapter:
    1. Also known as Wrapper
    2. Adapter pattern lets you wrap an otherwise incompatible object in an adapter to make it compatible with another class.
    3. Adapter inorder to plug A --> B , Adapter implements B, Adapter has A's instance, Called B's Adapter

 */


// Scenario : Rowing boat can only row; Fishing boat can only sail
public interface RowingBoat {
    void row();
}

public class FishingBoat {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishingBoat.class);
    public void sail() {
        LOGGER.info("The fishing boat is sailing");
    }
}

// Captain implements Rowing boat
public class Captain implements RowingBoat {

    private RowingBoat rowingBoat;

    public Captain(RowingBoat rowingBoat) {             //1. Captain allows RowingBoat to plug in
        this.rowingBoat = rowingBoat;
    }

    @Override
    public void row() {
        rowingBoat.row();
    }
}

// Captain wants to sail
public class FishingBoatAdapter implements RowingBoat { //2. One side of Adapter is RowingBoat

    private static final Logger LOGGER = LoggerFactory.getLogger(FishingBoatAdapter.class);

    private FishingBoat boat;                           //3. One side of Adapter is FishingBoat

    public FishingBoatAdapter() {
        boat = new FishingBoat();
    }

    @Override
    public void row() {                                 //4. Connect inside Adapter and outside Adapter
        boat.sail();
    }
}


// How to use?
Captain captain = new Captain(new FishingBoatAdapter());   //5. Plug the Adapter into the class
captain.row();




// Another example
public interface TwoPinSocket {
    public void chargeTwo();
}
public interface ThreePinSocket {
    public void safe();
    public void chargeThree();
}
public class TwoAdapter implements TwoPinSocket {   //1. To help three plug into two. (help my Mac's three plug into the wall's two)
    ThreePinSocket three;                           //2. A three inside the adapter
    public TwoAdapter(ThreePinSocket plugMacIn) {
        three = plugMacIn;
    }
    @Override
    public void chargeTwo() {                      //3. Connect from three to two inside the adapter
        three.safe();
        three.chargeThree();
    }
}

class MyMac implements ThreePinSocket {
    public void safe(){
        System.out.println("Mac is safe to charge");
    }
    public void chargeThree() {
        System.out.println("Mac is charging");
    }
}
class Wall implements TwoPinSocket {
    TwoPinSocket socket;
    public Wall(TwoPinSocket plugin) {
        socket = plugin;
    }
    public void chargeTwo() {
        socket.chargeTwo();
    }
}

TwoPinSocket threeToTwoAdapter = new TwoAdapter(new MyMac());  // plug my mac into the adapter:    MyMac --> Adapter
TwoPinSocket wall = new Wall(threeToTwoAdapter);               // plug the adapter into wall:      Adapter --> Wall