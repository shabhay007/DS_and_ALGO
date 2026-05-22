// LeetCode - 154



// Approach 1 - Sorting
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int findMin(int[] nums) {
        Arrays.sort(nums);

        return nums[0];
    }
}




// Approach 2 - Linear Search
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;

        for(int num : nums){
            min = Math.min(min, num);
        }

        return min;
    }
}





// Approach 3 - Binary Search
// T.C. - O(log(n)); O(n) -> worst case
// S.C. - O(1)
class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = n-1;
        int resultIdx = 0;

        while(l <= r){
            while(l < r && nums[l] == nums[l+1]) l++;
            while(l < r && nums[r] == nums[r-1]) r--;

            int mid = l + (r - l)/2;

            if(nums[mid] < nums[resultIdx]){
                resultIdx = mid;
            }

            if(nums[mid] > nums[r]){ // it means rotated and min will be in right
                l = mid + 1;
            }
            else{
                r = mid - 1;
            }
        }

        return nums[resultIdx];
    }
}