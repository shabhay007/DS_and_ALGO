// LeetCode - 1920



// Approach 1
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int[] buildArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            result[i] = nums[nums[i]];
        }

        return result;
    }
}




// Approach 2 - Without using extra space
// T.C. - O(2n)
// S.C. - O(1)

/* 
    Doing the modification in-place, we have to store the number in such a way
    that it stores both the numbers before and after modification.
    And to do that, we have to use y = a (old) + (b (new).x)
    here, x can be 100, 400 ... n (but you should choose as per your ease)
    BUT, it should be out of the constraints. For ex. in it nums[i] <= 1000;
    then, the x should be > 1000

    then, old num = y % x
    and, new num = y/x
*/

class Solution {
    public int[] buildArray(int[] nums) {
        int n = nums.length;

        // storing both number : y = a (old) + (b (new).x)
        for(int i = 0; i<n; i++){
            nums[i] = nums[i] + (nums[nums[i]] % n) * n;
        }

        // now dividing the num by n to get the desired result
        for(int i = 0; i<n; i++){
            nums[i] = nums[i]/n;
        }

        return nums;
    }
}