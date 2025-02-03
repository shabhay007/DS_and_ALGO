// LeetCode Easy - 3105


// Brute Force 1
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public boolean isIncreasing(int start, int end, int[] nums){
        for(int i = start; i < end; i++){
            if(nums[i] >= nums[i+1]){
                return false;
            }
        }

        return true;
    }

    public boolean isDecreasing(int start, int end, int[] nums){
        for(int i = start; i < end; i++){
            if(nums[i] <= nums[i+1]){
                return false;
            }
        }

        return true;
    }


    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        int maxLen = 1; // min length of any monotonic subarray

        for(int i = 0; i<n; i++){
            for(int j = i+1; j < n; j++){
                if(isIncreasing(i, j, nums)){
                    maxLen = Math.max(maxLen, j - i + 1);
                }

                if(isDecreasing(i, j, nums)){
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }
}





// Brute Force Approach 2
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        int result = 0;

        for(int i = 0; i<n; i++){
            int inc = 1;
            int j = i+1;

            while(j < n && nums[j] > nums[j-1]){
                inc++;
                j++;
            }

            int dec = 1;
            j = i+1;

            while(j < n && nums[j] < nums[j-1]){
                dec++;
                j++;
            }

            result = Math.max(result, Math.max(inc, dec));
        }

        return result;
    }
}





// Better - Using Stacks
// T.C. - O(n)
// S.C. - O(2n)
class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;

        Stack<Integer> incStack = new Stack<>();
        Stack<Integer> decStack = new Stack<>();

        int maxIncLen = 1;
        int maxDecLen = 1;

        for(int i = 0; i<n; i++){
            // storing monotonically increasing subarray in stack
            if(!incStack.isEmpty() && incStack.peek() < nums[i]){
                incStack.push(nums[i]);
            }
            else{
                // resets the stack if violation occurs
                // incStack = new Stack<>(); // it creates new Stack
                
                // it uses existing stack by clearing all the contents inside it
                // so, it is also more faster and memory efficient
                incStack.clear();
                incStack.push(nums[i]);
            }

            // now calculating the maxLenth
            maxIncLen = Math.max(maxIncLen, incStack.size());

            // storing monotonically decreasing subarray in stack
            if(!decStack.isEmpty() && decStack.peek() > nums[i]){
                decStack.push(nums[i]);
            }
            else{
                // resets the stack if violation occurs
                // decStack = new Stack<>();

                decStack.clear();
                decStack.push(nums[i]);
            }

            // now calculating the maxLength
            maxDecLen = Math.max(maxDecLen, decStack.size());
        }

        return Math.max(maxIncLen, maxDecLen);
    }
}





// Optimal
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        int result = 1;

        int inc = 1;
        int dec = 1;

        for(int i = 1; i<n; i++){
            if(nums[i] > nums[i-1]){
                inc++;

                // resetting the decreasing counter
                dec = 1;
            }
            else if(nums[i] < nums[i-1]){
                dec++;
                
                // resetting the increasing counter
                inc = 1;
            }
            else{
                // if contains equal elements, then that will neither be increasing nor
                // decreasing
                inc = 1;
                dec = 1;
            }

            // updating the result
            result = Math.max(result, Math.max(inc, dec));
        }

        return result;
    }
}