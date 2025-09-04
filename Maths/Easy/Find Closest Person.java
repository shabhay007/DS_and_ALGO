// LeetCode - 3516



// Approach 1
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public int findClosest(int x, int y, int z) {
        int d1 = Math.abs(x - z);
        int d2 = Math.abs(y - z);

        if(d1 < d2){
            return 1;
        }
        else if(d2 < d1){
            return 2;
        }
        else{
            return 0;
        }
    }
}