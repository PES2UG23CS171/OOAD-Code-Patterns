// Adapter Pattern - Media Player Example (AdvancedMediaPlayer → MediaPlayer)

// ── Target Interface ───────────────────────────────────────────────────────
// This is what the Client (AudioPlayer) expects to use
interface MediaPlayer {
	public void play(String audioType, String fileName);
}

// ── Adaptee Interface ──────────────────────────────────────────────────────
// This is the incompatible interface we want to plug in
interface AdvancedMediaPlayer {
	public void playVlc(String fileName);

	public void playMp4(String fileName);
}

// ── Adaptee Concrete Classes ───────────────────────────────────────────────
class VlcPlayer implements AdvancedMediaPlayer {
	public void playVlc(String fileName) {
		System.out.println("Playing vlc file. Name: " + fileName);
	}

	public void playMp4(String fileName) {
		// do nothing
	}
}

class Mp4Player implements AdvancedMediaPlayer {
	public void playVlc(String fileName) {
		// do nothing
	}

	public void playMp4(String fileName) {
		System.out.println("Playing mp4 file. Name: " + fileName);
	}
}

// ── Adapter ────────────────────────────────────────────────────────────────
// Implements Target (MediaPlayer), wraps Adaptee (AdvancedMediaPlayer) via
// composition
class MediaAdapter implements MediaPlayer {
	AdvancedMediaPlayer advancedMusicPlayer; // HAS-A relationship

	// Accepts the Adaptee directly → Target ob = new Adapter(adaptee)
	public MediaAdapter(AdvancedMediaPlayer advancedMusicPlayer) {
		this.advancedMusicPlayer = advancedMusicPlayer;
	}

	public void play(String audioType, String fileName) {
		// Translates the Target call → Adaptee call
		if (audioType.equalsIgnoreCase("vlc")) {
			advancedMusicPlayer.playVlc(fileName);
		} else if (audioType.equalsIgnoreCase("mp4")) {
			advancedMusicPlayer.playMp4(fileName);
		}
	}
}

// ── Demo ───────────────────────────────────────────────────────────────────
class AdapterPatternDemo {
	public static void main(String[] args) {

		// Create the Adaptee first
		VlcPlayer vlcPlayer = new VlcPlayer();
		Mp4Player mp4Player = new Mp4Player();

		// Then wrap in Adapter → Target = new Adapter(adaptee)
		MediaPlayer vlcAdapter = new MediaAdapter(vlcPlayer);
		MediaPlayer mp4Adapter = new MediaAdapter(mp4Player);
		// Target ob = new Adapter(adaptee)

		vlcAdapter.play("vlc", "far far away.vlc");
		mp4Adapter.play("mp4", "alone.mp4");
	}
}
