// LeetCode:- 862 - Hard


//Brute Force - gives TLE
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;

        for(int i = 0; i<n; i++){
            int length = Integer.MAX_VALUE;
            int sum = 0;

            for(int j = i; j<n; j++){
                sum += nums[j];

                if(sum >= k){
                    length = Math.min(length, j-i+1);
                }
            }

            minLength = Math.min(minLength, length);
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
}



//Optimal - Sliding Window + Monotonic Deque
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;
        long[] prefixSum = new long[n];
        Deque<Integer> dq = new LinkedList<>();

        int i = 0;
        while(i < n){
            if(i==0){
                prefixSum[i] = nums[i];
            }
            else{
                prefixSum[i] = prefixSum[i-1] + nums[i];
            }

            if(prefixSum[i] >= k){
                minLength = Math.min(minLength, i+1);
            }

            //checking if we need to shrink the window or not
            // Remove indices from the deque where the subarray sum is >= k
            while(!dq.isEmpty() && prefixSum[i] - prefixSum[dq.peekFirst()] >= k){
                minLength = Math.min(minLength, i - dq.peekFirst());
                dq.pollFirst();
            }

            //deque should be monotonically increasing
            while(!dq.isEmpty() && prefixSum[i] <= prefixSum[dq.peekLast()]){
                dq.pollLast(); // Remove indices that won't be useful
            }

            dq.offerLast(i);
            i++;
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
}
