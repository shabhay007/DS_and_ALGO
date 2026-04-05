// LeetCode - 657



// Approach 1 - Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean judgeCircle(String moves) {
        int n = moves.length();
        int right = 0;
        int left = 0;
        int up = 0;
        int down = 0;

        for(char ch : moves.toCharArray()){
            if(ch == 'R'){
                right++;
            }
            else if(ch == 'L'){
                left++;
            }
            else if(ch == 'U'){
                up++;
            }
            else{
                down++;
            }
        }

        return right == left && up == down;
    }
}