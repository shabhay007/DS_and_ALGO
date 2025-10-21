// LeetCode - 3346



// Approach 1 - Prefix Sum
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int max = -1;

        for(int num : nums){
            max = Math.max(max, num);
        }

        int[] freq = new int[max+1];

        // populating prefix arr frequency
        for(int num : nums){
            freq[num]++;
        }

        // taking cummulative sum of frequency
        for(int i = 1; i <= max; i++){
            freq[i] += freq[i-1];
        }

        
        int result = 0;

        // now processing to get the result
        for(int target = 0; target <= max; target++){
            if(freq[target] == 0){
                continue;
            }

            int left = Math.max(0, target - k);
            int right = Math.min(max, target + k);

            // total elements which lies in the range [target-k, target+k]
            // which can be converted to get the element with the max freq
            int total = freq[right] - ((left > 0) ? freq[left - 1] : 0);

            // element with max freq already which lies in the above range
            int currMaxElementFreq = freq[target] - ((target > 0) ? freq[target - 1] : 0);

            // elements which needs to be converted actually
            int required = total - currMaxElementFreq;

            int maxPossibleConversion = currMaxElementFreq + Math.min(required, numOperations);

            result = Math.max(result, maxPossibleConversion);
        }

        return result;
    }
}