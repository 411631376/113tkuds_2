public class AdvancedArrayRecursion {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
            }
        }
        int tmp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = tmp;
        return i + 1;
    }

    public static int findKthMin(int[] arr, int k) {
        quickSort(arr, 0, arr.length - 1);
        return arr[k - 1];
    }

    public static boolean hasSubArraySum(int[] arr, int target) {
        return checkSum(arr, 0, target);
    }

    private static boolean checkSum(int[] arr, int idx, int sum) {
        if (sum == 0) return true;
        if (idx >= arr.length || sum < 0) return false;
        return checkSum(arr, idx + 1, sum - arr[idx]) || checkSum(arr, idx + 1, sum);
    }
}