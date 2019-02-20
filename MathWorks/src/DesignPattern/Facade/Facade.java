

/*
resources:    https://java-design-patterns.com/patterns/facade/


Facade:
    1. Facade pattern provides a simplified interface to a complex subsystem.. Hide complexity inside.
    2. Provide a easier default methods
    3. A facade can provide a simple default view of the subsystem that is good enough for most clients

 */

// Define upper entity class and extended entity classes
public abstract class DwarvenMineWorker {

    private static final Logger LOGGER = LoggerFactory.getLogger(DwarvenMineWorker.class);

    public void goToSleep() {
        LOGGER.info("{} goes to sleep.", name());
    }

    public void wakeUp() {
        LOGGER.info("{} wakes up.", name());
    }

    public void goHome() {
        LOGGER.info("{} goes home.", name());
    }

    public void goToMine() {
        LOGGER.info("{} goes to the mine.", name());
    }

    private void action(Action action) {
        switch (action) {
            case GO_TO_SLEEP:
                goToSleep();
                break;
            case WAKE_UP:
                wakeUp();
                break;
            case GO_HOME:
                goHome();
                break;
            case GO_TO_MINE:
                goToMine();
                break;
            case WORK:
                work();
                break;
            default:
                LOGGER.info("Undefined action");
                break;
        }
    }

    public void action(Action... actions) {
        for (Action action : actions) {
            action(action);
        }
    }

    public abstract void work();

    public abstract String name();

    static enum Action {
        GO_TO_SLEEP, WAKE_UP, GO_HOME, GO_TO_MINE, WORK
    }
}

public class DwarvenTunnelDigger extends DwarvenMineWorker {

    private static final Logger LOGGER = LoggerFactory.getLogger(DwarvenTunnelDigger.class);

    @Override
    public void work() {
        LOGGER.info("{} creates another promising tunnel.", name());
    }

    @Override
    public String name() {
        return "Dwarven tunnel digger";
    }
}

public class DwarvenGoldDigger extends DwarvenMineWorker {

    private static final Logger LOGGER = LoggerFactory.getLogger(DwarvenGoldDigger.class);

    @Override
    public void work() {
        LOGGER.info("{} digs for gold.", name());
    }

    @Override
    public String name() {
        return "Dwarf gold digger";
    }
}

public class DwarvenCartOperator extends DwarvenMineWorker {

    private static final Logger LOGGER = LoggerFactory.getLogger(DwarvenCartOperator.class);

    @Override
    public void work() {
        LOGGER.info("{} moves gold chunks out of the mine.", name());
    }

    @Override
    public String name() {
        return "Dwarf cart operator";
    }
}


// Operate Facade: to provide default operations on these entities.
public class DwarvenGoldmineFacade {

    private final List<DwarvenMineWorker> workers;

    public DwarvenGoldmineFacade() {
        workers = new ArrayList<>();
        workers.add(new DwarvenGoldDigger());
        workers.add(new DwarvenCartOperator());
        workers.add(new DwarvenTunnelDigger());
    }

    public void startNewDay() {
        makeActions(workers, DwarvenMineWorker.Action.WAKE_UP, DwarvenMineWorker.Action.GO_TO_MINE);
    }

    public void digOutGold() {
        makeActions(workers, DwarvenMineWorker.Action.WORK);
    }

    public void endDay() {
        makeActions(workers, DwarvenMineWorker.Action.GO_HOME, DwarvenMineWorker.Action.GO_TO_SLEEP);
    }

    private static void makeActions(Collection<DwarvenMineWorker> workers,
                                    DwarvenMineWorker.Action... actions) {
        for (DwarvenMineWorker worker : workers) {
            worker.action(actions);
        }
    }
}


// How to use?    Hide the complex operation underneath and provide easier operations
DwarvenGoldmineFacade facade = new DwarvenGoldmineFacade();
facade.startNewDay();
// Dwarf gold digger wakes up.
// Dwarf gold digger goes to the mine.
// Dwarf cart operator wakes up.
// Dwarf cart operator goes to the mine.
// Dwarven tunnel digger wakes up.
// Dwarven tunnel digger goes to the mine.
facade.digOutGold();
// Dwarf gold digger digs for gold.
// Dwarf cart operator moves gold chunks out of the mine.
// Dwarven tunnel digger creates another promising tunnel.
facade.endDay();
// Dwarf gold digger goes home.
// Dwarf gold digger goes to sleep.
// Dwarf cart operator goes home.
// Dwarf cart operator goes to sleep.
// Dwarven tunnel digger goes home.
// Dwarven tunnel digger goes to sleep.