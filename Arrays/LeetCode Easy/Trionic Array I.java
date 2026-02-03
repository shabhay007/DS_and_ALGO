// LeetCode - 3637



// Approach 1 - Brute Force
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public boolean isIncreasing(int start, int end, int[] nums){
        for(int i = start; i<end; i++){
            if(nums[i] >= nums[i+1]){
                return false;
            }
        }

        return true;
    }
    
    public boolean isDecreasing(int start, int end, int[] nums){
        for(int i = start; i<end; i++){
            if(nums[i] <= nums[i+1]){
                return false;
            }
        }

        return true;
    }

    public boolean isTrionic(int[] nums) {
        int n = nums.length;

        if(n <= 3){
            return false;
        }

        for(int p = 1; p<=n-3; p++){
            for(int q = p+1; q<=n-2; q++){
                if (
                    isIncreasing(0, p, nums) &&
                    isDecreasing(p, q, nums) &&
                    isIncreasing(q, n - 1, nums) &&

                    nums[p - 1] < nums[p] &&
                    nums[p] > nums[p + 1] &&
                    nums[q - 1] > nums[q] &&
                    nums[q] < nums[q + 1]
                ){
                    return true;
                }
            }
        }

        return false;
    }
}





// Approach 2 - Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;

        if(n <= 3){
            return false;
        }

        int i = 0;

        // checking increasing sequence
        while(i+1 < n && nums[i] < nums[i+1]){
            i++;
        }

        if(i == 0 || i == n-1){
            return false;
        }

        // checking decreasing sequence
        while(i+1 < n && nums[i] > nums[i+1]){
            i++;
        }

        if(i == n-1){
            return false;
        }

        // checking increasing sequence
        while(i+1 < n && nums[i] < nums[i+1]){
            i++;
        }

        return i == n-1;
    }
}