// LeetCode - 2615



// Approach 1 - Brute Force
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];

        for(int i = 0; i<n; i++){
            long sum = 0;

            for(int j = 0; j<n; j++){
                if(i != j && nums[i] == nums[j]){
                    sum += Math.abs(i - j);
                }
            }

            arr[i] = sum;
        }

        return arr;
    }
}






// Approach 2 - Using prefix sum and map
// T.C : O(n)
// S.C : O(n)
class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];

        Map<Integer, Long> indexSum = new HashMap<>();
        Map<Integer, Long> indexCount = new HashMap<>();

        // Left to Right
        for (int i = 0; i < n; i++) {
            long freq = indexCount.getOrDefault(nums[i], 0L);
            long sum  = indexSum.getOrDefault(nums[i], 0L);

            arr[i] += freq * i - sum;

            indexCount.put(nums[i], freq + 1);
            indexSum.put(nums[i], sum + i);
        }

        indexSum.clear();
        indexCount.clear();

        // Right to Left
        for (int i = n - 1; i >= 0; i--) {
            long freq = indexCount.getOrDefault(nums[i], 0L);
            long sum  = indexSum.getOrDefault(nums[i], 0L);

            arr[i] += sum - freq * i;

            indexCount.put(nums[i], freq + 1);
            indexSum.put(nums[i], sum + i);
        }

        return arr;
    }
}