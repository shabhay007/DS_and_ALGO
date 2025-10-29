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





// Approach 2 - Bit Manipulation
// T.C. - O(log(n))
// S.C. - O(log(n))
class Solution {
    public int smallestNumber(int n) {
        String binaryStr = Integer.toBinaryString(n); // O(log(n))
        int len = binaryStr.length();

        return (int) (Math.pow(2, len)) - 1;
    }
}






// Approach 3 - Bit Manipulation (Minor change in Approach 2)
// T.C. - O(log(n))
// S.C. - O(log(n))
class Solution {
    public int smallestNumber(int n) {
        String binaryStr = Integer.toBinaryString(n); // O(log(n))
        int len = binaryStr.length();

        return (1 << len) - 1;
    }
}






// Approach 4 - Optimised Bit Manipulation
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public int smallestNumber(int n) {
        int len = (int) (Math.log(n) / Math.log(2)) + 1; // binary length

        return (1 << len) - 1;
    }
}