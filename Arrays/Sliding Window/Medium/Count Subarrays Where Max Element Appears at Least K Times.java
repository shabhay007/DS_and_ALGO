// LeetCode - 2962



// Approach 1 - Brute Force (Gives TLE)
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int max = 0;

        for(int num : nums){
            max = Math.max(max, num);
        }
        System.out.println(max);
        int result = 0;

        for(int i = 0; i<n; i++){
            int count = 0;

            for(int j = i; j<n; j++){
                if(nums[j] == max){
                    count++;
                }

                if(count >= k){
                    result++;
                }
            }
        }

        return result;
    }
}




// Approach 2 (Optimal) - Sliding Window
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        long result = 0;
        int max = 0;

        for(int num : nums){
            max = Math.max(max, num);
        }

        int l = 0;
        int r = 0;
        int count = 0;

        while(r < n){
            if(nums[r] == max){
                count++;
            }

            while(count >= k){
                result += n-r;
                
                if(nums[l] == max){
                    count--;
                }

                l++;
            }

            r++;
        }

        return result;
    }
}