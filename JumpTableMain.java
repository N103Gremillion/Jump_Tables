import java.io.*;
import java.util.*;

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

class Screen {

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
        queue = new LinkedList<>();
        state = State.IDLE;
        // ADD THE METHODS TO THE JUMP TABLES
        // ENTER
        stateEnterMeths.put(State.IDLE, this::StateEnterIdle);
        stateEnterMeths.put(State.LIST, this::StateEnterList);
        stateEnterMeths.put(State.STACK, this::StateEnterStack);
        stateEnterMeths.put(State.QUEUE, this::StateEnterQueue);
        // STAY
        stateStayMeths.put(State.IDLE, this::StateStayIdle);
        stateStayMeths.put(State.LIST, this::StateStayList);
        stateStayMeths.put(State.STACK, this::StateStayStack);
        stateStayMeths.put(State.QUEUE, this::StateStayQueue);
        // EXIT
        stateExitMeths.put(State.IDLE, this::StateExitIdle);
        stateExitMeths.put(State.LIST, this::StateExitList);
        stateExitMeths.put(State.STACK, this::StateExitStack);
        stateExitMeths.put(State.QUEUE, this::StateExitQueue);
    }

    // ENTER
    private void StateEnterIdle() {

    }
    private void StateEnterStack(){
        /* READ FROM THE STACK FILE (Note: I KNOW THIS IS NOT FOLLOWING THE DRY PRINCIPLE BUT
        I FIGURED SINCE THE METHODS FOR EACH DATA STRUCTURE ARE DIFFERENT IT IS OK)*/
        try {
            BufferedReader stackReader = new BufferedReader(new FileReader("stack.txt"));
            int ascii;
            // CONTINUE READING UNTIL YOU HIT THE END OF FILE AKA(-1)
            while ((ascii = stackReader.read()) != -1){
                // CONVERT THE ASCII TO ITS CHARACTER AND FILL THE STACK WITH WHAT IS IN THE CURRENT FILE
                Character character = (char) ascii;
                if (!character.equals(',')){
                    stack.push(character);
                }
            }
            // CLOSE THE READER
            stackReader.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }
    private void StateEnterQueue() {
        /* READ FROM THE QUEUE FILE (Note: I KNOW THIS IS NOT FOLLOWING THE DRY PRINCIPLE BUT
        I FIGURED SINCE THE METHODS FOR EACH DATA STRUCTURE ARE DIFFERENT IT IS OK)*/
        try {
            BufferedReader queueReader = new BufferedReader(new FileReader("queue.txt"));
            int ascii;
            // CONTINUE READING UNTIL YOU HIT THE END OF FILE AKA(-1)
            while ((ascii = queueReader.read()) != -1){
                // CONVERT THE ASCII TO ITS CHARACTER AND FILL THE STACK
                Character character = (char) ascii;
                if (!character.equals(',')){
                    queue.add(character);
                }
            }
            // CLOSE THE READER
            queueReader.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }
    private void StateEnterList() {
        /* READ FROM THE LIST FILE (Note: I KNOW THIS IS NOT FOLLOWING THE DRY PRINCIPLE BUT
        I FIGURED SINCE THE METHODS FOR EACH DATA STRUCTURE ARE DIFFERENT IT IS OK)*/
        try {
            BufferedReader listReader = new BufferedReader(new FileReader("list.txt"));
            int ascii;
            // CONTINUE READING UNTIL YOU HIT THE END OF FILE AKA(-1)
            while ((ascii = listReader.read()) != -1){
                // CONVERT THE ASCII TO ITS CHARACTER AND FILL THE STACK
                Character character = (char) ascii;
                if (!character.equals(',')){
                    list.add(character);
                }
            }
            // CLOSE THE READER
            listReader.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }

    // STAY
    private boolean StateStayIdle() {
        // GET INPUT USING SYSTEM.IN
        String selection = """
                1. Stack
                2. Queue
                3. List
                4. Quit
                ?""";
        System.out.print(selection + " ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("1")){
            // GO TO STACK
            changeState(State.STACK);
        }
        else if (input.equals("2")){
            // GO TO QUEUE
            changeState(State.QUEUE);
        }
        else if (input.equals("3")){
            // GO TO LIST
            changeState(State.LIST);
        }
        else if (input.equals("4")){
            // QUIT
            return false;
        }
        return true;
    }
    private boolean StateStayStack() {
        // DRAW CURRENT STACK
        drawStack(stack);
        // GET INPUT USING SYSTEM.IN
        String selection = """
                1. Push
                2. Pop
                3. Save & Move to Queue
                4. Save & Move to List
                5. Quit
                ? """;
        System.out.print(selection + " ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.charAt(0) == '1'){
            // PUSH
            if (input.length() == 3 && input.charAt(1) == ' '){
                stack.push(input.charAt(2));
            }
        }
        else if (input.equals("2")){
            // POP
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }
        else if (input.equals("3")){
            // GO TO QUEUE
            changeState(State.QUEUE);
        }
        else if (input.equals("4")){
            // GO TO LIST
            changeState(State.LIST);
        }
        else if (input.equals("5")){
            // QUIT
            return false;
        }
        return true;
    }
    private boolean StateStayQueue() {
        // DRAW CURRENT QUEUE
        drawQueue(queue);
        // GET INPUT USING SYSTEM.IN
        String selection = """
                1. Enqueue
                2. Dequeue
                3. Save & Move to Stack
                4. Save & Move to List
                5. Quit
                ? """;
        System.out.print(selection + " ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.charAt(0) == '1'){
            // PUSH
            if (input.length() == 3 && input.charAt(1) == ' '){
                queue.add(input.charAt(2));
            }
        }
        else if (input.equals("2")){
            // POP
            if (!queue.isEmpty()) {
                queue.remove();
            }
        }
        else if (input.equals("3")){
            // GO TO STACK
            changeState(State.STACK);
        }
        else if (input.equals("4")){
            // GO TO LIST
            changeState(State.LIST);
        }
        else if (input.equals("5")){
            // QUIT
            return false;
        }
        return true;
    }
    private boolean StateStayList() {
        // DRAW CURRENT LIST
        drawList(list);
        // GET INPUT USING SYSTEM.IN
        String selection = """
                1. Append
                2. Remove
                3. Save & Move to Stack
                4. Save & Move to Queue
                5. Quit
                ? """;
        System.out.print(selection + " ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.charAt(0) == '1'){
            // APPEND
            if (input.length() == 3 && input.charAt(1) == ' '){
                list.add(input.charAt(2));
            }
        }
        else if (input.equals("2")){
            // REMOVE LAST ELEMENT
            if (!list.isEmpty()) {
                list.remove(list.size() - 1);
            }
        }
        else if (input.equals("3")){
            // GO TO STACK
            changeState(State.STACK);
        }
        else if (input.equals("4")){
            // GO TO QUEUE
            changeState(State.QUEUE);
        }
        else if (input.equals("5")){
            // QUIT
            return false;
        }
        return true;
    }

    // EXIT
    private void StateExitIdle() {

    }
    private void StateExitStack() {
        // OVERWRITE ALL THE CONTENTS IN THE CURRENT STACK(file) AND SAVE
        try {
            // GET CONTENTS IN STACK
            String elements = "";
            BufferedWriter stackWriter = new BufferedWriter(new FileWriter("stack.txt"));
            Stack<Character> temp = new Stack<>();
            // TRANSFER ELEMENTS IN OPPOSITE ORDER TO TEMP
            while (!stack.isEmpty()){
                temp.push(stack.pop());
            }
            // ADD ELEMENTS TO THE STRING IN CORRECT ORDER
            while(!temp.isEmpty()){
                Character current = temp.pop();
                elements += (current + ",");
            }
            // WRITE TO FILE
            stackWriter.write(elements);
            if (stackWriter != null) {
                stackWriter.close();
            }
        }
        catch (IOException error){
            error.printStackTrace();
        }
        stack.removeAllElements();
    }
    private void StateExitQueue() {
        // OVERWRITE ALL THE CONTENTS IN THE CURRENT QUEUE(file) AND SAVE
        try {
            // GET CONTENTS IN QUEUE
            String elements = "";
            BufferedWriter queueWriter = new BufferedWriter(new FileWriter("queue.txt"));
            Queue<Character> temp = new LinkedList<>();
            temp.addAll(queue);
            // ADD ELEMENTS TO THE STRING IN CORRECT ORDER
            while(!temp.isEmpty()){
                Character current = temp.remove();
                elements += (current + ",");
            }
            // WRITE TO FILE
            queueWriter.write(elements);
            if (queueWriter != null) {
                queueWriter.close();
            }
        }
        catch (IOException error){
            error.printStackTrace();
        }
        queue.clear();
    }
    private void StateExitList() {
        // OVERWRITE ALL THE CONTENTS IN THE CURRENT LIST(file) AND SAVE
        try {
            // GET CONTENTS IN LIST
            String elements = "";
            BufferedWriter listWriter = new BufferedWriter(new FileWriter("list.txt"));
            // ADD ELEMENTS TO THE STRING IN CORRECT ORDER
            for (int i = 0; i < list.size(); i++) {
                elements += (list.get(i) + ",");
            }
            // WRITE TO FILE
            listWriter.write(elements);
            if (listWriter != null) {
                listWriter.close();
            }
        }
        catch (IOException error){
            error.printStackTrace();
        }
        list.clear();
    }

    // METHODS TO CHECK STATE AND INVOKE APPROPRIATE FUNCTION
    public boolean doState() {
        if (stateStayMeths.containsKey(state)) {
            return stateStayMeths.get(state).invoke();
        }
        return false;
    }
    public void changeState(State newState){
        if (state != newState) {
            // EXIT CURRENT STATE
            if (stateExitMeths.containsKey(state)) {
                stateExitMeths.get(state).invoke();
            }
            state = newState;
            // ENTER NEW STATE
            if (stateEnterMeths.containsKey(newState)) {
                stateEnterMeths.get(newState).invoke();
            }
        }
    }

    // DRAW METHODS
    public static void drawStack(Stack<Character> stack){
        // COPY ELEMENTS OF STACK SO IT IS NOT MODIFIED WHILE DRAWING
        Stack<Character> temp = new Stack<>();
        temp.addAll(stack);
        System.out.println("|   |");
        while (!temp.isEmpty()){
            System.out.println("|---|");
            System.out.println(String.format("| %c |", temp.pop()));
        }
        System.out.println("|---|");
    }
    public static void drawQueue(Queue<Character> queue){
        // COPY ELEMENTS OF QUEUE AND PRINT
        Queue<Character> temp = new LinkedList<>();
        temp.addAll(queue);
        System.out.print("| ");
        while (!temp.isEmpty()){
            System.out.print(temp.remove() + " | ");
        }
        System.out.print('\n');
    }
    public static void drawList(ArrayList<Character> list){
        // LOOP THROUGH AND PRINT THE CURRENT LIST
        System.out.print("{ ");
        for (int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + ", ");
        }
        System.out.print("}\n");
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