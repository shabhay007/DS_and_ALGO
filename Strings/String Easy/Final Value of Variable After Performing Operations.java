// LeetCode - 2011



// Approach 1 - Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int result = 0;

        for(String str : operations){
            if(str.equals("++X") || str.equals("X++")){
                result++;
            }
            else{
                result--;
            }
        }

        return result;
    }
}