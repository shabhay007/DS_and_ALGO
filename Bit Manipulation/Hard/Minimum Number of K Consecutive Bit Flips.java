// LeetCode Hard - 995


// Approach 1 - Brute Force (Try out all subarray starts with 0) (Gives TLE)
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int minOperations = 0;

        for(int i = 0; i<n; i++){
            if(nums[i] == 0){
                if(i + k > n){
                    return -1; // not possible to make all 0 to 1
                }

                minOperations++;

                for(int j = i; j < (i+k); j++){
                    nums[j] = (nums[j] == 0) ? 1 : 0;
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
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int minOperations = 0;

        int flipCountFromPastForCurrIdx = 0;
        boolean[] isFlipped = new boolean[n];

        for(int i = 0; i<n; i++){
            if(i >= k && isFlipped[i - k] == true){
                flipCountFromPastForCurrIdx--;
            }

            if(flipCountFromPastForCurrIdx % 2 == nums[i]){ // flip at index i
                if(i + k > n){
                    return -1;
                }

                flipCountFromPastForCurrIdx++;
                isFlipped[i] = true;
                minOperations++;
            }
        }

        return minOperations;
    }
}





// Approach 3 (Optimal) - No extra space (modifying input array)
// T.C. - O(n)
// S.C. - O(1) - Not recommended to modify given input
class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int minOperations = 0;

        int flipCountFromPastForCurrIdx = 0;

        for(int i = 0; i<n; i++){
            if(i >= k && nums[i - k] == 5){ // 5 is used for marking for flips
                flipCountFromPastForCurrIdx--;
            }

            if(flipCountFromPastForCurrIdx % 2 == nums[i]){ // flip at index i
                if(i + k > n){
                    return -1;
                }

                flipCountFromPastForCurrIdx++;
                nums[i] = 5;
                minOperations++;
            }
        }

        return minOperations;
    }
}





// Approach 4 (Optimal) - Using Deque for marking (RECOMMENDED)
// T.C. - O(n)
// S.C. - O(k)
class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int minOperations = 0;

        int flipCountFromPastForCurrIdx = 0;
        Deque<Integer> dq = new LinkedList<>();

        for(int i = 0; i<n; i++){
            if(i >= k){ // 5 is used for marking for flips
                flipCountFromPastForCurrIdx -= dq.pollFirst();
            }

            if(flipCountFromPastForCurrIdx % 2 == nums[i]){ // flip at index i
                if(i + k > n){
                    return -1;
                }

                flipCountFromPastForCurrIdx++;
                dq.offerLast(1);
                minOperations++;
            }
            else{
                dq.offerLast(0); // haven't flipped at index i
            }
        }

        return minOperations;
    }
}