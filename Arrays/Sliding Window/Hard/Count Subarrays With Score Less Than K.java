// LeetCode - 2302


// Approach 1 - Brute Force (Gives TLE)
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public boolean isValid(int start, int end, int[] nums, long k){
        long sum = 0;

        for(int i = start; i<=end; i++){
            sum += nums[i];
        }

        return sum * (end - start + 1) < k;
    }

    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        long count = 0;

        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                if(isValid(i, j, nums, k)){
                    count++;
                }
            }
        }

        return count;
    }
}




// Approach 2 - Better (Gives TLE)
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        long count = 0;

        for(int i = 0; i<n; i++){
            long sum = 0;

            for(int j = i; j<n; j++){
                sum += nums[j];

                if(sum * (j-i+1) < k){
                    count++;
                }
            }
        }

        return count;
    }
}





// Approach 3 (Optimal) - Sliding Window
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        long count = 0;

        int l = 0;
        int r = 0;
        long sum = 0;

        while(r < n){
            sum += nums[r];

            while(sum * (r-l+1) >= k){
                sum -= nums[l];
                l++;
            }

            if(sum * (r-l+1) < k){
                count += r-l+1;
            }

            r++;
        }

        return count;
    }
}