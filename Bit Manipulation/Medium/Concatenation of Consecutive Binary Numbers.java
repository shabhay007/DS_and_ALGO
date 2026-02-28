// LeetCode - 1680



// Approach 1 - Brute Force (Bit Manipulation + Simulation)
// T.C. - O(nlog(n))
// S.C. - O(nlog(n))
class Solution {
    public int concatenatedBinary(int n) {
        int mod = (int) 1e9 + 7;
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++){
            sb.append(Integer.toBinaryString(i)); // O(logn)
        }

        long result = 0;
        for(int i = 0; i < sb.length(); i++){
            result = ((result << 1) + sb.charAt(i) - '0') % mod;
        }

        return (int) result % mod;
    }
}





// Approach 2 - Brute Force (Bit Manipulation + Simulation)
// T.C. - O(nlog(n))
// S.C. - O(log(n))
class Solution {
    public int concatenatedBinary(int n) {
        int mod = (int) 1e9 + 7;
        
        long result = 0;
        for(int i = 1; i <= n; i++){
            String str = Integer.toBinaryString(i);
            
            for(char ch : str.toCharArray()){
                result = ((result << 1) + ch - '0') % mod;
            }
        }

        return (int) result % mod;
    }
}






// Approach 3 - (Bit Manipulation)
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int concatenatedBinary(int n) {
        int mod = (int) 1e9 + 7;
        
        long result = 0;
        for(int i = 1; i <= n; i++){
            // gives total bits in binary form of decimal number
            // int bits = (int) (Math.log(i)/Math.log(2)) + 1;
            int bits = 32 - Integer.numberOfLeadingZeros(i);

            result = ((result << bits) + i) % mod;
        }

        return (int) result % mod;
    }
}






// Approach 4 - (Bit Manipulation)
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int concatenatedBinary(int n) {
        int mod = (int) 1e9 + 7;
        
        long result = 0;
        int bits = 0;

        /*
            1 - 1 bits (power of 2 -> 2^0)
            2 - 2 bits (power of 2 -> 2^1)
            3 - 2 bits (not power of 2)
            4 - 3 bits (power of 2 -> 2^2)
            5 - 3 bits (not power of 2)
            6 - 3 bits (not power of 2)
            7 - 3 bits (not power of 2)
            8 - 4 bits (power of 2 -> 2^3)
            .
            .
            .

            It clearly shows when power of 2 changes, no. of bits increases
        */

        for(int i = 1; i <= n; i++){
            // power of 2
            if((i & (i-1)) == 0){
                bits++;
            }

            result = ((result << bits) + i) % mod;
        }

        return (int) result % mod;
    }
}