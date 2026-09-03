// LeetCode - 3876



// Approach 1 - Maths
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        int minEven = Integer.MAX_VALUE;
        int minOdd = Integer.MAX_VALUE;

        for(int i = 0; i<n; i++){
            if(nums1[i] % 2 == 0){
                minEven = Math.min(minEven, nums1[i]);
            }
            else{
                minOdd = Math.min(minOdd, nums1[i]);
            }
        }

        if(minEven == Integer.MAX_VALUE || minOdd == Integer.MAX_VALUE){
            return true;
        }

        if(minEven > minOdd){
            return true;
        }

        return false;
    }
}