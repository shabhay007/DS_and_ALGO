// LeetCode - 2419




// It is solved because of Bit Manipulation concept

// Approach 1 - Two Pointer/Sliding Window
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int max = 0;

        for(int i = 0; i<n; i++){
            max = Math.max(max, nums[i]);
        }

        int maxLen = 0;

        int i = 0;
        int j = 0;

        while(j < n){
            while(j < n && nums[j] == max){
                maxLen = Math.max(maxLen, j-i+1);
                j++;
            }

            j++;
            i = j;
        }

        return maxLen;
    }
}





// Approach 2
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int max = 0;

        for(int i = 0; i<n; i++){
            max = Math.max(max, nums[i]);
        }

        int count = 0;
        int length = 0;

        for(int i = 0; i<n; i++){
            if(nums[i] == max){
                count++;
                length = Math.max(length, count);
            }
            else{
                count = 0;
            }
        }

        return length;
    }
}






// Approach 3
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int currLen = 0;
        int maxLen = 0;
        int maxVal = nums[0];

        for(int i = 0; i<n; i++){
            if(nums[i] > maxVal){
                maxVal = nums[i];
                currLen = 1;
                maxLen = currLen;

            }
            else if(nums[i] == maxVal){
                currLen++;
            }
            else{
                currLen = 0;
            }

            maxLen = Math.max(maxLen, currLen);
        }

        return maxLen;
    }
}