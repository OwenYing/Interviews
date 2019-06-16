package DesignPattern.Builder;

public class Builder {
}

/*
resources:    https://java-design-patterns.com/patterns/builder/


Builder:
    1. Allows you to create different flavors of an object while avoiding constructor pollution.
    2. Useful when there could be several flavors of an object.
    3. Or when there are a lot of steps involved in creation of an object.
    4. Support chained call, JQuery has lots of it
    5. you don't need to worry about the parameters' order
    6. Use static inner class to group it or bound it with the target Entity Class
    7. The construction process can have more semantic meaning

 */

// Original Version : there will be so much parameters of constructor, as nums are growing, people messed up when construct an Object
public Hero(Profession profession, String name, HairType hairType, HairColor hairColor, Armor armor, Weapon weapon) {
}

// Builder Version
public final class Hero {
    private final Profession profession;
    private final String name;
    private final HairType hairType;
    private final HairColor hairColor;
    private final Armor armor;
    private final Weapon weapon;

    //1. Instead of accepting lots of parameters, here it only accept Builder
    public Hero(Builder builder) {
        this.profession = builder.profession;
        this.name = builder.name;
        this.hairColor = builder.hairColor;
        this.hairType = builder.hairType;
        this.weapon = builder.weapon;
        this.armor = builder.armor;
    }


    //2. Define the builder as inner static class, with returnning self methods.
    public static class Builder {                      // public static class can be only in other classes
        private final Profession profession;
        private final String name;
        private HairType hairType;
        private HairColor hairColor;
        private Armor armor;
        private Weapon weapon;

        //3. Use the most basic information to define the Builder constructor
        public Builder(Profession profession, String name) {
            if (profession == null || name == null) {
                throw new IllegalArgumentException("profession and name can not be null");
            }
            this.profession = profession;
            this.name = name;
        }

        //4. Self Returning: With understandable methods to define other parameters
        public Builder withHairType(HairType hairType) {
            this.hairType = hairType;
            return this;
        }

        public Builder withHairColor(HairColor hairColor) {
            this.hairColor = hairColor;
            return this;
        }

        public Builder withArmor(Armor armor) {
            this.armor = armor;
            return this;
        }

        public Builder withWeapon(Weapon weapon) {
            this.weapon = weapon;
            return this;
        }

        //5. Give a method to finally return the target Object
        public Hero build() {
            return new Hero(this);
        }
    }


}




// How to use?
Hero mage = new Hero.Builder(Profession.MAGE, "Riobard").withHairColor(HairColor.BLACK).withWeapon(Weapon.DAGGER).build();
//you don't need to worry about the parameters' order