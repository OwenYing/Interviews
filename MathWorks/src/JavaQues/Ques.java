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
 */