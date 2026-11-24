// LeetCode - 3411



// Approach 1 - Maths
// T.C. - O(n^2 * nlog(M)); M = max arr element
// S.C. - O(1)
class Solution {
    public int gcd(int st, int end, int[] nums) {
        int ans = nums[st];

        for (int i = st + 1; i <= end; i++) {
            ans = gcdTwo(ans, nums[i]);
        }

        return ans;
    }

    public int gcdTwo(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    public long lcm(int st, int end, int[] nums) {
        long ans = nums[st];

        for (int i = st + 1; i <= end; i++) {
            ans = (ans / gcdTwo((int)ans, nums[i])) * nums[i];
        }

        return ans;
    }

    public boolean solve(int st, int end, int[] nums){
        int prod = 1;
        for(int i = st; i <= end; i++){
            prod *= nums[i];
        }

        return prod == gcd(st, end, nums) * lcm(st, end, nums);
    }

    public int maxLength(int[] nums) {
        int n = nums.length;
        int result = 1;

        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                if(solve(i, j, nums)){
                    result = Math.max(result, j-i+1);
                }
            }
        }

        return result;
    }
}