// LeetCode Medium - 2429


// Appraoch 1 - Bit Manipulation
// T.C. - O(log(n) + 32 + 32)
// S.C. - O(1)
class Solution {
    public int minimizeXor(int num1, int num2) {
        int setBits = Integer.bitCount(num2); // O(log(num2))
        int bits = 31; // as there are 32 bits (1....32) so, 0....31
        int result = 0;

        while(bits >= 0 && setBits > 0){ // O(32)
            // check if bit is set in num1 and if so, set the bit in result
            if((num1 & (1 << bits)) != 0){
                result = result | (1 << bits);
                setBits--;
            }

            bits--;
        }

        bits = 0;
        while(setBits > 0 && bits <= 31){ // O(32)
            if((num1 & (1 << bits)) == 0){
                result |= (1 << bits);
                setBits--;
            }

            bits++;
        }

        return result;
    }
}