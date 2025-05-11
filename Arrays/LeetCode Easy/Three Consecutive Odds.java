// LeetCode - 1550


// Approach 1
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int n = arr.length;

        for(int i = 0; i<n-2; i++){
            if(arr[i] % 2 != 0 && arr[i+1] % 2 != 0 && arr[i+2] % 2 != 0){
                return true;
            }
        }

        return false;
    }
}




// Approach 2 - Slight change in Approach 1
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int m = arr.length;

        for(int i = 0; i<m; i++){
            if(arr[i] % 2 != 0 && (i+1 < m && arr[i+1] % 2 != 0) && (i+2 < m && arr[i+2] % 2 != 0)){
                return true;
            }
        }

        return false;
    }
}