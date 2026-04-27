// Proxy Pattern - Access Control Demo

// ── Subject Interface ──────────────────────────────────────────────────────
interface VideoService {
    void playVideo(String title, boolean isRestricted);
}

// ── Helper Class ───────────────────────────────────────────────────────────
class User {
    private int age;
    public User(int age) { this.age = age; }
    public int getAge() { return age; }
}

// ── Real Subject ───────────────────────────────────────────────────────────
class RealVideoService implements VideoService {
    public void playVideo(String title, boolean isRestricted) {
        System.out.println("Playing video: " + title);
    }
}

// ── Proxy (Protection Proxy) ───────────────────────────────────────────────
// Same interface as RealVideoService → client can't tell them apart
class VideoServiceProxy implements VideoService {
    private RealVideoService realService = new RealVideoService(); // HAS-A real object
    private User user;

    public VideoServiceProxy(User user) { this.user = user; }

    public void playVideo(String title, boolean isRestricted) {
        if (isRestricted && user.getAge() < 18) {
            System.out.println("Access Denied: Age restriction for \"" + title + "\"");
        } else {
            realService.playVideo(title, isRestricted); // delegate to real object
        }
    }
}

// ── Client ─────────────────────────────────────────────────────────────────
class AccessControlDemo {
    public static void main(String[] args) {
        User teen  = new User(15);
        User adult = new User(21);

        // Client uses VideoService interface — doesn't know it's a Proxy
        VideoService teenService  = new VideoServiceProxy(teen);
        VideoService adultService = new VideoServiceProxy(adult);

        teenService.playVideo("Comedy Clip", false);      // allowed
        teenService.playVideo("Restricted Movie", true);  // denied

        System.out.println("----");

        adultService.playVideo("Restricted Movie", true); // allowed
    }
}
