// LeetCode - 2210



// Approach 1
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int countHillValley(int[] nums) {
        int n = nums.length;
        int count = 0;

        for(int i = 1; i<n-1; i++){
            if(nums[i] == nums[i-1]){
                continue;
            }

            int nextNonEqual = -1;
            int j = i+1;

            while(j < n){
                if(nums[j] != nums[i]){
                    nextNonEqual = nums[j];
                    break;
                }

                j++;
            }

            int prevNonEqual = -1;
            j = i-1;

            while(j >= 0){
                if(nums[j] != nums[i]){
                    prevNonEqual = nums[j];
                    break;
                }

                j--;
            }

            if(prevNonEqual != -1 && nextNonEqual != -1 && nums[i] > prevNonEqual && nums[i] > nextNonEqual){
                count++;
            }
            else if(prevNonEqual != -1 && nextNonEqual != -1 && nums[i] < prevNonEqual && nums[i] < nextNonEqual){
                count++;
            }
        }

        return count;
    }
}





// Approach 2 - Two Pointers
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int countHillValley(int[] nums) {
        int n = nums.length;
        int count = 0;
        int i = 0; // points non equal on left side
        int j = 1; // points on current as well as non equal on right side (j+1)

        while(j+1 < n){
            if((nums[j] > nums[i] && nums[j] > nums[j+1]) || 
            (nums[j] < nums[i] && nums[j] < nums[j+1])){
                count++;
                i = j;
            }

            j++;
        }

        return count;
    }
}