// LeetCode - 1545



// Approach 1 - Brute Force
// T.C. - O(2^n)
// S.C. - O(2^n)
class Solution {
    public char findKthBit(int n, int k) {
        // string grows at (2^n - 1)
        StringBuilder sb = new StringBuilder();
        sb.append('0');
        n -= 1;

        while(n > 0){
            StringBuilder invert = new StringBuilder();

            for(int i = 0; i<sb.length(); i++){
                if(sb.charAt(i) == '0'){
                    invert.append('1');
                }
                else{
                    invert.append('0');
                }
            }

            // System.out.println("invert : " + invert.toString());
            sb.append('1').append(invert.reverse());
            // System.out.println("string : " + sb.toString());
            
            if(sb.length() >= k){
                return sb.charAt(k-1);
            }

            n--;
        }

        return sb.charAt(k-1);
    }
}






// Approach 2 - Recursion
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public char findKthBit(int n, int k) {
        if(n == 1){
            return '0';
        }

        int length = (1 << n) - 1; // power of 2

        if(k < Math.ceil(length/2.0)){
            return findKthBit(n-1, k);
        }
        else if(k == Math.ceil(length/2.0)){
            return '1';
        }
        else{
            char ch = findKthBit(n-1, length-(k-1)); // handling reverse

            return (ch == '1') ? '0' : '1'; // handling flip
        }
    }
}