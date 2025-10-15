package menu;
import user.UserManager;
import user.User;

import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;

import java.io.PrintWriter;

public class LoginMenu extends BaseMenu {
    private final UserManager userManager;
    private User currentUser;

    public LoginMenu(Terminal terminal, LineReader reader, PrintWriter writer,  UserManager userManager) {
        super(terminal, reader, writer);
        this.userManager = userManager;
    }

    @Override
    protected void initializeMenuOptions() {
        // No menu options in loginMenu
    }

    @Override
    protected void handleInput(char key) {
        // No input handling needed for loginMenu
    }

    public void display() {
        boolean running = true;
        getWriter().println(getReset()); // Reset colors
        clearScreen();

        centerAscii(ascii()); // Ascii art
        padding();

        while(running) {
            padding();
            String username = centeredInput("Username > ");
            String password = centeredInput("Password > ", true);

            try {
                 currentUser = userManager.login(username, password);
                if(currentUser != null) {
                    padding();
                    running = false;
                } else  {
                    padding();
                    getWriter().println(getRed() + centerText("Invalid username or password!") + getReset());
                }
            }catch(Exception e) {
                getWriter().println("An error occurred while logging in" + e.getMessage());
            }
        }
    }
}