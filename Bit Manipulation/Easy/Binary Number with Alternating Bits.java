// LeetCode - 693



// Approach 1 - Bit Manipulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean hasAlternatingBits(int n) {
        String binaryStr = Integer.toBinaryString(n);

        for(int i = 0; i<binaryStr.length()-1; i++){
            if(binaryStr.charAt(i) == binaryStr.charAt(i+1)){
                return false;
            }
        }

        return true;
    }
}