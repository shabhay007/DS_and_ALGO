// LeetCode - 3536



// Approach 1 - Maths
// T.C. - O(log(d))
// T.C. - O(1)
class Solution {
    public int maxProduct(int n) {
        int[] digits = new int[10];

        while(n > 0){
            int d = n % 10;
            digits[d]++;

            n /= 10;
        }

        int first = 0;
        int second = 0;

        for(int i = 9; i >= 0; i--){
            if(digits[i] > 0 && i > first){
                first = i;
                digits[i]--;
            }

            if(digits[i] > 0){
                second = Math.max(second, i);
            }
        }

        return first * second;
    }
}






// Approach 2 - String Conversion
// T.C. - O(d * log(d))
// T.C. - O(d)
class Solution {
    public int maxProduct(int n) {
        String str = String.valueOf(n);
        char[] ch = str.toCharArray();
        Arrays.sort(ch);

        return (ch[ch.length - 1] - '0') * (ch[ch.length - 2] - '0');
    }
}