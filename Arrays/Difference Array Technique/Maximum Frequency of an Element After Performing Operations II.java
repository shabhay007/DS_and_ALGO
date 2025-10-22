// LeetCode - 3347



// Approach 1 - Prefix Sum (Gives MLE)
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





// Approach 2 - Difference Array Technique (Gives MLE)
// T.C. - O(n); n = max + k
// S.C. - O(n)
class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int max = Arrays.stream(nums).max().getAsInt() + k;

        int[] diff = new int[max+2];
        Map<Integer, Integer> freq = new HashMap<>();

        // populating prefix arr frequency
        for(int num : nums){
            freq.put(num, freq.getOrDefault(num, 0) + 1);

            int left = Math.max(0, num - k);
            int right = Math.min(max, num + k);

            diff[left]++;
            diff[right + 1]--;
        }

        int result = 1;

        // now processing to get the result
        for(int target = 0; target <= max; target++){
            if(target > 0){
                diff[target] += diff[target - 1];
            }

            int targetFreq = freq.getOrDefault(target, 0);
            int needConversion = diff[target] - targetFreq;
            int maxPossibleConversion = Math.min(needConversion, numOperations);

            result = Math.max(result, maxPossibleConversion + targetFreq);
        }

        return result;
    }
}





// Approach 3 - Using Difference Array Technique
// T.C : O(nlogn)
// S.C : O(n)
class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int maxVal = Arrays.stream(nums).max().getAsInt() + k;
        TreeMap<Integer, Integer> diff = new TreeMap<>();
        HashMap<Integer, Integer> freq = new HashMap<>();

        // O(n log n)
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);

            int l = Math.max(num - k, 0);
            int r = Math.min(num + k, maxVal);

            diff.put(l, diff.getOrDefault(l, 0) + 1);
            diff.put(r + 1, diff.getOrDefault(r + 1, 0) - 1);

            // Ensure key exists for later iteration
            diff.putIfAbsent(num, diff.getOrDefault(num, 0));
        }

        int result = 1;
        int cumSum = 0;

        // O(n)
        for (Map.Entry<Integer, Integer> entry : diff.entrySet()) {
            int target = entry.getKey();
            int val = entry.getValue();

            cumSum += val;
            int targetFreq = freq.getOrDefault(target, 0);
            int needConversion = cumSum - targetFreq;

            int maxPossibleFreq = Math.min(needConversion, numOperations);
            result = Math.max(result, targetFreq + maxPossibleFreq);
        }

        return result;
    }
}