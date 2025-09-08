// LeetCode - 1317



// Approach 1 - Maths
// T.C. - O(n.log(max(a, b)))
// S.C. - O(1)
class Solution {
    public boolean check(int a, int b){
        while(a > 0){
            int digit = a % 10;
            a /= 10;

            if(digit == 0){
                return false;
            }
        }

        while(b > 0){
            int digit = b % 10;
            b /= 10;

            if(digit == 0){
                return false;
            }
        }

        return true;
    }

    public int[] getNoZeroIntegers(int n) {
        int a = n-1;
        int b = 1;

        while(true){
            if(check(a, b)){
                return new int[]{a, b};
            }

            a--;
            b++;
        }
    }
}