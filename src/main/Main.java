package main;


import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class Main {
    public static void main(String[] args) {
        try (Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .jna(true)
                .build()) {

            terminal.enterRawMode();
            Game game = new Game(terminal);
            game.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}