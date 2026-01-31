// LeetCode - 744



// Approach 1 - Binary Search
// T.C. - O(log(n))
// S.C. - O(1)
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int i = 0;
        int j = n-1;
        int result = -1;

        while(i <= j){
            int mid = i + (j - i)/2;

            if(letters[mid] > target){
                result = mid;
                j = mid - 1;
            }
            else{
                i = mid + 1;
            }
        }

        return result == -1 ? letters[0] : letters[result];
    }
}