// Command Pattern - Library Demo (Issue / Return Books)

// ── Command Interface ──────────────────────────────────────────────────────
interface LibraryCommand {
    void execute();
}

// ── Receiver (does the actual work) ───────────────────────────────────────
class Library {
    public void issueBook(String book)  { System.out.println("Book issued: " + book); }
    public void returnBook(String book) { System.out.println("Book returned: " + book); }
}

// ── Concrete Commands ──────────────────────────────────────────────────────
class IssueBookCommand implements LibraryCommand {
    private Library library;
    private String book;
    public IssueBookCommand(Library library, String book) { this.library = library; this.book = book; }
    public void execute() { library.issueBook(book); }
}

class ReturnBookCommand implements LibraryCommand {
    private Library library;
    private String book;
    public ReturnBookCommand(Library library, String book) { this.library = library; this.book = book; }
    public void execute() { library.returnBook(book); }
}

// ── Invoker ────────────────────────────────────────────────────────────────
class Librarian {
    private LibraryCommand command;
    public void setCommand(LibraryCommand command) { this.command = command; }
    public void processRequest() { command.execute(); }
}

// ── Client ─────────────────────────────────────────────────────────────────
class LibraryDemo {
    public static void main(String[] args) {
        Library library = new Library();

        LibraryCommand issue  = new IssueBookCommand(library, "Java Programming");
        LibraryCommand returnCmd = new ReturnBookCommand(library, "Java Programming");

        Librarian librarian = new Librarian();

        librarian.setCommand(issue);
        librarian.processRequest();    // Book issued

        librarian.setCommand(returnCmd);
        librarian.processRequest();   // Book returned
    }
}
