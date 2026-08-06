// LeetCode - 3345



// Approach 1 - Maths
// T.C. - O(klog(d)); k = ans - n + 1
// S.C. - O(1)
class Solution {
    public int productOfDigits(int n){
        int product = 1;

        while(n > 0){
            product *= n % 10;
            n /= 10;
        }

        return product;
    }

    public int smallestNumber(int n, int t) {
        int product = productOfDigits(n);

        if(product % t == 0){
            return n;
        }

        while(product % t != 0){
            product = productOfDigits(n);

            if(product % t == 0){
                return n;
            }
            else{
                n++;
            }
        }

        return -1;
    }
}