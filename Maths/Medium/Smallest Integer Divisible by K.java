// LeetCode - 1015



// Approach 1 - Maths
// T.C. - O(k)
// S.C. - O(1)
class Solution {
    public int smallestRepunitDivByK(int k) {
        // If k is multiple of 2 or 5, impossible
        if (k % 2 == 0 || k % 5 == 0){
            return -1;
        }

        
        int length = 1;

        // 1st number should also be stored as remainder, else it will show
        // wrong behaviour
        int n = 1 % k;

        while(n != 0){
            n = (n*10 + 1) % k;
            length++;
        }

        return length;
    }
}