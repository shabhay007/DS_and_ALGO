// LeetCode - 868



// Approach 1 - Bit Manipulation
// T.C. - O(str.length)
// S.C. - O(1)
class Solution {
    public int binaryGap(int n) {
        String str = Integer.toBinaryString(n);
        int maxD = 0;
        int prev = -1;

        for(int i = 0; i<str.length(); i++){
            char ch = str.charAt(i);

            if(ch == '1'){
                if(prev == -1){
                    prev = i;
                }
                else{
                    maxD = Math.max(maxD, i - prev);
                    prev = i;
                }
            }
        }

        return maxD;
    }
}





// Approach 2 - Bit Manipulation
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public int binaryGap(int n) {
        int maxD = 0;
        int prev = -1;

        for(int i = 0; i<32; i++){
            if(((n >> i) & 1) != 0){
                maxD = (prev != -1) ? Math.max(maxD, i - prev) : maxD;
                prev = i;
            }
        }

        return maxD;
    }
}