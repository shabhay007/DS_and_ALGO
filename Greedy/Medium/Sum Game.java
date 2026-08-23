// LeetCode - 1927



// Approach 1 - Greedy
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftKnownSum = 0;
        int rightKnownSum = 0;
        int leftQnsMark = 0;
        int rightQnsMark = 0;

        for(int i = 0; i<n; i++){
            if(i < n/2){
                if(num.charAt(i) != '?'){
                    leftKnownSum += num.charAt(i) - '0';
                }
                else{
                    leftQnsMark++;
                }
            }
            else{
                if(num.charAt(i) != '?'){
                    rightKnownSum += num.charAt(i) - '0';
                }
                else{
                    rightQnsMark++;
                }
            }
        }

        // in case total ? mark count is odd, alice will always win
        if((leftQnsMark + rightQnsMark) % 2 != 0){
            return true;
        }

        int right = leftKnownSum + (int) ((4.5) * leftQnsMark);
        int left = rightKnownSum + (int) ((4.5) * rightQnsMark);

        return (left == right) ? false : true;
    }
}