import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Task01 {

    void obtainStackOverflow() {
        /* Exception in thread "main" java.lang.StackOverflowError
         */
        Task01 inner = new Task01();
        inner.obtainStackOverflow();
    }

    long factorial(long a) {
        /* Exception in thread "main" java.lang.StackOverflowError
         */
        if (a == 1)
            return 1;
        return a * factorial(a + 1);    // An intentional mistake here to get StackOverflow
    }

    void obtainOutOfMemory() {
        /* Exception in thread "main" java.lang.OutOfMemoryError:
           Java heap space
         */

        ArrayList<String[]> list = new ArrayList<>();
        while (true) {
            list.add(new String[0]);
        }
    }

    void obtainOutOfMemory2() {
        /* Exception in thread "main" java.lang.OutOfMemoryError:
           Requested array size exceeds VM limit
         */
        String[] s = new String[Integer.MAX_VALUE];
    }

    void obtainDivisionByZero() {
        /* Exception in thread "main" java.lang.ArithmeticException:
           / by zero
        */
        int a = 10 / 0;
    }

    String obtainNullPointerException(Integer[] array) {
        int[] outArray = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            outArray[i] = array[i];
        }
        return Arrays.toString(outArray);
    }

    void obtainIllegalStateException() {
        /* Exception in thread "main" java.lang.IllegalStateException:
           Scanner closed
        */
        Scanner scn = new Scanner(System.in);
        scn.close();
        scn.nextLine();

}

    public static void main(String[] args) {
        Task01 app = new Task01();

//        app.obtainStackOverflow();    // 1

//        app.factorial(5);   // (StackOverflow 2)

//        app.obtainOutOfMemory();      // 2

//        app.obtainOutOfMemory2();

//        app.obtainDivisionByZero();   // 3


        // NullPointerException Section // 4
        /*

        {
            System.out.println(
                    app.obtainNullPointerException(
                            new Integer[]{1, 2, 3, 4, 5}));
            // check if method works correctly at all


            System.out.println(
                    app.obtainNullPointerException(
                            new Integer[]{1, 2, null, 4, 5}));
            // intentionally incorrect call
            //Output: Exception in thread "main" java.lang.NullPointerException
        }

        */


//        app.obtainIllegalStateException();  // 5

    }

}
