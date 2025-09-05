import java.util.HashMap;

public class LC01_TwoSum_THSHoliday {
    public static void main(String[] args) {
        int target = 55;
        int[] nums = {20, 15, 35, 40};

        int[] result = twoSum(nums, target);
        if (result != null) {
            System.out.println(result[0] + " " + result[1]);
        } else {
            System.out.println("-1 -1");
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
            
            map.put(nums[i], i);
        }
        
        return new int[] {-1, -1};
    }
}
