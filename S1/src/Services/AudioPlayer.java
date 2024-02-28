package Services;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class AudioPlayer {

    private String audioFilePath;

    public AudioPlayer(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    public void play() {
        var audioFile = new File(audioFilePath);

        if (!audioFile.exists()) {
            printErrorMessage("Audio file does not exist: " + audioFilePath);
            return;
        }

        if (!audioFile.isFile()) {
            printErrorMessage("Invalid audio file path: " + audioFilePath);
            return;
        }

        var media = new Media(audioFile.toURI().toString());
        var mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnError(() -> {
            printErrorMessage("Error playing audio: " + mediaPlayer.getError().getMessage());
        });
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        });

        mediaPlayer.play();
    }

    private void printErrorMessage(String message) {
        String redBackground = "\u001B[41m";
        String resetColor = "\u001B[0m";
        System.out.println(redBackground + message + resetColor);
    }
}