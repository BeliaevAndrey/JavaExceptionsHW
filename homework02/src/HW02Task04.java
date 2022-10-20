import java.util.InputMismatchException;
import java.util.Scanner;

public class HW02Task04 {

    void slowpoke(Scanner inFlow) {

        System.out.print("Enter some words please: ");
        String someString = inFlow.nextLine();
        inFlow.reset();

        if (someString.isEmpty()) {
            throw new InputMismatchException("Empty lines are not allowed!");
        } else {
            System.out.println("You entered: " + someString);
        }
    }

    public static void main(String[] args) {
        HW02Task04 app = new HW02Task04();
        Scanner inFlow = new Scanner(System.in);
        app.slowpoke(inFlow);
        app.slowpoke(inFlow);
        app.slowpoke(inFlow);
        inFlow.close();
    }
}
