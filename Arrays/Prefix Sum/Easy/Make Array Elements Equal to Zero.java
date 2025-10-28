// LeetCode - 3354



// Approach 1 - Simulation
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    private boolean isValidSelection(int curr, int count, int[] nums, int direction) {
        int[] arr = nums.clone(); // avoid modifying the original array

        while (curr >= 0 && curr < nums.length && count > 0) {
            if (arr[curr] > 0) {
                arr[curr]--;
                direction *= -1;

                if(arr[curr] == 0){
                    count--;
                }
            }

            // i.e. it will move in the direction it is moving
            // if direction is -ve, curr will decrease and move in that direction
            // and vice versa
            curr += direction;
        }
        
        // all must become 0 for a valid selection
        return count == 0;
    }

    public int countValidSelections(int[] nums) {
        int n = nums.length;
        int countOfNonZero = 0;

        for(int num : nums){
            if(num != 0){
                countOfNonZero++;
            }
        }


        int selection = 0;

        for(int curr = 0; curr < n; curr++){
            if(nums[curr] == 0){
                if(isValidSelection(curr, countOfNonZero, nums, 1)){
                    selection++;
                }

                if(isValidSelection(curr, countOfNonZero, nums, -1)){
                    selection++;
                }
            }
        }

        return selection;
    }
}






// Approach 2 - Using Prefix sum
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        int totalSum = 0;

        for(int num : nums){
            totalSum += num;
        }


        int selection = 0;
        int currSum = 0;

        for(int curr = 0; curr < n; curr++){
            currSum += nums[curr];

            if(nums[curr] == 0){
                int leftSum = currSum;
                int rightSum = totalSum - currSum;

                if(leftSum == rightSum){
                    selection += 2;
                }
                else if(Math.abs(leftSum - rightSum) == 1){
                    selection += 1;
                }
            }
        }

        return selection;
    }
}