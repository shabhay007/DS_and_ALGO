// LeetCode Medium - 2381


// Brute Force
// T.C. - O(q * n)
// S.C. - O(n)
class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        char arr[] = s.toCharArray();

        for(int[] shift : shifts){
            int start = shift[0];
            int end = shift[1];
            int direction = shift[2];

            for(int i = start; i <= end; i++){
                if(direction == 1){
                    // finding the value ch - 'a' then adding 1 (1 character)
                    // then taking modulo to wrap around, after this add 'a' to get
                    // the resultant character to the value comes through (arr[i] - 'a' + 1) % 26
                    arr[i] = (char) ((arr[i] - 'a' + 1) % 26 + 'a');
                }
                else{
                    // subtracting 1 for backward movement
                    // to avoid -ve values, adding 26 (total alphabets)
                    arr[i] = (char) ((arr[i] - 'a' - 1 + 26) % 26 + 'a');
                }
            }
        }

        return new String(arr);
    }
}



// Optimal - Difference Array Technique
// T.C. - O(q + n + n)
// S.C. - O(n)
class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int diffArray[] = new int[n];

        // STEP 1 - performing queries using Difference Array Technique
        for(int[] shift : shifts){
            int start = shift[0];
            int end = shift[1];
            int direction = shift[2];

            int x;
            if(direction == 0){
                x = -1;
            }
            else{
                x = 1;
            }

            diffArray[start] += x;

            if(end + 1 < n){
                diffArray[end + 1] -= x;
            }
        }

        // STEP 2 - finding cummulative sum of difference array
        for(int i = 1; i<n; i++){
            diffArray[i] = diffArray[i] + diffArray[i-1];
        }

        StringBuilder sb = new StringBuilder();

        // STEP 3 - Applying the shifts
        for(int i = 0; i<n; i++){
            int shift = diffArray[i] % 26; // for wrapping around

            if(shift < 0){
                shift += 26; // adding 26 to remove -ve value
            }

            // after adding shift, it may get out of bound, so taking modulo
            // to wrap around
            char ch = (char) (((s.charAt(i) - 'a') + shift) % 26 + 'a');

            sb.append(ch);
        }

        return sb.toString();
    }
}