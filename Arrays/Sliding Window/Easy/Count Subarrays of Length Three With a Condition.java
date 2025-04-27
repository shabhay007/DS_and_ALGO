// LeetCode 3392



// Approach 1 (Brute Force)
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public boolean isValid(int start, int end, int[] nums){
        int sum = nums[start] + nums[end];

        return sum * 2 == nums[start+1];
    }

    public int countSubarrays(int[] nums) {
        int n = nums.length;
        int count = 0;

        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                if(j - i + 1 == 3 && isValid(i, j, nums)){
                    count++;
                }
            }
        }

        return count;
    }
}





// Approach 2 (Optimal) - Sliding window
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int countSubarrays(int[] nums) {
        int n = nums.length;
        int count = 0;

        int l = 0;
        int r = 0;

        while(r < n){
            if(r-l+1 > 3){
                l++;
            }

            if(r-l+1 == 3){
                int sum = nums[r] + nums[l];

                if(sum * 2 == nums[l+1]){
                    count++;
                }
            }

            r++;
        }

        return count;
    }
}