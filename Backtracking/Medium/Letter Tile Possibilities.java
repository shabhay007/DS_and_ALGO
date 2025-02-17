// LeetCode Medium - 1079


// Approach 1 - Recursion + Backtracking
// T.C. - O(n * n!); n! as we are finding every permutations for every possible subset lengths
// S.C. - O(n + n + n + n!); rec. stack, visited, StringBuilder(worst case), Set
class Solution {
    public void getPermutations(String tiles, StringBuilder sb, Set<String> result, boolean[] visited){
        if(sb.length() == tiles.length()){
            return;
        }

        for(int i = 0; i<tiles.length(); i++){ // O(n)
            if(!visited[i]){
                visited[i] = true;
                sb.append(tiles.charAt(i));
                result.add(sb.toString());
                getPermutations(tiles, sb, result, visited);
                
                // backtracking
                sb.deleteCharAt(sb.length() - 1);
                visited[i] = false;
            }
        }
    }

    public int numTilePossibilities(String tiles) {
        int n = tiles.length();

        // visited to track if the character of specific position is used or not
        boolean[] visited = new boolean[n];

        // set to remove duplicate subsets and permutations
        // O(n!) space, it will take in worst case as it can store all pemutations
        Set<String> result = new HashSet<>();

        // finding the all subsets and permutations
        getPermutations(tiles, new StringBuilder(), result, visited);

        return result.size();
    }
}





// Approach 2 - Recursion + Backtracking
// T.C. - O(2^n * n!); n! as we are finding every permutations for every possible subset lengths
// S.C. - O(26 + n); recursion stack, freq array
class Solution {
    public int getPermutations(int[] freq){
        int count = 0;

        for(int i = 0; i < 26; i++){ // O(26)
            if(freq[i] == 0){
                continue;
            }

            count++;

            // do
            freq[i]--;

            // explore
            count += getPermutations(freq);
            
            // backtracking
            freq[i]++;
        }

        return count;
    }

    public int numTilePossibilities(String tiles) {
        int n = tiles.length();

        int[] freq = new int[26];

        for(int i = 0; i<n; i++){
            char ch = tiles.charAt(i);

            freq[ch-'A']++;
        }

        // finding permutations of all possible subsets length
        return getPermutations(freq);
    }
}