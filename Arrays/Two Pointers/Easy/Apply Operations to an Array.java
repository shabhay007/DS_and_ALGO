// LeetCode Easy - 2460



// Approach 1
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public int[] applyOperations(int[] nums) {
        int n = nums.length;

        for(int i = 0; i < n-1; i++){
            if(nums[i] == nums[i+1]){
                nums[i] = 2 * nums[i];
                nums[i+1] = 0;
            }
        }

        int[] result = new int[n];
        int j = 0;

        for(int i = 0; i < n; i++){
            if(nums[i] != 0){
                result[j++] = nums[i];
            }
        }

        return result;
    }
}






// Approach 2 (Optimal)
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[] applyOperations(int[] nums) {
        int n = nums.length;

        for(int i = 0; i < n-1; i++){
            if(nums[i] == nums[i+1]){
                nums[i] = 2 * nums[i];
                nums[i+1] = 0;
            }
        }

        int i = 0;
        int j = 0;
        
        while(j < n){
            if(nums[j] != 0){
                swap(i, j, nums);
                i++;
            }
            
            j++;
        }

        return nums;
    }
}




// Approach 3 (Optimal) - (One Pass)
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        int j = 0;

        for(int i = 0; i < n; i++){
            if(i+1 < n && nums[i] == nums[i+1]){
                nums[i] = 2 * nums[i];
                nums[i+1] = 0;
            }

            if(nums[i] != 0){
                if(i != j){
                    swap(i, j, nums);
                }
                
                j++;
            }
        }

        return nums;
    }
}