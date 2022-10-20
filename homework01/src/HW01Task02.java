import java.util.Arrays;
import java.util.logging.Logger;

public class HW01Task02 {

    int[] difference(int[] arr1, int[] arr2) {
        Logger log = Logger.getLogger(this.getClass().getSimpleName());

        if (arr1.length != arr2.length) {
            log.info("The arrays are of different length. Returning empty array.");
            return new int[0];
        }
        int[] result = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = arr1[i] - arr2[i];
        }
        return result;
    }

    public static void main(String[] args) {
        HW01Task02 app = new HW01Task02();

        int[] testArr1 = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] testArr2 = Arrays.copyOf(testArr1, testArr1.length);
        Arrays.sort(testArr2);

        int[] testArr3 = Arrays.copyOfRange(testArr2, 0, testArr2.length - 1);


        System.out.println(Arrays.toString(app.difference(testArr1, testArr3)));    // incorrect

        System.out.println(Arrays.toString(app.difference(testArr1, testArr2)));    // correct


    }
}
