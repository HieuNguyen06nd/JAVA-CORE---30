package S136;

public class Main {
    public static void main(String[] args) {
        int[] nums = {2,2,1,4,2,1,2};
        System.out.println(singNumber(nums));
    }
    public static int singNumber(int[] nums){
        int result = 0;
        for (int i = 0; i <nums.length ; i++) {
            result = result^nums[i];
        }
        return result;
    }
}
