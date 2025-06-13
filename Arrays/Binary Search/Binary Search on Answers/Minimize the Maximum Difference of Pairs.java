// LeetCode Medium - 2616


// Approach - Binary Search on Answers
// T.C. - O(2n.log(n))
// S.C. - O(1)
class Solution {
    public boolean isValid(int mid, int nums[], int p){
        int n = nums.length;
        int countPairs = 0;
        int i = 0;

        while(i < n-1){
            if(nums[i+1] - nums[i] <= mid){
                countPairs++;
                i += 2;
            }
            else{
                i++;
            }
        }

        return countPairs >= p;
    }

    public int minimizeMax(int[] nums, int p) {
        int n = nums.length;
        Arrays.sort(nums);

        int l = 0;
        int r = nums[n-1] - nums[0];
        int result = 0;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(isValid(mid, nums, p)){
                result = mid;
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return result;
    }
}