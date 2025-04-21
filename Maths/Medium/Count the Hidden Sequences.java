// LeetCode Medium - 2145


// Approach 1 - Maths
// T.C. - O(n)
// S.C. - O(1)

/* 
    if we found the first hidden sequence; say a[0]
    then we can find the whole sequence by adding the diff. 
    
    Let's say x is 1st element (which is smallest) & y is last (max)
    then lower <= x <= upper

    now, if we add/sub any num (alpha), still the diff will be same
    now we can say x >= lower - alpha & x <= upper - alpha because 
    lower - alpha >= lower & upper - alpha <= upper

    and [a, b] gives b-a+1 numbers
    therefore, [lower-alpha, upper-alpha] gives upper-alpha - (lower-alpha) + 1 nums
*/

class Solution {
    public int numberOfArrays(int[] diff, int lower, int upper) {
        int curr = 0; // let 0 be the first element of the hidden sequence
        int minVal = 0;
        int maxVal = 0;

        for(int d : diff){
            curr += d;

            minVal = Math.min(minVal, curr);
            maxVal = Math.max(maxVal, curr);

            if((upper - maxVal) - (lower - minVal) + 1 <= 0){
                return 0;
            }
        }

        return (upper - maxVal) - (lower - minVal) + 1;
    }
}