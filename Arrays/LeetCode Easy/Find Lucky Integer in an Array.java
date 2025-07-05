// LeetCode - 1394



// Approach 1 - Using frequency array
// T.C. - O(2n)
// S.C. - O(500) ~ O(1)
class Solution {
    public int findLucky(int[] arr) {
        int n = arr.length;
        int[] result = new int[501];

        for(int i = 0; i<n; i++){
            result[arr[i]]++;
        }

        // finding the lucky number
        for(int i = 500; i>0; i--){
            if(i == result[i]){
                return i;
            }
        }

        return -1;
    }
}





// Approach 2 - Advanced Bit Manipulation (codestorywithMIK)
// T.C. - O(2n)
// S.C. - O(1)

/*
    For Learning point of view and not for Interview
    
    00000000 00000000 00000000 00000000

    In this 32 bits, we'll use first 16 bits to store the
    number and the other 16 bits to store frequency
*/

class Solution {
    public int findLucky(int[] arr) {
        int n = arr.length;
        
        for(int element : arr){
            int val = (element & 65535); // 2^16 or 65535

            if(val <= n){
                arr[val - 1] += (1 << 16); // 2^16 or 65535
            }
        }

        // now finding the lucky number
        for(int val = n; val > 0; val--){
            if((arr[val - 1] >> 16) == val){
                return val;
            }
        }

        return -1;
    }
}