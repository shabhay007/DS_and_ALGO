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




// Approach 2 -> 2 pass
// T.C. - O(n + k)
// S.C. - O(1)
class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;

        int length = 1; // element at index 0.
        int lastColor = colors[0];
        int groups = 0;

        // 1st pass
        for(int i = 1; i<n; i++){
            if(colors[i] == lastColor){
                length = 1;
                lastColor = colors[i];
                continue;
            }

            length++;

            if(length >= k){
                groups++;
            }

            lastColor = colors[i];
        }

        // 2nd pass
        for(int i = 0; i < k-1; i++){ // for k-1 starting elements
            if(colors[i] == lastColor){
                length = 1;
                lastColor = colors[i];
                continue;
            }

            length++;

            if(length >= k){
                groups++;
            }

            lastColor = colors[i];
        }

        return groups;
    }
}




// Approach 3 -> 2 pass with slight optimization in Approach 2
// T.C. - O(n + k)
// S.C. - O(1)
class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;

        int length = 1; // element at index 0.
        int lastColor = colors[0];
        int groups = 0;

        // 1st pass
        for(int i = 1; i<n; i++){
            if(colors[i] == lastColor){
                length = 1;
                lastColor = colors[i];
                continue;
            }

            length++;

            if(length >= k){
                groups++;
            }

            lastColor = colors[i];
        }

        // 2nd pass
        for(int i = 0; i < k-1; i++){ // for k-1 starting elements
            if(colors[i] == lastColor){
                length = 1;
                lastColor = colors[i];

                // since we are checking here only k elements and 1 elements
                // is equal, that means we'll never get k sized window
                // so we can use break instead of continue;
                break; // only change
            }

            length++;

            if(length >= k){
                groups++;
            }

            lastColor = colors[i];
        }

        return groups;
    }
}