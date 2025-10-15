// LeetCode - 3349



// Approach 1 - Brute Force
// T.C. - O(n * k)
// S.C. - O(1)
class Solution {
    public boolean isStrictlyIncreasing(int start, int end, List<Integer> nums){
        for(int i = start; i<end-1; i++){
            if(nums.get(i) >= nums.get(i+1)){
                return false;
            }
        }

        return true;
    }

    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();

        for(int i = 0; (i + 2*k) <= n; i++){
            if(isStrictlyIncreasing(i, i+k, nums) && isStrictlyIncreasing(i+k, i+2*k, nums)){
                return true;
            }
        }

        return false;
    }
}





// Approach 2 - Sliding window concept
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        int currStreak = 1;
        int prevStreak = 0;

        for(int i = 1; i < n; i++){
            if(nums.get(i) > nums.get(i-1)){
                currStreak++;
            }
            else{
                prevStreak = currStreak;
                currStreak = 1;
            }

            if(currStreak/2 >= k){
                return true;
            }

            if(Math.min(prevStreak, currStreak) >= k){
                return true;
            }
        }

        return false;
    }
}