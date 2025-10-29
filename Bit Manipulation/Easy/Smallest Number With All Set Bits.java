// LeetCode - 3370



// Approach 1 - Using String Manipulation
// T.C. - O(log(n)); n = no of digits in binary form of n
// S.C. - O(log(n))
class Solution {
    public int smallestNumber(int n) {
        String binaryStr = Integer.toBinaryString(n); // O(log(n))
        int len = binaryStr.length();

        int bitCount = Integer.bitCount(n); // O(1)

        if(bitCount == len){
            return n;
        }

        char[] chArr = binaryStr.toCharArray();

        for(int i = len-1; i >= 0; i--){
            if(chArr[i] == '0'){
                chArr[i] = '1';
            }
        }

        String newStr = new String(chArr);

        return Integer.parseInt(newStr, 2);
    }
}