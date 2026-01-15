// LeetCode - 2943



// Approach 1 - Sorting
// T.C. - O(nlogn + mlogm)
// S.C. - O(1)
class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);

        int maxHeight = 1;
        int height = 1;

        for(int i = 1; i<hBars.length; i++){
            if(hBars[i] == hBars[i-1] + 1){
                height++;
            }
            else{
                // maxHeight = Math.max(maxHeight, height);
                height = 1;
            }

            // instead of else block, we can use it here, by this we can avoid this
            // line after the loop ends for the last one
            maxHeight = Math.max(maxHeight, height);
        }
        
        int maxWidth = 1;
        int width = 1;

        for(int i = 1; i<vBars.length; i++){
            if(vBars[i] == vBars[i-1] + 1){
                width++;
            }
            else{
                width = 1;
            }

            maxWidth = Math.max(maxWidth, width);
        }


        // already took 1 as if we remove 1 bar, we'll get height of 2, 
        // 2 bars -> 3 ht.
        // similarly for width, so incrementing 1 in side directly
        int side = Math.min(maxHeight, maxWidth) + 1;

        return side * side;
    }
}