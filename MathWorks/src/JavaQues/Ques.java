package JavaQues;

import java.util.concurrent.Semaphore;

public class Ques {
}


/*
1. Java destructor ? How to force it?
    |-- protected void finalize();
    |-- System.gc();    Runtime.getRuntime().gc();
2. How Java GC works?  Is it guaranteed to work?
    |-- Live objects are tracked and everything else designated garbage.
        |-- Tree structure with GC Root
        |-- When an object is no longer used, the garbage collector reclaims the underlying memory and reuses it for future object allocation.
            This means there is no explicit deletion and no memory is given back to the operating system.
    |-- Usually Yes, but one scenario: the programmer forgot to de-reference the object.
    |-- GC deals with Heap memory, follow isolated islands principle
3. Synchronized
    class Sender
    {
        public void send(String msg)
        {
            synchronized(this)
            {
                System.out.println("Sending\t" + msg );
                try
                {
                    Thread.sleep(1000);
                }
                catch (Exception e)
                {
                    System.out.println("Thread interrupted.");
                }
                System.out.println("\n" + msg + "Sent");
            }
        }
    }
4. "==" vs equals()
    |-- "==" is an operator, "equals()" is a method
    |-- "==" compare both primitive and objects, "equals()" compares objects
    |-- "==" compare whether is the same reference, "equals()" compares by business logic
5. What happens when compiling and running Java code?
    |-- Compiling: .java file -> compiler -> bytecode(.class)
    |-- Running: bytecode -> JVM (Class Loader, Bytecode Verifier, Just-In-Time Compiler) -> Machine Code
6. Hashtable vs HashMap
    |-- Hashtable is synchronized , HashMap is not
    |-- Hashtable doesn't allow null keys/values , HashMap allows 1 null key and any number of null value
    |-- HashMap can switch to LinkedHashMap easily
7. Polymorphism: see below
8. Java Generics: see below
9. LinkedList vs ArrayList
10. You have some classes and want to use them in another project?
    |-- .jar files and import them to the project
11. Why java Generic?
    |-- A type of method to operate on objects of various type while providing compiling time type safety that allows programmers to catch invalid types at compile time.
12. Difference between stack memory and heap memory?
    |-- Stack is used for static memory allocation and Heap is used for dynamic memory allocation.
    |-- Stack Compile time; Heap Run time
    |-- Stack fast; Heap slow
    |-- For multithreads, each thread has own Stack, but shared Heap
    |-- Stack (value, method, with scope) ; Heap (pointers, reference, without scope)
13. Multi-threading
14. OOP principles: Encapsulation, inheritance, polymorphism
 */



//===================== Polymorphism Example =========================
class Main {
    public static void main(String[] args) {
        Son s = new Son();
        s.outputPrivate();
        s.increaseA();
        s.outputPrivate();
        s.outputProtected();
        s.outputPrivate();
        System.out.println(s.getA());
        /* Output:
            private: 100
            private: 101
            protected: 1001
            private: 101
            -100
         */
    }
}

class Parent {
    private int a = 100;
    protected int b = 1001;
    public void outputPrivate() {
        System.out.println("private: " + a);
    }
    public void increaseA() {
        a++;
    }
    public int getA() {
        return this.a;
    }
}
class Son extends Parent{
    int a = -100;
    public void outputProtected() {
        System.out.println("protected: " + this.b);
    }
    public int getA() {
        return a;
    }
}

//===================== Java Generics Example ========================
//1. <E> right before return type
//2. Parameters cannot be primitive types, must be reference types

public static <E> void printArray( E[] inputArray ) {
    // Display array elements
    for(E element : inputArray) {
        System.out.printf("%s ", element);
    }
    System.out.println();
}

//Bounded Type Parameters: There may be times when you'll want to restrict the kinds of types that are allowed to be passed to a type parameter.
public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
    T max = x;   // assume x is initially the largest

    if(y.compareTo(max) > 0) {
        max = y;   // y is the largest so far
    }

    if(z.compareTo(max) > 0) {
        max = z;   // z is the largest now
    }
    return max;   // returns the largest object
}

// Generic Class
class Box<T> {
    private T t;

    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<Integer>();
        Box<String> stringBox = new Box<String>();

        integerBox.add(new Integer(10));
        stringBox.add(new String("Hello World"));

        System.out.printf("Integer Value :%d\n\n", integerBox.get());
        System.out.printf("String Value :%s\n", stringBox.get());
    }
}


//===================== Multithreading Example ========================
//a. If extends from Thread --> start()
//   If implements Runnable --> new Thread(runnable).start()
//b. Difference
//      extends Threads --> only allow one extend, so only if the class main
//                          functionality related to thread, use this
//      implement Runnable --> since multi-implements, this adds more flexibility.
//1. extends Thread
class MultithreadingDemo extends Thread {
    public void run() {
        try {
            // Displaying the thread that is running
            System.out.println ("Thread " + Thread.currentThread().getId() + " is running");
        }
        catch (Exception e) {
            System.out.println ("Exception is caught");
        }
    }
}

class Multithread {
    public static void main(String[] args) {
        int n = 8; // Number of threads
        for (int i=0; i<8; i++) {
            MultithreadingDemo object = new MultithreadingDemo();
            object.start();
        }
    }
}

//2. implements Runnable
class MultithreadingDemo2 implements Runnable {
    public void run() {
        try {
            // Displaying the thread that is running
            System.out.println ("Thread " + Thread.currentThread().getId() + " is running");
        }
        catch (Exception e) {
            System.out.println ("Exception is caught");
        }
    }
}

class Multithread2 {
    public static void main(String[] args) {
        int n = 8; // Number of threads
        for (int i=0; i<8; i++) {
            Thread object = new Thread(new MultithreadingDemo2());
            object.start();
        }
    }
}

//3. Anonymous
class Test {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println("1111");
            }
        });
        t.start();

        Thread q = new Thread(()-> {
            System.out.println("aaaaa");
        });
        q.start();
    }
}