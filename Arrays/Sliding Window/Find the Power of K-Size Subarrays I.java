// Approach 1
class Solution {
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;

        
        int[] result = new int[n-k+1];

        for(int i = 0; i<n-k+1; i++){
            boolean isConsecutive = true;

            for(int j = 0; j<k-1; j++){
                if(nums[i+j] + 1 != nums[i+j+1]){
                    isConsecutive = false;
                    break;
                }
            }

            result[i] = isConsecutive ? nums[i+k-1] : -1;
        }

        return result;
    }
}




//Approach 2
class Solutions{
    public boolean isSorted(int[] nums, int start, int k){
        for(int i = 0; i<k - 1; i++){
            if(nums[start + i] >= nums[start + i + 1] || nums[start+i] + 1 != nums[start + i + 1]){
                return false;
            }
        }

        return true;
    }

    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n-k+1];

        for(int i = 0; i<n-k+1; i++){
            if(isSorted(nums, i, k)){
                result[i] = nums[i+k-1];
            }
            else{
                result[i] = -1;
            }
        }

        return result;
    }
}
