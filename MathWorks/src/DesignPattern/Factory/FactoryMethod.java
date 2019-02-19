package DesignPattern.Factory;

public class FactoryMethod {
}

/*
resources:    https://java-design-patterns.com/patterns/factory-method/


Factory Method:
    1. Virtual Constructor
    2. Factory Method lets a class defer instantiation to subclasses
 */

//With same weapon type, ElfBlacksmith will create Elf weapon style; OrcBlacksmith will create Orc style
public interface Blacksmith {
    Weapon manufactureWeapon(WeaponType weaponType);
}

public class ElfBlacksmith implements Blacksmith {
    public Weapon manufactureWeapon(WeaponType weaponType) {
        return new ElfWeapon(weaponType);
    }
}

public class OrcBlacksmith implements Blacksmith {
    public Weapon manufactureWeapon(WeaponType weaponType) {
        return new OrcWeapon(weaponType);
    }
}

// How to use?
Blacksmith blacksmith = new ElfBlacksmith(); // Elf style weapon is created
blacksmith.manufactureWeapon(WeaponType.SPEAR);
blacksmith.manufactureWeapon(WeaponType.AXE);