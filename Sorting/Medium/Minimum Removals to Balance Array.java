// LeetCode - 3634



// Approach 1 - Soting + Binary Search
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int getLastPosition(int[] nums, long target, int n){
        int l = 0;
        int r = n-1;

        int pos = -1;

        while(l <= r){
            int mid = l + (r-l)/2;

            if(nums[mid] <= target){
                pos = mid;
                l = mid + 1;
            }
            else{
                r = mid - 1;
            }
        }

        return pos;
    }

    public int minRemoval(int[] nums, int k) {
        int n = nums.length;

        if(n == 1){
            return 0;
        }

        Arrays.sort(nums);
        int minNum = n;

        for(int i = 0; i<n; i++){
            long target =  nums[i] * k;
            int pos = getLastPosition(nums, target, n);

            if(pos >= i){
                int num = n - (pos - i + 1);
                minNum = Math.min(minNum, num);
            }
        }

        return minNum;
    }
}