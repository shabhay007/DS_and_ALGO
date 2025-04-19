// LeetCode Medium - 2563



// Approach 1 (Brute Force)
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        int n = nums.length;
        long pairs = 0;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                long sum = nums[i] + nums[j];

                if(sum >= lower && sum <= upper){
                    pairs++;
                }
            }
        }

        return pairs;
    }
}





// Approach 2 (Optimal) - Binary Search
// T.C. - O(n.log(n))
// S.C. - O(1)
class Solution {
    private int lowerBound(int start, int end, int[] nums, int target) {
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }

    private int upperBound(int start, int end, int[] nums, int target) {
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }

    public long countFairPairs(int[] nums, int lower, int upper) {
        int n = nums.length;
        Arrays.sort(nums);

        long pairs = 0;

        for(int i = 0; i<n; i++){
            int idx1 = lowerBound(i+1, n, nums, lower-nums[i]);
            int idx2 = upperBound(i+1, n, nums, upper-nums[i]);

            int left = idx1 - 1 - i;
            int right = idx2 - 1 - i;

            pairs += right - left;
        }

        return pairs;
    }
}