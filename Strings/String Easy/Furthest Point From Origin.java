// LeetCode - 2833



// Approach 1 - Taking '_' as char with most frequency
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int n = moves.length();
        int l = 0;
        int r = 0;

        for(int i = 0; i<n; i++){
            if(moves.charAt(i) == 'L'){
                l++;
            }
            else if(moves.charAt(i) == 'R'){
                r++;
            }
        }

        int dist = 0;
        if(l >= r){
            for(int i = 0; i<n; i++){
                if(moves.charAt(i) == 'L' || moves.charAt(i) == '_'){
                    dist++;
                }
                else{
                    dist--;
                }
            }
        }

        int distWithR = 0;
        if(l < r){
            for(int i = 0; i<n; i++){
                if(moves.charAt(i) == 'R' || moves.charAt(i) == '_'){
                    distWithR++;
                }
                else{
                    distWithR--;
                }
            }
        }

        return Math.max(dist, distWithR);
    }
}






// Approach 2 - Taking '_' as char with most frequency
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int n = moves.length();
        int l = 0;
        int r = 0;

        for(int i = 0; i<n; i++){
            if(moves.charAt(i) == 'L'){
                l++;
            }
            else if(moves.charAt(i) == 'R'){
                r++;
            }
        }

        int underscore = n - l - r;

        return (l >= r) ? l - r + underscore : r - l + underscore;
    }
}