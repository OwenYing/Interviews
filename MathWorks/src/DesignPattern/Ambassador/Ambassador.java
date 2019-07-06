


/*

resource:           https://java-design-patterns.com/patterns/ambassador/


Ambassador:
    1. Controll access to another object.
    2. Very similar to Proxy Pattern by limitting the frequency and checking latency.
    3. Using the ambassador pattern, we can implement less-frequent polling from clients along with latency checks and logging.



 */


// Base interface
interface RemoteServiceInterface {
    long doRemoteFunction(int value) throws Exception;
}

// The slow legacy remote service, implemented using Singleton
public class RemoteService implements RemoteServiceInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteService.class);
    private static RemoteService service = null;

    static synchronized RemoteService getRemoteService() {
        if (service == null) {
            service = new RemoteService();
        }
        return service;
    }

    private RemoteService() {}

    @Override
    public long doRemoteFunction(int value) {

        long waitTime = (long) Math.floor(Math.random() * 1000);

        try {
            sleep(waitTime);
        } catch (InterruptedException e) {
            LOGGER.error("Thread sleep interrupted", e)
        }
        return waitTime >= 200 ? value * 10 : -1;
    }
}

// A service ambassador adding additional features such as logging, latency checks
public class ServiceAmbassador implements RemoteServiceInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAmbassador.class);
    private static final int RETRIES = 3;
    private static final int DELAY_MS = 3000;

    ServiceAmbassador() {}

    @Override
    public long doRemoteFunction(int value) {
        return safeCall(value);
    }

    private long safeCall(int value) {

        int retries = 0;
        long result = -1;

        for (int i = 0; i < RETRIES; i++) {

            if (retries >= RETRIES) {
                return -1;
            }

            if ((result = checkLatency(value)) == -1) {             // Get the result with tries
                LOGGER.info("Failed to reach remote: (" + (i + 1) + ")");
                retries++;
                try {
                    sleep(DELAY_MS);
                } catch (InterruptedException e) {
                    LOGGER.error("Thread sleep state interrupted", e);
                }
            } else {
                break;
            }
        }
        return result;
    }

    private long checkLatency(int value) {
        RemoteService service = RemoteService.getRemoteService();   // here get the RemoteService first
        long startTime = System.currentTimeMillis();
        long result = service.doRemoteFunction(value);
        long timeTaken = System.currentTimeMillis() - startTime;

        LOGGER.info("Time taken (ms): " + timeTaken);
        return result;
    }
}