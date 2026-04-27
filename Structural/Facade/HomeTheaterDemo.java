// Facade Pattern - Home Theater System

// ── Subsystem Classes ──────────────────────────────────────────────────────
class Amplifier {
    String description;
    Tuner tuner;
    StreamingPlayer player;

    public Amplifier(String description) {
        this.description = description;
    }

    public void on() {
        System.out.println(description + " on");
    }

    public void off() {
        System.out.println(description + " off");
    }

    public void setSurroundSound() {
        System.out.println(description + " surround sound on");
    }

    public void setVolume(int level) {
        System.out.println(description + " setting volume to " + level);
    }

    public void setTuner(Tuner tuner) {
        this.tuner = tuner;
        System.out.println(description + " setting tuner");
    }

    public void setStreamingPlayer(StreamingPlayer player) {
        this.player = player;
        System.out.println(description + " setting streaming player");
    }

    public String toString() {
        return description;
    }
}

class Tuner {
    String description;
    double frequency;

    public Tuner(String description, Amplifier amp) {
        this.description = description;
    }

    public void on() {
        System.out.println(description + " on");
    }

    public void off() {
        System.out.println(description + " off");
    }

    public void setFrequency(double freq) {
        this.frequency = freq;
        System.out.println(description + " frequency to " + freq);
    }

    public String toString() {
        return description;
    }
}

class StreamingPlayer {
    String description;
    String movie;

    public StreamingPlayer(String description, Amplifier amp) {
        this.description = description;
    }

    public void on() {
        System.out.println(description + " on");
    }

    public void off() {
        System.out.println(description + " off");
    }

    public void play(String movie) {
        this.movie = movie;
        System.out.println(description + " playing \"" + movie + "\"");
    }

    public void stop() {
        System.out.println(description + " stopped");
    }

    public void setSurroundAudio() {
        System.out.println(description + " surround audio set");
    }

    public String toString() {
        return description;
    }
}

class Projector {
    String description;

    public Projector(String description, StreamingPlayer player) {
        this.description = description;
    }

    public void on() {
        System.out.println(description + " on");
    }

    public void off() {
        System.out.println(description + " off");
    }

    public void wideScreenMode() {
        System.out.println(description + " widescreen mode (16x9)");
    }

    public String toString() {
        return description;
    }
}

class TheaterLights {
    String description;

    public TheaterLights(String description) {
        this.description = description;
    }

    public void on() {
        System.out.println(description + " on");
    }

    public void off() {
        System.out.println(description + " off");
    }

    public void dim(int level) {
        System.out.println(description + " dimming to " + level + "%");
    }
}

class Screen {
    String description;

    public Screen(String description) {
        this.description = description;
    }

    public void up() {
        System.out.println(description + " going up");
    }

    public void down() {
        System.out.println(description + " going down");
    }
}

class PopcornPopper {
    String description;

    public PopcornPopper(String description) {
        this.description = description;
    }

    public void on() {
        System.out.println(description + " on");
    }

    public void off() {
        System.out.println(description + " off");
    }

    public void pop() {
        System.out.println(description + " popping popcorn!");
    }
}

// ── Facade ─────────────────────────────────────────────────────────────────
// Single simple interface over the complex subsystem
class HomeTheaterFacade {
    Amplifier amp;
    Tuner tuner;
    StreamingPlayer player;
    Projector projector;
    TheaterLights lights;
    Screen screen;
    PopcornPopper popper;

    public HomeTheaterFacade(Amplifier amp, Tuner tuner, StreamingPlayer player,
            Projector projector, Screen screen, TheaterLights lights, PopcornPopper popper) {
        this.amp = amp;
        this.tuner = tuner;
        this.player = player;
        this.projector = projector;
        this.screen = screen;
        this.lights = lights;
        this.popper = popper;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        popper.on();
        popper.pop();
        lights.dim(10);
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setStreamingPlayer(player);
        amp.setSurroundSound();
        amp.setVolume(5);
        player.on();
        player.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        popper.off();
        lights.on();
        screen.up();
        projector.off();
        amp.off();
        player.stop();
        player.off();
    }
}

// ── Client ─────────────────────────────────────────────────────────────────
class HomeTheaterDemo {
    public static void main(String[] args) {
        Amplifier amp = new Amplifier("Amplifier");
        Tuner tuner = new Tuner("AM/FM Tuner", amp);
        StreamingPlayer player = new StreamingPlayer("Streaming Player", amp);
        Projector projector = new Projector("Projector", player);
        TheaterLights lights = new TheaterLights("Theater Lights");
        Screen screen = new Screen("Theater Screen");
        PopcornPopper popper = new PopcornPopper("Popcorn Popper");

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, tuner, player, projector, screen, lights, popper);

        homeTheater.watchMovie("Raiders of the Lost Ark");
        System.out.println();
        homeTheater.endMovie();
    }
}
