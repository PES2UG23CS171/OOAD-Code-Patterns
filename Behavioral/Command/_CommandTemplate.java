// COMMAND PATTERN TEMPLATE
// Use when: You want to encapsulate a request as an object (allows undo, queuing, logging).

// 1. Command Interface
interface Command {
    void execute();
}

// 2. Receiver (The object that actually knows how to perform the work)
class Receiver {
    public void actionA() { System.out.println("Receiver does Action A"); }
    public void actionB() { System.out.println("Receiver does Action B"); }
}

// 3. Concrete Command
class ConcreteCommandA implements Command {
    private Receiver receiver;

    public ConcreteCommandA(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        receiver.actionA(); // Delegates to receiver
    }
}

// 4. Invoker (Asks the command to carry out the request)
class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void invoke() {
        command.execute();
    }
}

// 5. Client
class CommandTemplateDemo {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommandA(receiver);

        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.invoke(); // Invoker runs command, command calls receiver
    }
}
