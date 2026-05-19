// LeetCode - 2540



// Approach 1 - Two Pointers
// T.C. - O(n)
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
// T.C. - O(n)
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