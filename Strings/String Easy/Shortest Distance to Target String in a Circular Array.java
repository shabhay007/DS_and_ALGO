// LeetCode - 2515



// Approach 1
// T.C. - O(n * L); L = avg. length of words
// S.C. - O(1)
class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDist = Integer.MAX_VALUE;

        for(int i = 0; i<n; i++){
            if(words[i].equals(target)){ // comparison -> O(L)
                int straightDist = Math.abs(i - startIndex);
                int circularDist = n - straightDist;

                minDist = Math.min(minDist, Math.min(straightDist, circularDist));
            }
        }

        return (minDist == Integer.MAX_VALUE) ? -1 : minDist;
    }
}