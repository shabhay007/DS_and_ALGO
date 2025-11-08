// LeetCode - 1611



// Approach 1 - Bit Manipulation
// T.C. - O(32)
// S.C. - O(32)


/*
    // it is for understanding
    // now converting decimal number to binary string
    String binaryString = Integer.toBinaryString(n);


    now will traverse in the binary str and look for set bits
    as ans depends on set bits

    and traversal will be from left to right but the indexing will be
    like n-1 to 0

    eg. str ->   111001
        index -> 543210
    

    eg. 10000 if we need to convert this to 0 then we need (i-1)th index
        to be set
        so 10000 -> 11000
        and again 1000 -> 1100
        ... 100 -> 110
        ... like this
*/


class Solution {
    public int minimumOneBitOperations(int n) {
        // in a number there are 32 bits
        int[] f = new int[32];
        f[0] = 1;

        for(int i = 1; i<32; i++){
            f[i] = 2 * f[i-1] + 1;
        }

        int minOps = 0;
        int sign = 1; // 1 -> add, -1 -> subtract

        for(int i = 31; i>=0; i--){
            // checking if the ith bit is set or not
            // 01001 -> 3rd 0th bits are set -> f[3] - f[0]
            int ithBit = ((1 << i) & n);

            if(ithBit == 0){
                continue;
            }

            if(sign > 0){
                minOps += f[i];
                
            }
            else{
                minOps -= f[i];
            }

            // toggling the sign
            sign *= -1;
        }

        return minOps;
    }
}