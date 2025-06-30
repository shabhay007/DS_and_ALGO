// LeetCode - 594



// Approach 1 (Brute Force) - Sorting
// T.C. - O(nlog(n) + n^2)
// S.C. - O(1)
class Solution {
    public int findLHS(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);
        int maxLength = 0;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                if(nums[j] - nums[i] == 1){
                    maxLength = Math.max(maxLength, j-i+1);
                }
            }
        }

        return maxLength;
    }
}





// Approach 2 - Map
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public int findLHS(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // subSeq can contain only 2 element in any number
        // as diff = 1 -> {min, min+1}
        int maxLength = 0;

        for(int i = 0; i<n; i++){
            if(map.containsKey(nums[i] + 1)){
                int currLength = map.get(nums[i]) + map.get(nums[i] + 1);
                maxLength = Math.max(maxLength, currLength);
            }
        }

        return maxLength;
    }
}