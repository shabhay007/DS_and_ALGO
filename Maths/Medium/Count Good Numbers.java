// LeetCode Medium - 1922


// Approach 1 (Brute Force) - Gives TLE (It will only process if n is super small (n <= 6))
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public boolean isValid(String str){
        Set<Integer> set = new HashSet<>();
        set.add(2);
        set.add(3);
        set.add(5);
        set.add(7);

        for(int i = 0; i<str.length(); i++){
            int digit = str.charAt(i) - '0';

            if(i % 2 == 0){
                if(digit % 2 != 0){
                    return false;
                }
            }
            else{
                if(!set.contains(digit)){
                    return false;
                }
            }
        }

        return true;
    }

    public int countGoodNumbers(long n) {
        long mod = (long) 1e9 + 7;
        // long start = (long) Math.pow(10, n-1);
        // long end = (long) Math.pow(10, n) - 1;

        long count = 0;

        for(long i = 0; i < Math.pow(10, n); i++){
            String str = String.format("%0" + n + "d", i); // leading zero padding

            if(isValid(str)){
                count = (count + 1) % mod;
            }
        }

        return (int) count;
    }
}





// Approach 2 (Optimal) - Binary Exponentiation (Exponentiation by Squaring OR Fast Exponentiation)
// T.C. - O(log(n) * log(n)); log(base 2) n
// S.C. - O(log(base 2) n)
class Solution {
    public long findPower(long a, long b){ // O(log(b))
        int M = (int) 1e9 + 7;

        if(b == 0){
            return 1;
        }

        long half = findPower(a, b/2);
        long result = (half * half) % M;

        if(b % 2 == 1){
            result = (result * a) % M;
        }

        return result;
    }

    public int countGoodNumbers(long n) {
        int M = (int) 1e9 + 7;
        long result = findPower(5, (n+1)/2) * findPower(4, n/2) % M;

        return (int) result;
    }
}