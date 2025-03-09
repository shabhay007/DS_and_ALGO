// LeetCode Medium - 3208



// Approach 1 - Circular Sliding Window
// T.C. - O(n + k)
// S.C. - O(1)
class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;
        int result = 0;
        int left = 0;

        // expanding the subarray
        // we can also use while loop here
        for(int right = 1; right < (n+k-1); right++){
            // skipping entire subarray with non alternating colors
            if(colors[right % n] == colors[(right - 1) % n]){
                left = right;
            }

            if(right - left + 1 == k){
                result++;
                left++; // moving to next subarray of size k
            }
        }

        return result;
    }
}