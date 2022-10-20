import java.util.Arrays;

public class HW01Task03 {

    public static void main(String[] args) {
        HW01Task03 app = new HW01Task03();

        int[] testArr1 = new int[]{90, 80, 70, 60, 50, 40, 30, 20, 10};
        int[] testArr2 = Arrays.copyOf(testArr1, testArr1.length);

        int[] testArr3 = Arrays.copyOfRange(testArr2, 0, testArr2.length - 1);

        try {
            System.out.println(Arrays.toString(app.divider(testArr1, testArr3)));  // incorrect
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            testArr2[4] = 0;        // let there will be `Infinity` in answer
            System.out.println(Arrays.toString(app.divider(testArr1, testArr2)));    // correct
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    double[] divider(int[] arr1, int[] arr2) {

        if (arr1.length != arr2.length) {
            throw new RuntimeException("The arrays are of different length");

        }
        double[] result = new double[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = (double) arr1[i] / arr2[i];
        }
        return result;
    }

}
