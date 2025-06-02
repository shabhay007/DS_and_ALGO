// LeetCode - 135



// Approach 1 - Greedy
// T.C. - O(3n)
// S.C. - O(2n)
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;

        // traversing through left to right and finding if the curr is 
        // greater than prev, increment else no increment in candies
        int[] leftToRight = new int[n];
        int candies = 1;

        for(int i = 0; i<n; i++){
            if(i == 0){
                leftToRight[i] = 1;
            }
            else if(ratings[i] > ratings[i-1]){
                leftToRight[i] = ++candies;
            }
            else{
                leftToRight[i] = 1;
                candies = 1;
            }
        }


        // traversing through right to left and finding if the curr is 
        // greater than next, increment else no increment in candies
        int[] rightToLeft = new int[n];
        candies = 1;

        for(int i = n-1; i>=0; i--){
            if(i == n-1){
                rightToLeft[i] = 1;
            }
            else if(ratings[i] > ratings[i+1]){
                rightToLeft[i] = ++candies;
            }
            else{
                rightToLeft[i] = 1;
                candies = 1;
            }
        }


        // now processing the result from both the arrays
        int result = 0;

        for(int i = 0; i<n; i++){
            result += Math.max(leftToRight[i], rightToLeft[i]);
        }

        return result;
    }
}






// Approach 2 - Greedy (taking less space)
// T.C. - O(3n)
// S.C. - O(n)
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;

        // traversing through left to right and finding if the curr is 
        // greater than prev, increment else no increment in candies
        int[] count = new int[n];
        int candies = 1;

        for(int i = 0; i<n; i++){
            if(i == 0){
                count[i] = 1;
            }
            else if(ratings[i] > ratings[i-1]){
                count[i] = ++candies;
            }
            else{
                count[i] = 1;
                candies = 1;
            }
        }


        // traversing through right to left and finding if the curr is 
        // greater than next, increment else no increment in candies
        candies = 1;

        for(int i = n-1; i>=0; i--){
            if(i == n-1){
                count[i] = Math.max(count[i], 1);
            }
            else if(ratings[i] > ratings[i+1]){
                count[i] = Math.max(count[i], ++candies);
            }
            else{
                count[i] = Math.max(count[i], 1);
                candies = 1;
            }
        }


        // now processing the result from both the arrays
        int result = 0;

        for(int i = 0; i<n; i++){
            result += count[i];
        }

        return result;
    }
}







// Approach 3 (most optimal) - Greedy
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int candies = n; // each children is given at least 1 candy

        int i = 1;

        while(i < n){
            if(ratings[i] == ratings[i-1]){
                i++;
                continue;
            }

            // increasing slope - peak
            int peak = 0;
            while(ratings[i] > ratings[i-1]){
                peak++;
                candies += peak;

                // update
                i++;
                if(i == n) return candies;
            }

            // decreasing slope - dip
            int dip = 0;
            while(i < n && ratings[i] < ratings[i-1]){
                dip++;
                candies += dip;
                i++;
            }

            candies -= Math.min(peak, dip);
        }

        return candies;
    }
}