// LeetCode Medium - 2683


// My Approach - Optimal (xor properties)
// T.C. - O(n)
// S.C. - O(1)
// if we take example of [1, 1, 0], and org = [a, b, c]
// then derived = [a^b, b^c, c^a]
// you can see that every element of org comes 2 times in derived,
// so, if we take xor of derived then it should result true to be valid array existence
class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        int xor = 0;

        for(int num : derived){
            xor ^= num;
        }

        return xor == 0;
    }
}




// Better - Using xor properties
// T.C. - O(2n)
// S.C. - O(n)
// since, a ^ b = c; and xor is associative, commutative etc.
// therefore, a ^ c = b  ||  b ^ c = a  etc.
class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        int n = derived.length;
        int[] original = new int[n];

        // take original[0] = 0;
        original[0] = 0;

        for(int i = 0; i<n-1; i++){
            original[i+1] = original[i] ^ derived[i];
        }

        if((original[0] ^ original[n-1]) == derived[n-1]){
            return true;
        }

        // if above is false, now check by taking original[0] = 1;
        original[0] = 1;

        for(int i = 0; i<n-1; i++){
            original[i+1] = original[i] ^ derived[i];
        }

        if((original[0] ^ original[n-1]) == derived[n-1]){
            return true;
        }

        return false;
    }
}