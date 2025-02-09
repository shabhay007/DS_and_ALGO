// LeetCode Medium - 2364


// Brute Force - GIVES TLE
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        long badPairs = 0;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                if((j - i) != (nums[j] - nums[i])){
                    badPairs++;
                }
            }
        }

        return badPairs;
    }
}





// Optimal - Using HashMap
// T.C. - O(2n)
// S.C. - O(2n)
class Solution {
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        long badPairs = 0;
        int[] temp = new int[n];

        // making the given relation into (nums[i] - i) != (nums[j] - j)
        for(int i = 0; i<n; i++){ // O(n)
            temp[i] = nums[i] - i;
        }

        HashMap<Integer, Integer> countMap = new HashMap<>();

        for(int i = 0; i<n; i++){ // O(n)
            int numsInLeftOfJ = i;
            int currNumsSeenInLeft = countMap.getOrDefault(temp[i], 0);
            badPairs += numsInLeftOfJ - currNumsSeenInLeft;

            countMap.put(temp[i], countMap.getOrDefault(temp[i], 0) + 1);
        }

        return badPairs;
    }
}





// Optimal 2 - Using HashMap (Slight change in Optimal 1)
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        long badPairs = 0;

        HashMap<Integer, Integer> countMap = new HashMap<>();

        for(int i = 0; i<n; i++){ // O(n)
            int numsInLeftOfJ = i;
            int currNumsSeenInLeft = countMap.getOrDefault(nums[i] - i, 0);
            badPairs += numsInLeftOfJ - currNumsSeenInLeft;

            countMap.put(nums[i] - i, countMap.getOrDefault(nums[i] - i, 0) + 1);
        }

        return badPairs;
    }
}





// Optimal 3 - Using HashMap (Slight change in Optimal 1)
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        long badPairs = 0;

        HashMap<Integer, Integer> countMap = new HashMap<>();

        for(int i = 0; i<n; i++){ // O(n)
            int totalPairs = i;
            int goodPairsWithCurr = countMap.getOrDefault(nums[i] - i, 0);
            badPairs += totalPairs - goodPairsWithCurr;

            countMap.put(nums[i] - i, countMap.getOrDefault(nums[i] - i, 0) + 1);
        }

        return badPairs;
    }
}