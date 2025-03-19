// LeetCode Medium - 3191



// Approach 1 - Brute Force (Trying all subarrays of size 3)
// T.C. - O(n * 3)
// S.C. - O(1)
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int minOperations = 0;

        for(int i = 0; i<n; i++){
            if(nums[i] == 0){
                if(i + 3 > n){
                    return -1; // not possible to make all 0 to 1
                }

                minOperations++;

                for(int j = i; j < (i+3); j++){
                    // nums[j] = (nums[j] == 0) ? 1 : 0;

                    // OR
                    
                    nums[j] ^= 1; // Flip using XOR
                }
            }
        }

        return minOperations;
    }
}




// Approach 2 (Optimal)
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int minOperations = 0;
        int flipCountFromPastForCurrIdx = 0;
        boolean[] isFlipped = new boolean[n];

        for(int i = 0; i<n; i++){
            if(i >= 3 && isFlipped[i - 3]){
                flipCountFromPastForCurrIdx--;
            }

            if(flipCountFromPastForCurrIdx % 2 == nums[i]){
                if(i + 3 > n){
                    return -1;
                }

                flipCountFromPastForCurrIdx++;
                minOperations++;
                isFlipped[i] = true;
            }
        }

        return minOperations;
    }
}




// Approach 3 (Optimal) - Using Deque
// T.C. - O(n)
// S.C. - O(k)
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int minOperations = 0;
        int flipCountFromPastForCurrIdx = 0;
        Deque<Integer> dq = new LinkedList<>();

        for(int i = 0; i<n; i++){
            if(i >= 3){
                flipCountFromPastForCurrIdx -= dq.pollFirst();
            }

            if(flipCountFromPastForCurrIdx % 2 == nums[i]){
                if(i + 3 > n){
                    return -1;
                }

                flipCountFromPastForCurrIdx++;
                minOperations++;
                dq.offerLast(1);
            }
            else{
                dq.offerLast(0);
            }
        }

        return minOperations;
    }
}




// Approach 4 (Optimal) - Modifying input array (Not Recommended)
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int minOperations = 0;
        int flipCountFromPastForCurrIdx = 0;

        for(int i = 0; i<n; i++){
            if(i >= 3 && nums[i - 3] == 5){ // 5 is used for marking
                flipCountFromPastForCurrIdx--;
            }

            if(flipCountFromPastForCurrIdx % 2 == nums[i]){
                if(i + 3 > n){
                    return -1;
                }

                flipCountFromPastForCurrIdx++;
                minOperations++;
                nums[i] = 5;
            }
        }

        return minOperations;
    }
}




// Approach 5 (Optimal) - Sliding Window
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int minOperations = 0;

        for(int i = 0; i < (n-2); i++){
            if(nums[i] == 0){
                nums[i] = 1 - nums[i]; // flipping
                nums[i + 1] = 1 - nums[i + 1];
                nums[i + 2] = 1 - nums[i + 2];
                minOperations++;
            }
        }

        // we have checked till n-2
        // now we have to check last 2 elements
        if(nums[n-1] == 0 || nums[n-2] == 0){
            return -1;
        }

        return minOperations;
    }
}