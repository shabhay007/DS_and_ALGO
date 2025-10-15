// LeetCode - 3350



// Approach 1 - Binary Search on Answer (Gives TLE)
// T.C. - O(n * k * log(n))
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

    public boolean isPossible(int k, List<Integer> nums){
        int n = nums.size();

        for(int i = 0; (i + 2*k) <= n; i++){
            if(isStrictlyIncreasing(i, i+k, nums) && 
                isStrictlyIncreasing(i+k, i+2*k, nums)){
                return true;
            }
        }

        return false;
    }

    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int l = 0;
        int r = n;

        int result = 0;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(isPossible(mid, nums)){
                result = mid;
                l = mid + 1;
            }
            else{
                r = mid - 1;
            }
        }

        return result;
    }
}




// Approach 2 - Sliding window like concept
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int prevStreak = 0;
        int currStreak = 1;
        int max = 0;

        for(int i = 1; i<n; i++){
            if(nums.get(i) > nums.get(i-1)){
                currStreak++;
            }
            else{
                prevStreak = currStreak;
                currStreak = 1;
            }

            max = Math.max(max, currStreak/2);

            max = Math.max(max, Math.min(prevStreak, currStreak));
        }

        return max;
    }
}