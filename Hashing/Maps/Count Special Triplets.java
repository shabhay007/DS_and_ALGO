// LeetCode - 3583



// Approach 1 - Brute Force (Gives TLE)
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int specialTriplets(int[] nums) {
        int n = nums.length;
        int mod = (int) 1e9 + 7;
        int result = 0;

        // i is middle and checking how many numbers are double in left and right
        // of the curr number
        for(int i = 1; i<n-1; i++){
            int leftCount = 0;
            int rightCount = 0;

            // left count
            for(int j = 0; j<i; j++){
                if(nums[j] == nums[i] * 2){
                    leftCount++;
                }
            }

            // right count
            for(int j = i+1; j<n; j++){
                if(nums[j] == nums[i] * 2){
                    rightCount++;
                }
            }

            // System.out.println("leftCount : " + leftCount);
            // System.out.println("rightCount : " + rightCount);

            // total valid triplets will be (left * right)
            result = (result + leftCount * rightCount) % mod;
        }

        return result % mod;
    }
}





// Approach 2 - Using Map
// T.C. - O(2n)
// S.C. - O(2n)
class Solution {
    public int specialTriplets(int[] nums) {
        int n = nums.length;
        int mod = (int) 1e9 + 7;
        int result = 0;

        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();

        for(int i = 0; i<n; i++){
            right.put(nums[i], right.getOrDefault(nums[i], 0) + 1);
        }

        // processing
        for(int i = 0; i<n; i++){
            // reducing the freq from right which is being visited
            right.put(nums[i], right.get(nums[i]) - 1);

            // finding freq
            int leftFreq = left.getOrDefault(nums[i] * 2, 0);
            int rightFreq = right.getOrDefault(nums[i] * 2, 0);
            long count = (1L * leftFreq * rightFreq);

            result = (int) ((result + count) % mod);

            // now updating the left map
            left.put(nums[i], left.getOrDefault(nums[i], 0) + 1);
        }

        return (int) result % mod;
    }
}





// Approach - 3 (Using map : 1 Pass Solution)
// T.C : O(n)
// S.C : O(n)
class Solution {
    static final int M = (int) 1e9 + 7;

    public int specialTriplets(int[] nums) {
        Map<Integer, Integer> validI = new HashMap<>();
        Map<Integer, Integer> validJ = new HashMap<>();

        int result = 0;

        for (int num : nums) {

            // If num is valid k (k must be even)
            if (num % 2 == 0) {
                result = (result + validJ.getOrDefault(num / 2, 0)) % M;
            }

            // Is current num a valid j?
            int updatedJ = (validJ.getOrDefault(num, 0) +
                            validI.getOrDefault(num * 2, 0)) % M;
            validJ.put(num, updatedJ);

            // Count num as a valid i
            validI.put(num, validI.getOrDefault(num, 0) + 1);
        }

        return result;
    }
}