// LeetCode - 1111



// Approach 1 - Observation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int depth = 0;
        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            char ch = seq.charAt(i);

            if(ch == '('){
                depth++;

                // even will go in group 0 and odd will go in group 1
                result[i] = (depth % 2 == 0) ? 0 : 1;
            }
            else{
                result[i] = (depth % 2 == 0) ? 0 : 1;
                depth--;
            }
        }

        return result;
    }
}