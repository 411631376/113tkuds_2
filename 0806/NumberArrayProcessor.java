import java.util.*;
public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 2, 4, 5, 1};
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr1) set.add(num);
        System.out.println(set);

        int[] sorted1 = {1, 3, 5}, sorted2 = {2, 4, 6};
        int[] merged = new int[sorted1.length + sorted2.length];
        System.arraycopy(sorted1, 0, merged, 0, sorted1.length);
        System.arraycopy(sorted2, 0, merged, sorted1.length, sorted2.length);
        Arrays.sort(merged);
        System.out.println(Arrays.toString(merged));

        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr1) freq.put(num, freq.getOrDefault(num, 0) + 1);
        int maxFreq = 0, mode = arr1[0];
        for (int k : freq.keySet()) {
            if (freq.get(k) > maxFreq) {
                maxFreq = freq.get(k);
                mode = k;
            }
        }
        System.out.println("出現最多: " + mode);
    }
}