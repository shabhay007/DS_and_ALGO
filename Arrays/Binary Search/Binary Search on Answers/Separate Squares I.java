// LeetCode - 3453


/*
    So mathematically, the target is: bottomArea(midY) == totalArea / 2

    But algorithmically, binary search cannot work with equality on floating-point values.

    Why we use > / >= instead of == 1️ Floating-point reality (the practical reason)

    With double, this: bottomArea == totalArea / 2; is almost never true due to precision errors.

    So if you wait for equality:
    - binary search may never converge
    - or may oscillate forever
*/


// Approach 1 - Binary Search on Answers (V. Imp. Concept - See this qn. on Github for more explanation)
// T.C. - O(n * log(high - low))
// S.C. - O(1)
class Solution {
    public boolean check(double midY, int[][] arr, double totalArea){
        double bottomArea = 0.00000;

        for(int[] sq : arr){
            int bottomY = sq[1];
            int side = sq[2];

            int topY = bottomY + side;
            
            // case I : midY is not intersecting & sq is below it
            if(midY >= topY){
                bottomArea += (double) side * side;
            }

            // case II : midY is intersecting & we have to find are of rect.
            // one will be length = side
            // the other will be breadth = midY - y
            else if(midY > bottomY){
                bottomArea += (double) side * (midY - bottomY);
            }

        }

        // V. Imp. Concept - See this qn. on Github for more explanation
        return bottomArea >= totalArea/2.0;
    }

    public double separateSquares(int[][] squares) {
        int n = squares.length;
        double totalArea = 0.00000;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;

        for(int[] sq : squares){
            totalArea += (double) sq[2] * sq[2];
            minY = Math.min(minY, sq[1]);
            maxY = Math.max(maxY, sq[1] + sq[2]);
        }

        double l = minY;
        double r = maxY;
        double result = l;

        while(r - l > 1e-5){
            double midY = l + (r - l)/2.0;
            
            // +1 or -1 will change value by large margin as we are using 
            // double precision, so will directly assign mid value
            // and because of this, at loop level w'll use precision also
            // instead of check l <= r as shown above
            if(check(midY, squares, totalArea)){
                result = midY;
                // result = midY;
                // r = midY - 1; 
                r = midY; 
            }
            else{
                // l = midY + 1;
                l = midY;
            }
        }

        return result;
    }
}