// LeetCode - 1009



// Approach 1 - Brute Force (Converting all bits using string manipulation)
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int bitwiseComplement(int n) {
        String str = Integer.toBinaryString(n);
        StringBuilder sb = new StringBuilder(str);

        for(int i = 0; i<sb.length(); i++){
            char ch = sb.charAt(i);

            if(ch == '0'){
                sb.setCharAt(i, '1');
            }
            else{
                sb.setCharAt(i, '0');
            }
        }

        // System.out.println(sb.toString());

        return Integer.parseInt(sb.toString(), 2);
    }
}






// Approach 2 - Simulation
// T.C. - O(log2(n))
// S.C. - O(1)
class Solution {
    public int bitwiseComplement(int n) {
        if(n == 0){
            return 1;
        }

        int counter = 0;
        int result = 0;

        while(n > 0){
            int r = n % 2;

            // 1-r = to flip the bit
            result += (1 << counter) * (1 - r);
            n /= 2; // it can also be written as n >> 1
            counter++;
        }

        return result;
    }
}






// Approach 3 - Bit Manipulation
// T.C. - O(log2(n))
// S.C. - O(1)
class Solution {
    public int bitwiseComplement(int n) {
        if(n == 0){
            return 1;
        }

        int mask = 1;

        while(mask < n){
            mask = (mask << 1) | 1; // builds mask like 1111.....1
        }

        // now doing xor with n to flip all the bits
        return n ^ mask;
    }
}







// Approach 4 - Bit Manipulation
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public int bitwiseComplement(int n) {
        int len = (int) Math.floor(Math.log(n)/Math.log(2)) + 1; // to find no. of bits
        int mask = (1 << len) - 1; // 2^bits - 1 = 111....1

        return n ^ mask;
    }
}