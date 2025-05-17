// LeetCode - 75


// Approach 1 - Counting
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int countZero = 0;
        int countOne = 0;
        int countTwo = 0;

        for(int num : nums){
            if(num == 0) countZero++;
            if(num == 1) countOne++;
            else countTwo++;
        }

        // appending 0
        for(int i = 0; i<countZero; i++){
            nums[i] = 0;
        }

        // appending 1
        for(int i = countZero; i<countZero + countOne; i++){
            nums[i] = 1;
        }

        // appending 2
        for(int i = countZero + countOne; i<n; i++){
            nums[i] = 2;
        }
    }
}





// Approach 2 - Dutch National Flag Algorithm (Striver's lecture - Amazing explanation)
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void sortColors(int[] nums) {
        int n = nums.length;
        int i = 0; // denotes 0
        int j = 0; // denotes 1
        int k = n-1; // denotes 2

        while(j <= k){
            if(nums[j] == 1){
                j++;
            }
            else if(nums[j] == 2){
                swap(j, k, nums);
                k--;
            }
            else{ // in case of 0
                swap(i, j, nums);
                i++;
                j++;
            }
        }
    }
}