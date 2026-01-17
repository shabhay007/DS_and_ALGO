// LeetCode - 3047



// Approach 1 - Brute Force (Maths + Geometry)
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long maxArea = 0;

        for(int i = 0; i<n; i++){
            // for finding overlapping area
            long startX = bottomLeft[i][0];
            long startY = bottomLeft[i][1];
            long endX = topRight[i][0];
            long endY = topRight[i][1];

            for(int j = i+1; j<n; j++){
                long nextStartX = bottomLeft[j][0];
                long nextStartY = bottomLeft[j][1];
                long nextEndX = topRight[j][0];
                long nextEndY = topRight[j][1];

                long overlapX1 = Math.max(startX, nextStartX);
                long overlapY1 = Math.max(startY, nextStartY);
                long overlapX2 = Math.min(endX, nextEndX);
                long overlapY2 = Math.min(endY, nextEndY);

                if (overlapX1 < overlapX2 && overlapY1 < overlapY2) {
                    long side = Math.min(overlapX2 - overlapX1, overlapY2 - overlapY1);
                    maxArea = Math.max(maxArea, side * side);
                }
            }
        }

        return maxArea;
    }
}





// Approach 2 - More Clean & Readable Brute Force (Maths + Geometry)
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long maxSide = 0;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                long topRightX = Math.min(topRight[i][0], topRight[j][0]);
                long bottomLeftX = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                long width = topRightX - bottomLeftX;

                long topRightY = Math.min(topRight[i][1], topRight[j][1]);
                long bottomLeftY = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                long height = topRightY - bottomLeftY;

                long side = Math.min(width, height);

                maxSide = Math.max(maxSide, side);
            }
        }

        return maxSide * maxSide;
    }
}