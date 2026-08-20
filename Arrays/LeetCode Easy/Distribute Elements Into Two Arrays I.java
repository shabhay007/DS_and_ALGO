// LeetCode - 3069



// Approach 1 - Simulation
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        List<Integer> arr1 = new ArrayList<>();
        arr1.add(nums[0]);

        List<Integer> arr2 = new ArrayList<>();
        arr2.add(nums[1]);

        for (int i = 2; i < n; i++) {
            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }

        // 1. Concatenating the two streams
        return Stream.concat(arr1.stream(), arr2.stream())
                // 2. Unbox Integer objects to primitive ints 
                // (prevents memory overhead)
                .mapToInt(Integer::intValue)
                .toArray(); // 3. Collect into an int[]
    }
}