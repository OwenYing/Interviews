package OOD.ParkingLot;

public class Design {
}


/*
System Requirements:
    1. Multiple floors
    2. Multiple entry and exit points
    3. Collect ticket --> entry ; Pay ticket --> exit
    4. Pay --> automated or parking attendant
    5. Pay --> cash or credit card
    6. Vehicles <= max capacity
    7. Each floor have many parking spots
    8. Display board to display any available parking spot

Use case requirement:
    |-- Users
        1. Admin
        2. Parking attendant
        3. System
        4. Customer
    |-- Use case
        1. Add/Remove/Edit paring floor
        2. Add/Remove/Edit parking spot
        3. Add/Remove a parking attendant
        4. Take ticket
        5. Scan ticket
        6. Credit card payment
        7. Cash payment

Class Diagram:
    1. ParkingLot
    2. ParkingFloor
    3. ParkingSpot
    4. Account
    5. Ticket
    6. Vehicle
    7. EntrancePanel / ExitPanel
    8. Payment
    9. ParkingDisplayBoard
    10. ParkingAttendantPortal
    11. CustomerInfoPortal


 */