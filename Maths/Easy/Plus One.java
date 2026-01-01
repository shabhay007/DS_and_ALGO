// LeetCode - 66



// Approach 1 - Maths
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int carry = 0;

        for(int i = n-1; i>=0; i--){
            if(digits[i] < 9){
                digits[i] += 1;
                carry = 0;
                break;
            }
            else{
                digits[i] = 0;
                carry = 1;
            }
        }

        // when all the digits are 9 then after processing the digits array
        // 1 will be carried out
        if(carry == 1){
            int[] result = new int[n+1];
            result[0] = 1;
            return result;
        }

        return digits;
    }
}