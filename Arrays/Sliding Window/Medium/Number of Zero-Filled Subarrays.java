// LeetCode - 2348



// Approach 1 - Sliding Window (My approach)
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public long zeroFilledSubarray(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        long count = 0;

        while(j < n){
            while(j < n && nums[j] == 0){
                count += j - i + 1;
                j++;
            }

            j++;
            i = j;
        }

        return count;
    }
}





// Approach 2 - Maths
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public long zeroFilledSubarray(int[] nums) {
        int n = nums.length;
        int i = 0;
        long count = 0;

        while(i < n){
            long len = 0;

            if(nums[i] == 0){
                while(i < n && nums[i] == 0){
                    i++;
                    len++;
                }
            }
            else{
                i++;
            }

            count += len * (len + 1)/2;
        }

        return count;
    }
}




// Approach 3
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public long zeroFilledSubarray(int[] nums) {
        int n = nums.length;
        long result = 0;
        int count = 0;

        for(int i = 0; i<n; i++){
            if(nums[i] == 0){
                count++;
            }
            else{
                count = 0;
            }

            result += count;
        }

        return result;
    }
}