package OOD.Restaurant;

import java.util.LinkedList;
import java.util.Queue;

public class Design {
}

/*

User Scenario:
    1. user come to front desk
        --> number of users
        --> wait in corresponding queue(different table size)
        --> give estimated waiting time, related to queue size
        --> take queue head when other table finished
        --> send text message when close to the front
        --> assign to a table
        --> take orders
        --> wait for orders
        --> eat
        --> finish eating
        --> pay the bill
        --> leave
    2. waiter get a group
        --> assign them to number n table
        --> mark this table occupied
        --> take order
        --> give food
        --> take bill
        --> finish
        --> mark table as dirty
        --> clean the table
        --> waiting for another group
    3. orders will be offer into a queue
        --> chief cook the order queue head
        --> chief finished and offer it to finished queue
        --> chief notify the waiter to take it

Object Design:
    |--Waiting Queue <Group>
    |--Group
        |--Adult Number
        |--Kid Number
    |--Table class --> 4 place table, 6 place table
        |--occupied
        |--dirty
        |--clean
    |--Order class
        |--Drink Order
        |--Food Order
    |--Bill
        |--final price
        |--tips
    |--Restaurant
        |-- Tuple<tabletype, tablenumber>
        |--Max waiting queue length
    |--Waiter
 */

class Restaurant {
    private List<Table> tables;
    private Map<Table, WaitingQueue> queues;
}
class Table {
    enum State {
        OCCUPIED, DIRTY, CLEANED;
    }
    private State currentState = State.CLEANED;
    private int tableID;
    private int sitNumber;
}
