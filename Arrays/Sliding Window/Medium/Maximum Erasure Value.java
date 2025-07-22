// LeetCode - 1695



// Approach 1 - Sliding Window + Set
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        int maxSum = 0;

        while(j < n){
            while(set.contains(nums[j])){
                set.remove(nums[i]);
                sum -= nums[i];
                i++;
            }

            if(!set.contains(nums[j])){
                set.add(nums[j]);
                sum += nums[j];
                maxSum = Math.max(maxSum, sum);
            }

            j++;
        }

        return maxSum;
    }
}





// Approach 2 - Sliding Window + Cummulative Sum + Hash Array/Map
// T.C. - O(3n)
// S.C. - O(2n)
class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] cummSum = new int[n];
        cummSum[0] = nums[0];

        for(int i = 1; i<n; i++){
            cummSum[i] = nums[i] + cummSum[i-1];
        }

        int[] map = new int[10001];
        Arrays.fill(map, -1);

        int i = 0;
        int j = 0;
        int maxSum = 0;

        while(j < n){
            // jumping i so that i - j is valid subarray
            i = Math.max(i, map[nums[j]] + 1);
            int jthSum = cummSum[j];
            int ithSum = (i - 1) < 0 ? 0 : cummSum[i-1];
            int subarraySum = jthSum - ithSum;
            maxSum = Math.max(maxSum, subarraySum);
            map[nums[j]] = j;

            j++;
        }

        return maxSum;
    }
}