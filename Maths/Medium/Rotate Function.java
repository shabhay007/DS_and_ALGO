// LeetCode - 396



// Approach 1 - Brute Force
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;

        int start = 0;
        int max = Integer.MIN_VALUE;

        while(start < n){
            int i = start;
            long curr = 0;

            for(int k = 0; k<n; k++){
                curr += k * nums[i % n];
                i++;
            }

            max = Math.max(max, (int) curr);
            start++;
        }

        return max;
    }
}






// Approach 2 - Maths
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int sum = 0; // total sum
        int F = 0; // F(0)

        for(int i = 0; i<n; i++){
            sum += nums[i];
            F += i * nums[i];
        }

        // now processing newF from prev
        int max = Integer.MIN_VALUE;
        for(int k = 0; k<n; k++){
            int newF = F + sum - (n * nums[n - 1 - k]);
            max = Math.max(max, newF);
            F = newF;
        }

        return max;
    }
}