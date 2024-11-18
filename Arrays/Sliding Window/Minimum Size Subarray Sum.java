// LeetCode:- 209 - Medium



// Brute Force - gives TLE
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;

        for(int i = 0; i<n; i++){
            int sum = 0;

            for(int j = i; j<n; j++){
                sum += nums[j];

                if(sum >= target){
                    minLength = Math.min(minLength, j - i + 1);
                }
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}



// Optimal - Sliding Window - O(n) - This method works fine only with +ve integers and not -ve integers
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        int sum = 0;

        while(j<n){
            sum += nums[j];

            //Trying to shrink the subarray to find minLength
            // At max we are traversing every element twice only
            // So, time complexity will be O(n).
            while(sum >= target){ // Don't get confused with this while loop
                minLength = Math.min(minLength, j-i+1);
                sum -= nums[i];
                i++;
            }

            j++;
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}



// Optimal - Sliding Window + Prefix Sum + Monotonic Deque :- O(n log n)
// This method works fine for all
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;
        long[] prefixSum = new long[n];
        Deque<Integer> dq = new LinkedList<>();

        int i = 0;
        while(i<n){
            if(i == 0){
                prefixSum[i] = nums[i];
            }
            else{
                prefixSum[i] = prefixSum[i-1] + nums[i];
            }

            if(prefixSum[i] >= target){
                minLength = Math.min(minLength, i+1);
            }

            // Shrinking the window in order to get minLength
            while(!dq.isEmpty() && prefixSum[i] - prefixSum[dq.peekFirst()] >= target){
                minLength = Math.min(minLength, i - dq.pollFirst());
            }

            // To maintain the increasing order, by checking the conditions 
            // and removing the indices which won't be helpful
            while(!dq.isEmpty() && prefixSum[i] <= prefixSum[dq.peekLast()]){
                dq.pollFirst();
            }

            dq.offerLast(i);
            i++;
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}