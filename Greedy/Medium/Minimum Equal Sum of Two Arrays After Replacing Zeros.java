// LeetCode - 2918



// Approach 1 - Greedy
// T.C. - O(n+m)
// S.C. - O(1)
class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        // for nums1 array
        long sum1 = 0;
        int zeroCount1 = 0;

        for(int num : nums1){
            sum1 += num;

            if(num == 0){
                zeroCount1++;
            }
        }


        // for nums2 array
        long sum2 = 0;
        int zeroCount2 = 0;

        for(int num : nums2){
            sum2 += num;

            if(num == 0){
                zeroCount2++;
            }
        }

        // minSum - let's add all 1's in place of 0
        // then the resultant sum will be minimum
        long minSum1 = sum1 + zeroCount1;
        long minSum2 = sum2 + zeroCount2;

        if(zeroCount1 == 0 && zeroCount2 == 0){
            if(sum1 == sum2){
                return sum1;
            }
        }

        if(minSum1 > minSum2 && zeroCount2 == 0) return -1;
        if(minSum1 < minSum2 && zeroCount1 == 0) return -1;

        return Math.max(minSum1, minSum2);
    }
}