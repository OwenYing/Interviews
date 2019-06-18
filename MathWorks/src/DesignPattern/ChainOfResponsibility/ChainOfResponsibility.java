



/*

resources:      https://java-design-patterns.com/patterns/chain/

Chain of Responsibility:
    1. Linked List like.
    2. Pass request along the node: if current node can handle it, let it handle. if not, pass to the next node.
    3. Chain the receiving objects and pass the request along the chain until an object handles it.
    4. you want to issue a request to one of several objects without specifying the receiver explicitly
    5. the set of objects that can handle a request should be specified dynamically



 */

// Request Class
public class Request {
    private final RequestType requestType;
    private final String requestDescription;
    private boolean handled;

    public Request(final RequestType requestType, final String requestDescription) {
        this.requestType = Objects.requireNonNull(requestType);
        this.requestDescription = Objects.requireNonNull(requestDescription);
    }

    public String getRequestDescription() { return requestDescription; }

    public RequestType getRequestType() { return requestType; }

    public void markHandled() { this.handled = true; }

    public boolean isHandled() { return this.handled; }

    @Override
    public String toString() { return getRequestDescription(); }
}

// Request Types
public enum RequestType {
    DEFEND_CASTLE, TORTURE_PRISONER, COLLECT_TAX
}

// Request Handler --> Linked List liked.
public abstract class RequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestHandler.class);

    private RequestHandler next;  // the pointer to the next node
    // Hook the linked list
    public RequestHandler(RequestHandler next) {
        this.next = next;
    }

    // Default pass handler function: pass the responsibility to the next node
    public void handleRequest(Request req) {
        if(next != null)
            next.handleRequest(req);
    }

    protected void printHandling(Request req) {
        LOGGER.info("{} handling request \"{}\"", this, req);
    }

    @Override
    public abstract String toString();

}

// One instance Request Handler
public class OrcCommander extends RequestHandler {
    public OrcCommander(RequestHandler handler) {
        super(handler);
    }

    @java.lang.Override
    public void handleRequest(Request req) {
        // If this kind of node can handle the request, let it handle
        if(req.getRequestType().equals(RequestType.DEFEND_CASTLE)) {
            printHandling(req);
            req.markHandled();
        } else {
            super.handleRequest(req);   // If cannot handle, then pass to the next node
        }
    }

    @Override
    public String toString() {
        return "Orc commander";
    }
}


// Use the Request and the Request Handler
public class OrcKing {
    RequestHandler chain;

    public OrcKing() {
        buildChain();
    }

    private void buildChain() {
        chain = new OrcCommander(new OrcOfficer(new OrcSoldier(null)));
    }

    public void makeRequest(Request req) {
        chain.handleRequest(req);
    }
}


OrcKing king = new OrcKing();
king.makeRequest(new Request(RequestType.DEFEND_CASTLE, "defend castle")); // Orc commander handling request "defend castle"
king.makeRequest(new Request(RequestType.TORTURE_PRISONER, "torture prisoner")); // Orc officer handling request "torture prisoner"
king.makeRequest(new Request(RequestType.COLLECT_TAX, "collect tax")); // Orc soldier handling request "collect tax"

