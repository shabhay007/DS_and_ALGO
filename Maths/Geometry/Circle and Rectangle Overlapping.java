// LeetCode Medium - 1401



// Approach 1 - Maths
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // nearest point
        int xi = -1;
        int yi = -1;

        if(x1 > xCenter) xi = x1;
        else if(x2 < xCenter) xi = x2;
        else xi = xCenter;

        if(y1 > yCenter) yi = y1;
        else if(y2 < yCenter) yi = y2;
        else yi = yCenter;

        int x = (xCenter - xi) * (xCenter - xi);
        int y = (yCenter - yi) * (yCenter - yi);
        int d = (int) Math.sqrt(x + y);

        return d <= radius;
    }
}