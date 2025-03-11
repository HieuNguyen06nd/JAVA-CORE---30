import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums =  {5, 3, 2, 7, 8, 1, 2};
        insertionSort(nums);
        System.out.println(Arrays.toString(nums));

    }

    private static void insertionSort(int[] nums) {
        for(int i = 1; i < nums.length; i++) {
            int newValue = nums[i];
            int j = i - 1;
            while(j >= 0 && nums[j] > newValue) {
                nums[j + 1] = nums[j];
                nums[j] = -10000;
                j--;
            }
            nums[j+1] = newValue;
        }
    }

    private static void selectionSort(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            int minIdx = i;
            for(int j = i+1; j < nums.length; j++) {
                if(nums[j] < nums[minIdx]) {
                    minIdx = j;
                }
            }
            swap(i, minIdx, nums);
        }
    }

    private static void bubbleSort(int[] nums) {
        for(int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1; j++) {
                if(nums[j] > nums[j+1]) {
                    swap(j, j+1, nums);
                }
            }
        }
    }

    private static void swap(int j, int i, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}












