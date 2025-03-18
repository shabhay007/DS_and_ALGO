// LeetCode Medium - 2401


// Approach 1 - Brute Force (Checking all the subarrays)
// T.C. - O(n^4)
// S.C. - O(1)
class Solution {
    public boolean isNice(int start, int end, int[] nums){
        for(int i = start; i <= end; i++){
            for(int j = start + 1; j <= end; j++){
                if(i != j && (nums[i] & nums[j]) != 0){
                    return false;
                }
            }
        }

        return true;
    }

    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int maxLength = 1;

        // checking all the subarrays
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(isNice(i, j, nums)){
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }

        return maxLength;
    }
}



// Approach 2 (Better)
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int maxLength = 1;

        // checking all the subarrays
        for(int i = 0; i<n; i++){
            int mask = 0;

            for(int j = i; j<n; j++){
                if((mask & nums[j]) != 0){
                    break;
                }

                maxLength = Math.max(maxLength, j - i + 1);
                mask |= nums[j]; // or operation to include curr element
            }
        }

        return maxLength;
    }
}




// Approach 3 (Optimal) - Sliding Window
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int maxLength = 1;

        int l = 0;
        int r = 0;
        int mask = 0;

        while(r < n){
            while((mask & nums[r]) != 0){ // shrink the window
                // remove the lth element
                mask ^= nums[l];
                l++;
            }

            maxLength = Math.max(maxLength, r - l + 1);
            mask |= nums[r];
            r++; // expanding the window
        }

        return maxLength;
    }
}