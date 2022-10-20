import java.util.Locale;
import java.util.Scanner;

public class HW02Task01 {

    float asker() {
        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.US);     // Set decimal separator "." (Attention: 123,123 => 123123.0)
        float asked = 0f;
        boolean flag = true;

        do {
            System.out.print("Input a float number: ");
            if (!input.hasNextFloat()) {
                input.next();
                System.out.println("Wrong input. Number only allowed.");
            } else {
                asked = input.nextFloat();
                flag = false;
                input.close();
            }
        } while (flag);

        return asked;
    }


    public static void main(String[] args) {
        HW02Task01 app = new HW02Task01();
        System.out.println("Asked: " + app.asker());
    }

}
