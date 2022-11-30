import controller.Controller;
import view.Display;

import java.nio.file.Path;
import java.nio.file.Paths;



public class App {
    public static void main(String[] args) {
        Display.greeting();
        Path path = Paths.get(System.getProperty("user.dir"), "homework03", "Card-index");
        Controller controller = new Controller(path);
        int flag = 0;

        while (flag == 0) {
            flag = controller.buildCard();
        }
    }

}