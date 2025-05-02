// LeetCode Medium - 838



// Approach 1
// T.C. - O(3n)
// S.C. - O(3n)
class Solution {
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        char[] arr = dominoes.toCharArray();

        int[] closestRToLeft = new int[n];
        int[] closestLtoRight = new int[n];


        // finding closest R for left by moving from left to right
        for(int i = 0; i<n; i++){
            if(arr[i] == 'R'){
                closestRToLeft[i] = i; // push towards right starts from here
            }
            else if(arr[i] == '.'){
                closestRToLeft[i] = (i > 0) ? closestRToLeft[i-1] : -1;
            }
            else{
                closestRToLeft[i] = -1; // push towards left
            }
        }

        // finding closest L in for right by moving from right to left
        for(int i = n-1; i >= 0; i--){
            if(arr[i] == 'L'){
                closestLtoRight[i] = i; // left push starts from here
            }
            else if(arr[i] == '.'){
                closestLtoRight[i] = (i+1 < n) ? closestLtoRight[i+1] : -1;
            }
            else{
                closestLtoRight[i] = -1; // right push
            }
        }

        char[] result = new char[n];
        for(int i = 0; i<n; i++){
            if (closestRToLeft[i] == -1 && closestLtoRight[i] == -1) {
                result[i] = '.';
            } 
            else if (closestRToLeft[i] == -1) {
                result[i] = 'L';
            } 
            else if (closestLtoRight[i] == -1) {
                result[i] = 'R';
            } 
            else {
                int distLeftToRight = Math.abs(i - closestRToLeft[i]);
                int distRightToLeft = Math.abs(i - closestLtoRight[i]);

                if (distLeftToRight == distRightToLeft) {
                    result[i] = '.';
                } 
                else if (distLeftToRight < distRightToLeft) {
                    result[i] = 'R';
                } 
                else {
                    result[i] = 'L';
                }
            }
        }

        return new String(result);
    }
}