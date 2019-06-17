


/*

resources:      https://java-design-patterns.com/patterns/bridge/

Bridge:
    1. Decouple an abstraction from its implementation so that the two can vary independently
    2. Bridge pattern is about preferring composition over inheritance. Implementation details are pushed from a hierarchy to another object with a separate hierarchy
    3. Template
        |-- A base class as an interface, a inserted class as an interface.
        |-- The subclass of base class has an reference of the inserted interface
        |-- The subclass call the interface's reference's method
        |-- Pass in different implementation of the interface into the subclass
        |-- Thus in different subclasses, the implementation is different.
    4. By doing this, we can combine different things separately

 */


public interface Weapon {
    void wield();
    void swing();
    void unwield();
    Enchantment getEnchantment();
}

public class Sword implements Weapon {

    private final Enchantment enchantment;  //1. The subclass should possess a reference of the interface

    public Sword(Enchantment enchantment) {
        this.enchantment = enchantment;     //2. In constructor, pass in the interface
    }

    @Override
    public void wield() {
        LOGGER.info("The sword is wielded.");       // here we can do individual things regarding to the weapon
        enchantment.onActivate();           //3. Invoke the methods of the interface
    }

    @Override
    public void swing() {
        LOGGER.info("The sword is swinged.");
        enchantment.apply();
    }

    @Override
    public void unwield() {
        LOGGER.info("The sword is unwielded.");
        enchantment.onDeactivate();
    }

    @Override
    public Enchantment getEnchantment() {
        return enchantment;
    }
}

public class Hammer implements Weapon {

    private final Enchantment enchantment;

    public Hammer(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public void wield() {
        LOGGER.info("The hammer is wielded.");
        enchantment.onActivate();
    }

    @Override
    public void swing() {
        LOGGER.info("The hammer is swinged.");
        enchantment.apply();
    }

    @Override
    public void unwield() {
        LOGGER.info("The hammer is unwielded.");
        enchantment.onDeactivate();
    }

    @Override
    public Enchantment getEnchantment() {
        return enchantment;
    }
}




public interface Enchantment {          //4. We define the interface with methods
    void onActivate();
    void apply();
    void onDeactivate();
}

public class FlyingEnchantment implements Enchantment {     //5. We implement the interface with different implementation

    @Override
    public void onActivate() {
        LOGGER.info("The item begins to glow faintly.");
    }

    @Override
    public void apply() {
        LOGGER.info("The item flies and strikes the enemies finally returning to owner's hand.");
    }

    @Override
    public void onDeactivate() {
        LOGGER.info("The item's glow fades.");
    }
}

public class SoulEatingEnchantment implements Enchantment {

    @Override
    public void onActivate() {
        LOGGER.info("The item spreads bloodlust.");
    }

    @Override
    public void apply() {
        LOGGER.info("The item eats the soul of enemies.");
    }

    @Override
    public void onDeactivate() {
        LOGGER.info("Bloodlust slowly disappears.");
    }
}






Sword enchantedSword = new Sword(new SoulEatingEnchantment());
enchantedSword.wield();
enchantedSword.swing();
enchantedSword.unwield();
// The sword is wielded.
// The item spreads bloodlust.
// The sword is swinged.
// The item eats the soul of enemies.
// The sword is unwielded.
// Bloodlust slowly disappears.

Hammer hammer = new Hammer(new FlyingEnchantment());
hammer.wield();
hammer.swing();
hammer.unwield();
// The hammer is wielded.
// The item begins to glow faintly.
// The hammer is swinged.
// The item flies and strikes the enemies finally returning to owner's hand.
// The hammer is unwielded.
// The item's glow fades.





