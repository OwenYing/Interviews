


/*

resource:       https://java-design-patterns.com/patterns/flyweight/

Flyweight:
    1. Use Map to share common objects, to support large numbers of fine-grained objects efficiently.
    2. Use Map to store already created objects, return them when application calls them.
    3. Each type of object only be created once.
    4. To use this pattern, the application doesn't depend on object identity. Since flyweight objects may be shared, identity
        tests will return true for conceptually distinct objects.
 */


public interface Potion {
    void drink();
}

public class HealingPotion implements Potion {
    private static final Logger LOGGER = LoggerFactory.getLogger(HealingPotion.class);
    @Override
    public void drink() {
        LOGGER.info("You feel healed. (Potion={})", System.identityHashCode(this));
    }
}

public class HolyWaterPotion implements Potion {
    private static final Logger LOGGER = LoggerFactory.getLogger(HolyWaterPotion.class);
    @Override
    public void drink() {
        LOGGER.info("You feel blessed. (Potion={})", System.identityHashCode(this));
    }
}

public class InvisibilityPotion implements Potion {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvisibilityPotion.class);
    @Override
    public void drink() {
        LOGGER.info("You become invisible. (Potion={})", System.identityHashCode(this));
    }
}


// The following is flyweight factory which shares the objects
public class PotionFactory {

    private final Map<PotionType, Potion> potions; //1. Create a map to store the objects

    public PotionFactory() {
        potions = new EnumMap<>(PotionType.class);
    }

    Potion createPotion(PotionType type) {
        Potion potion = potions.get(type);          //2. If the object has been created, return it
        if (potion == null) {                       //3. If the object hasn't been created, create it and store it into the map
            switch (type) {
                case HEALING:
                    potion = new HealingPotion();
                    potions.put(type, potion);
                    break;
                case HOLY_WATER:
                    potion = new HolyWaterPotion();
                    potions.put(type, potion);
                    break;
                case INVISIBILITY:
                    potion = new InvisibilityPotion();
                    potions.put(type, potion);
                    break;
                default:
                    break;
            }
        }
        return potion;
    }
}


// Although it is creating, but they are same objects -- By doing this, we save the memeory
PotionFactory factory = new PotionFactory();
factory.createPotion(PotionType.INVISIBILITY).drink(); // You become invisible. (Potion=6566818)
factory.createPotion(PotionType.HEALING).drink(); // You feel healed. (Potion=648129364)
factory.createPotion(PotionType.INVISIBILITY).drink(); // You become invisible. (Potion=6566818)
factory.createPotion(PotionType.HOLY_WATER).drink(); // You feel blessed. (Potion=1104106489)
factory.createPotion(PotionType.HOLY_WATER).drink(); // You feel blessed. (Potion=1104106489)
factory.createPotion(PotionType.HEALING).drink(); // You feel healed. (Potion=648129364)