package DesignPattern.Singletons;

public class Singletons {

}


//1. Eager init
class Singleton1 {
    //1. Private static instance
    private static Singleton1 instance = new Singleton1(); //eagerness
    //2. Private constructor
    private Singleton1() {
        // init
    }
    //3. public static getter
    public static Singleton1 getInstance() {
        return instance;
    }
}

//2. Lazy init
class Singleton2 {
    private static Singleton2 instance = null;
    private Singleton2() {

    }
    public static Singleton2 getInstance() {
        if(instance == null)  // laziness
            instance = new Singleton2();
        return instance;
    }
}

//3. Thread Safe Lazy init
class Singleton3 {
    private static Singleton3 instance = null;
    private Singleton3() {

    }
    public static synchronized Singleton3 getInstance() { // thread safety
        if(instance == null)
            instance = new Singleton3();
        return instance;
    }
}


//4. Double checked locking
class Singleton4 {
    private volatile static Singleton4 instance = null;
    private Singleton4() {

    }
    public static Singleton4 getInstance() {
        if(instance == null) {
            synchronized (this) {
                if(instance == null)
                    instance = new Singleton4();
            }
        }
        return instance
    }
}

//5. Using enum
public enum Singleton5 {
    INSTANCE;
}