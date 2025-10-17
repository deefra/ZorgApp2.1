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
        getWriter().println(getUtility().getReset()); // Reset colors
        getUtility().clearScreen();

        getUtility().centerAscii(getUtility().ascii()); // Ascii art
        getUtility().padding();

        while(running) {
            getUtility().padding();
            String username = getUtility().centeredInput("Username > ");
            String password = getUtility().centeredInput("Password > ", true);

            try {
                 currentUser = userManager.login(username, password);
                if(currentUser != null) {
                    getUtility().padding();
                    running = false;
                } else  {
                    getUtility().padding();
                    getWriter().println(getUtility().getRed() + getUtility().centerText("Invalid username or password!") + getUtility().getReset());
                }
            }catch(Exception e) {
                getWriter().println("An error occurred while logging in" + e.getMessage());
            }
        }
    }
}