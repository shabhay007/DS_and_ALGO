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





// Approach 2 - Sorting + Sliding Window
// T.C. - O(nlog(n) + 2n)
// S.C. - O(1)
class Solution {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        int maxL = 1;

        Arrays.sort(nums);

        int i = 0;
        int j = 0;
        int maxEl = nums[0];
        int minEl = nums[0];

        while(j < n){
            minEl = nums[i];
            maxEl = nums[j];
            long target = (long) minEl * k;

            if(i < j && maxEl > target){
                i++;
                minEl = nums[i];
            }

            // finding max length subarray so that, we can minimize the no of removal
            maxL = Math.max(maxL, j-i+1);

            j++;
        }

        return n - maxL;
    }
}