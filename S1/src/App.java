import java.util.Scanner;

import Services.AudioPlayer;
import Services.GifMaker;
import Services.WebScrapper;

public class App {
    public static void main(String[] args) {
        LoadMenu();
    }

    public static void LoadMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Console App!");
        System.out.println("1. Create GIF");
        System.out.println("2. Save Telegram Profile");
        System.out.println("3. Play Audio");

        var choice = scanner.nextInt();
        switch (choice) {
            case 1:
                gifMaker();
                break;
            case 2:
                saveTelegramProfile();
                break;
            case 3:
                playAudio();
                break;
            default:
                System.out.println("\u001B[41m\u001B[37mInvalid choice. Please select 1, 2, or 3.");
                LoadMenu();
        }
        scanner.close();
    }

    public static void gifMaker() {
        var userInput = getConsoleInput(
                "\u001B[44mPlease enter a valid directory path to images that you want to create a gif with them:\u001B[0m");

        var gifMaker = new GifMaker(userInput);
        gifMaker.createGif();
    }

    public static void saveTelegramProfile() {
        var telegramGroupLink = getConsoleInput(
                "\u001B[44mPlease enter a valid telegram group link:\u001B[0m");
        var directory = getConsoleInput(
                "\u001B[44mWhere do want to save the images?:\u001B[0m");

        var webScrapper = new WebScrapper(directory, telegramGroupLink);
        webScrapper.scrappTelegramGroup();
    }

    public static void playAudio() {
        var userInput = getConsoleInput(
                "\u001B[44mPlease enter a valid directory path to audio that you want to play it:\u001B[0m");

        var audioPlayer = new AudioPlayer(userInput);
        audioPlayer.play();
    }

    public static String getConsoleInput(String message) {
        System.out.println(message);

        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
