import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

// CLASSES
public class JumpTableMain {
    public static void main(String[] args) {
        Screen screen = new Screen();
        boolean keepRunning = true;
        while(keepRunning) {
            keepRunning = screen.doState();
        }
    }
}

public class Screen {

    // JUMP TABLES
    private HashMap<State, StateEnterExitMeth> stateEnterMeths;
    private HashMap<State, StateStayMeth> stateStayMeths;
    private HashMap<State, StateEnterExitMeth> stateExitMeths;

    // DATA STRUCTURES
    public ArrayList<Character> list;
    public Stack<Character> stack;
    public Queue<Character> queue;

    // STATE
    public State state;

    // CONSTRUCTOR
    public Screen(){
        // JUMP TABLES
        stateEnterMeths = new HashMap<State, StateEnterExitMeth>();
        stateStayMeths = new HashMap<State, StateStayMeth>();
        stateExitMeths = new HashMap<State, StateEnterExitMeth>();
        // DATA STRUCTURES
        list = new ArrayList<>();
        stack = new Stack<>();
        queue = new Queue<>();
        state = State.IDLE;
        // ADD THE METHODS TO THE JUMP TABLES
        // ENTER
        stateEnterMeths.put(State.IDLE, () => { StateEnterIdle(); });
        stateEnterMeths.put(State.LIST, () => { StateEnterList(); });
        stateEnterMeths.put(State.STACK, () => { StateEnterStack(); });
        stateEnterMeths.put(State.QUEUE, () => { StateEnterQueue(); });
        // STAY
        stateStayMeths.put(State.IDLE, () => { StateStayIdle(); });
        stateStayMeths.put(State.LIST, () => { StateStayList(); });
        stateStayMeths.put(State.STACK, () => { StateStayStack(); });
        stateStayMeths.put(State.QUEUE, () => { StateStayQueue(); });
        // EXIT
        stateExitMeths.put(State.IDLE, () => { StateExitIdle(); });
        stateExitMeths.put(State.LIST, () => { StateExitList(); });
        stateExitMeths.put(State.STACK, () => { StateExitStack(); });
        stateExitMeths.put(State.QUEUE, () => { StateExitQueue(); });
    }

    // ENTER
    private void StateEnterIdle() {

    }
    private void StateEnterStack() {

    }
    private void StateEnterQueue() {

    }
    private void StateEnterList() {

    }

    // STAY
    private boolean StateStayIdle() {

    }
    private boolean StateStayStack() {

    }
    private boolean StateStayQueue() {

    }
    private boolean StateStayList() {

    }

    // EXIT
    private void StateExitIdle() {

    }
    private void StateExitStack() {

    }
    private void StateExitQueue() {

    }
    private void StateExitList() {

    }

    // OTHERS
    public void doState(){
        if (stateEnterMeths.containsKey(state)){
            stateEnterMeths.get(state).invoke();
        }
        else if (stateStayMeths.containsKey(state)){
            stateStayMeths.get(state).invoke();
        }
        else if (stateExitMeths.containsKey(state)){
            stateExitMeths.get(state).invoke();
        }
    }
}

// INTERFACES
interface StateEnterExitMeth{
    void invoke();
}

interface StateStayMeth{
    boolean invoke();
}

// ENUMS
enum State{
    IDLE,
    STACK,
    QUEUE,
    LIST
}