// LeetCode Medium - 2523


// Approach 1 - Sieve Algorithm
// T.C. - O(R log(logR) + R-L)
// S.C. - O(right)
class Solution {
    public int[] closestPrimes(int left, int right) {
        // Sieve Algorithm to find prime numbers between [1, right]
        boolean[] prime = new boolean[right + 1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;

        // Sieve - O(R log(logR))
        for(int p = 2; p*p <= right; p++){
            if(prime[p]){
                for(int i = p*p; i<=right; i += p){
                    prime[i] = false;
                }
            }
        }

        // left to right
        // finding minDiff bet pair of prime numbers
        int[] res = new int[]{-1, -1};
        int minDiff = Integer.MAX_VALUE;
        int prev = -1;

        for(int i = left; i <= right; i++){
            if(prime[i]){
                if(prev == -1){
                    prev = i;
                }
                else {
                    if(i - prev < minDiff){
                        res[0] = prev;
                        res[1] = i;
                        minDiff = i - prev;
                    }

                    prev = i;
                }
            }
        }

        return res;
    }
}