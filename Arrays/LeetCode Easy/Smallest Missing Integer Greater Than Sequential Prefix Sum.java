// LeetCode - 2996



// Approach 1 - Simulation
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i<n; i++){
            set.add(nums[i]);
        }

        int sum = nums[0];
        for(int i = 1; i<n; i++){
            if(nums[i-1] + 1 == nums[i]){
                sum += nums[i];
            }
            else{
                break;
            }
        }

        while(set.contains(sum)){
            sum++;
        }

        return sum;
    }
}