import menu.BaseMenu;
import menu.LoginMenu;
import menu.MainMenu;
import user.User;
import user.UserManager;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        try {
            Terminal terminal = TerminalBuilder.terminal();
            LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();
            PrintWriter writer = new PrintWriter(terminal.writer(), true);
            UserManager userManager = new UserManager();
            boolean running = true;

            while(running){
                if (userManager.getCurrentUser() == null){
                    BaseMenu loginMenu = new LoginMenu(terminal, reader, writer, userManager);
                    loginMenu.display(); // Run the login menu
                } else {
                    User currentUser = userManager.getCurrentUser();
                    BaseMenu MainMenu = new MainMenu(terminal, reader, writer, userManager, currentUser);
                    MainMenu.display(); // Run the main menu
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
