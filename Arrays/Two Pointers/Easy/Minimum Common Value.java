// LeetCode - 2540



// Approach 1 - Two Pointers
// T.C. - O(m + n)
// S.C. - O(1)
class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int i = 0;
        int j = 0;
        boolean flag = true;

        while(flag){
            while(i < m && nums1[i] < nums2[j]){
                i++;
            }

            while(i < m && j < n && nums1[i] > nums2[j]){
                j++;
            }

            if(i < m && j < n && nums1[i] == nums2[j]){
                flag = false;
                return nums1[i];
            }

            if(i == m || j == n){
                flag = false;
            }
        }

        return -1;
    }
}





// Approach 2 - Two Pointers
// T.C. - O(m + n)
// S.C. - O(1)
class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int i = 0;
        int j = 0;
        
        while(i < m && j < n){
            if(nums1[i] == nums2[j]){
                return nums1[i];
            }
            else if(nums1[i] > nums2[j]){
                j++;
            }
            else{
                i++;
            }
        }

        return -1;
    }
}





// Approach 3 - Binary Search (If one arr is much longer than other, it's most efficient than other two approaches)
// T.C. - O(mlogn); where m is the length of the shorter array and n is the length of the longer array
// S.C. - O(1)
class Solution {
    public boolean binarySearch(int target, int[] nums){
        int l = 0;
        int r = nums.length - 1;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(nums[mid] > target){
                r = mid - 1;
            }
            else if(nums[mid] < target){
                l = mid + 1;
            }
            else{
                return true;
            }
        }

        return false;
    }

    public int getCommon(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        
        // Binary search should be done on the larger array
        // If nums1 is longer, call getCommon with the arrays swapped
        if(m > n){
            return getCommon(nums2, nums1);
        }
        
        // Searching for each element of nums1 in nums2
        // Return the first common element found
        for(int num : nums1){
            if(binarySearch(num, nums2)){
                return num;
            }
        }

        return -1;
    }
}