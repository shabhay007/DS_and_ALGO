// LeetCode - 3379



// Approach 1 - Circular Traversal in Array using %
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            int shift = nums[i];
            int idx = ((i + shift) % n + n) % n;
            result[i] = nums[idx];
        }

        return result;
    }
}





// Approach 2 - Circular Traversal in Array using %
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            int shift = nums[i];
            int idx = (i + shift) % n;

            if(idx < 0){
                idx += n;
            }
            
            result[i] = nums[idx];
        }

        return result;
    }
}