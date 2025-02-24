package application;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class music {
	private MediaPlayer mediaPlayer;
	private final static String filePath = "/Resources/music.mp3";
    
    public void playMusic(boolean loop, double initialVolume) {
        try {
        	// ใช้ getResource เพื่อเข้าถึงไฟล์ใน Resources folder            
            Media sound = new Media(getClass().getResource(filePath).toExternalForm());
            mediaPlayer = new MediaPlayer(sound);
            
            // ตั้งค่าการเล่นวนซ้ำ
            if (loop) {
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            }
            
            // ตั้งค่าความดังเริ่มต้น (0.0 - 1.0)
            mediaPlayer.setVolume(initialVolume);
            
            // เล่นเพลง
            mediaPlayer.play();
            
        } catch (Exception e) {
            System.out.println("Error playing music: " + e.getMessage());
        }
    }
    
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
    
    public void pauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
    
    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            // ตรวจสอบให้ค่าความดังอยู่ในช่วงที่ถูกต้อง
            if (volume < 0.0) volume = 0.0;
            if (volume > 1.0) volume = 1.0;
            mediaPlayer.setVolume(volume);
        }
    }
    
    public double getVolume() {
        if (mediaPlayer != null) {
            return mediaPlayer.getVolume();
        }
        return 0.0;
    }
    
    public void dispose() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer = null;
        }
    }
	
	
}
