// LeetCode Easy - 3151


// Approach 1
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean isArraySpecial(int[] nums) {
        int n = nums.length;

        if(n == 1) return true;

        for(int i = 0; i < n-1; i++){
            if(nums[i] % 2 == nums[i+1] % 2){
                return false;
            }
        }

        return true;
    }
}




// Approach 2 - Bit Manipulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean isArraySpecial(int[] nums) {
        int n = nums.length;

        if(n == 1) return true;

        // if a number is odd, its last bit will always be 1 and its 0 for even
        // ex.- 3 -> (011) and 4 -> (100)

        for(int i = 0; i < n-1; i++){
            // if last bit is same, it means they have same parity

            // to find last bit on any number, take the & operation with that number
            if((nums[i] & 1) == (nums[i+1] & 1)){
                return false;
            }
        }

        return true;
    }
}