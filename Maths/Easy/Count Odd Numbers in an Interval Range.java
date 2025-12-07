// LeetCode - 1523



// Approach 1 - Brute force
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int countOdds(int low, int high) {
        int count = 0;

        for(int i = low; i <= high; i++){
            if(i % 2 != 0){
                count++;
            }
        }

        return count;
    }
}





// Approach 2 - Maths
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public int countOdds(int low, int high) {
        // System.out.println(low/2);
        // System.out.println(high/2);
        // System.out.println((high+1)/2);
        return ((high+1)/2) - (low/2);
    }
}