import java.util.Arrays;

public class Activity2 {
    public float sortAndFindMedian(int[] numbers) {
        sort(numbers);
        int n = numbers.length;
        if (n % 2 == 0) {
            // even amount of elements, take the average of the two middle elements
            return ((float) (numbers[(n / 2) - 1] + numbers[n / 2])) / 2;
        } else {
            // odd amount of elements, take the middle element
            return numbers[n / 2];
        }
    }

    // sorts the provided array in ascending numerical order
    private void sort(int[] numbers){
        // selection sort
        for (int i = 0; i < numbers.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if(numbers[j] < numbers[min]){
                    min = j;
                }
            }
            swap(numbers, i, min);
        }
        // Java has a default implementation of Dual-Pivot Quicksort that can be called by
        // Arrays.sort(numbers);
    }

    // swaps the elements on the indicated indexes of the provided array
    private void swap(int[] numbers, int i, int i2){
        if (i == i2) {
            return;
        }
        int temp = numbers[i];
        numbers[i] = numbers[i2];
        numbers[i2] = temp;
    }

    public static void main(String[] args) {
        Activity2 test = new Activity2();
        int[] t1 = {10000,5,6,2,3,5,7, 1,12, 8, 64,2};
        test.sort(t1);
        System.out.println(Arrays.toString(t1));
        int[] t2 = {10000,5,6,2,3,5,7, 1,12, 8, 64,2};
        System.out.println(test.sortAndFindMedian(t2));
    }
}
