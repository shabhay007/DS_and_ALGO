// LeetCode - 2654



// Approach 1 - Brute Force
// T.C. - O(n*n*log(maxEl))
// S.C. - O(1)
class Solution {
    public int getGCD(int a, int b){ // O(log(maxEl))
        while(b != 0){
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }

    public int minOperations(int[] nums) {
        int n = nums.length;
        int countOfOne = 0;

        for(int num : nums){
            if(num == 1){
                countOfOne++;
            }
        }
        
        // if there is 1 present in nums
        if(countOfOne != 0){
            return n - countOfOne;
        }

        // if not try to make one 1 in min ops
        int minOps = Integer.MAX_VALUE;

        for(int i = 0; i<n; i++){
            int gcd = nums[i];

            for(int j = i+1; j<n; j++){
                gcd = getGCD(gcd, nums[j]);

                if(gcd == 1){
                    minOps = Math.min(minOps, j-i);
                    break;
                }
            }
        }

        return (minOps == Integer.MAX_VALUE) ? -1 : (n - 1) + minOps;
    }
}