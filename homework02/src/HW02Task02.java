public class HW02Task02 {

    void someCode(int[] intArray) {
        try {
            int d = 0;
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Catching exception: " + e);
        }
    }


    public static void main(String[] args) {
        HW02Task02 app = new HW02Task02();

        int[] testArray1 = new int[]{};
        int[] testArray2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] testArray3 = new int[]{1, 2, 3, 4, 5, 6, 7, 8};

        app.someCode(testArray1);
        app.someCode(testArray2);
        app.someCode(testArray3);
        app.someCode(null);
    }

}
