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






// Approach 2 - Maths
// T.C. - O(k)
// S.C. - O(1)
class Solution {
    public int smallestRepunitDivByK(int k) {
        if(k == 1){
            return 1;
        }

        int num = 0;

        // length will be from 1 to k-1
        for(int length = 1; length <= k; length++){
            // remainder will never exceed k-1
            // and to avoid overflow, using modulo
            num = (num * 10 + 1) % k;

            if(num == 0){
                return length;
            }
        }

        return -1;
    }
}