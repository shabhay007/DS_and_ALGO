// LeetCode - 67



// Approach 1 - Binary Conversion + Bit Manipulation (will throw Number Format Exception for large inputs)
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public String addBinary(String a, String b) {
        int numA = Integer.parseInt(a, 2);
        int numB = Integer.parseInt(b, 2);

        return Integer.toBinaryString(numA + numB);
    }
}





// Approach 2 - Binary Conversion + Bit Manipulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;

        while(i >= 0 || j >= 0 || carry == 1){
            int sum = carry;

            if(i >= 0){
                sum += a.charAt(i) - '0';
            }

            if(j >= 0){
                sum += b.charAt(j) - '0';
            }

            sb.insert(0, sum % 2);
            carry = sum/2;
            i--;
            j--;
        }

        return sb.toString();
    }
}