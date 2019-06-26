

/*

resources:      https://java-design-patterns.com/patterns/prototype/


Prototype:
    1. It allows you to create a copy of an existing object and modify it to your needs,
        instead of going through the trouble of creating an object from scratch and setting it up.
    2. Need to implement Cloneable and overriding clone from object.
    3. By cloning the prototype, we don't need to set all the same attributes again.

 */

class Sheep implements Cloneable {
    private String name;
    public Sheep(String name) { this.name = name; }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    @Override
    public Sheep clone() throws CloneNotSupportedException {
        return new Sheep(name);
    }
}



Sheep original = new Sheep("Jolly");
System.out.println(original.getName()); // Jolly

// Clone and modify what is required
Sheep cloned = original.clone();
cloned.setName("Dolly");
System.out.println(cloned.getName()); // Dolly