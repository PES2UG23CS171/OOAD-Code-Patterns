// Builder Pattern - RTF Text Converter
// Key idea: Same Director (RTFReader) produces different output formats
// (ASCII, TeX, TextWidget) by swapping the Builder it uses.

// ── Builder Interface ──────────────────────────────────────────────────────
// Each method = one step the Director will call
interface TextConverter {
    void convertCharacter(char c);
    void convertParagraph();
}

// ── Product classes (what each builder produces) ───────────────────────────
class ASCIIText {
    private StringBuilder text = new StringBuilder();
    public void append(char c)    { text.append(c); }
    public String getText()       { return text.toString(); }
}

class TeXText {
    private StringBuilder text = new StringBuilder();
    public void append(String s)  { text.append(s); }
    public String getText()       { return text.toString(); }
}

class TextWidget {
    private StringBuilder content = new StringBuilder();
    public void append(char c)    { content.append(c); }
    public void newLine()         { content.append("\n"); }
    public String getContent()    { return content.toString(); }
}

// ── Concrete Builders ──────────────────────────────────────────────────────
class ASCIIConverter implements TextConverter {
    private ASCIIText asciiText = new ASCIIText();
    public void convertCharacter(char c)  { asciiText.append(c); }
    public void convertParagraph()        { asciiText.append('\n'); }
    public ASCIIText getResult()          { return asciiText; }
}

class TeXConverter implements TextConverter {
    private TeXText texText = new TeXText();
    public void convertCharacter(char c)  { texText.append(String.valueOf(c)); }
    public void convertParagraph()        { texText.append("\\par "); }
    public TeXText getResult()            { return texText; }
}

class TextWidgetConverter implements TextConverter {
    private TextWidget widget = new TextWidget();
    public void convertCharacter(char c)  { widget.append(c); }
    public void convertParagraph()        { widget.newLine(); }
    public TextWidget getResult()         { return widget; }
}

// ── Director ───────────────────────────────────────────────────────────────
// Reads the input and calls builder steps — doesn't care about the output format
class RTFReader {
    private TextConverter builder;
    public RTFReader(TextConverter builder) { this.builder = builder; }
    public void parse(String text) {
        for (char c : text.toCharArray()) {
            if (c == '\n') builder.convertParagraph();
            else           builder.convertCharacter(c);
        }
    }
}

// ── Client ─────────────────────────────────────────────────────────────────
class RTFDemo {
    public static void main(String[] args) {
        String input = "Hello World\nBuilder Pattern";

        ASCIIConverter ascii = new ASCIIConverter();
        new RTFReader(ascii).parse(input);
        System.out.println("ASCII: " + ascii.getResult().getText());

        TeXConverter tex = new TeXConverter();
        new RTFReader(tex).parse(input);
        System.out.println("TeX:   " + tex.getResult().getText());

        TextWidgetConverter widget = new TextWidgetConverter();
        new RTFReader(widget).parse(input);
        System.out.println("Widget:\n" + widget.getResult().getContent());
    }
}
