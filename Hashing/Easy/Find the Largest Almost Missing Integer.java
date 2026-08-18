// LeetCode - 3471



// Approach 1 - Observation Based
// T.C. - O(n * k)
// S.C. - O(1)
class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i<=n-k; i++){
            Set<Integer> set = new HashSet<>();

            for(int j = i; j < i+k; j++){
                set.add(nums[j]);
            }

            // now counting a number is present in how many subarrays
            for(int x : set){
                map.put(x, map.getOrDefault(x, 0) + 1);
            }
        }

        // now processing the element which comes only in 1 subarray which is max
        int result = -1;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int element = entry.getKey();
            int freq = entry.getValue();

            if(freq == 1){
                result = Math.max(result, element);
            }
        }

        return result;
    }
}






// Approach 2 - Observation Based + hash array
// T.C. - O(n*k)
// S.C. - O(1)
class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] numOfSubarrays = new int[52];

        for(int i = 0; i<=n-k; i++){
            Set<Integer> set = new HashSet<>();

            for(int j = i; j < i+k; j++){
                set.add(nums[j]);
            }

            // now counting a number is present in how many subarrays
            for(int x : set){
                numOfSubarrays[x+1]++;
            }
        }

        System.out.println(numOfSubarrays);

        // now processing the element which comes only in 1 subarray which is max
        int result = -1;
        for(int i = 0; i<52; i++){
            if(numOfSubarrays[i] == 1){
                result = Math.max(result, i-1); // since every element stored at x+1
            }
        }

        return result;
    }
}






// Approach 3 - Observation Based + hash array
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] freq = new int[51];
        int max = -1;

        for(int num : nums){
            freq[num]++;
            max = Math.max(max, num);
        }

        // case I
        if(k == n){
            return max;
        }

        // case II
        if(k == 1){
            for(int i = 50; i>=0; i--){
                if(freq[i] == 1){
                    return i;
                }
            }

            return -1;
        }

        // case III
        int result = -1;

        if(freq[nums[0]] == 1){
            result = Math.max(result, nums[0]);
        }

        if(freq[nums[n-1]] == 1){
            result = Math.max(result, nums[n-1]);
        }

        return result;
    }
}