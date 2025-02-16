// LeetCode Medium - 1718


// Backtracking
// T.C. - O(n!)
// S.C. - O(n + n); recursion stack + visited
class Solution {
    public boolean solve(int i, int n, int[] result, int[] visited, int len){
        if(i >= len){
            return true;
        }

        if(result[i] != -1){
            return solve(i+1, n, result, visited, len);
        }

        for(int num = n; num >= 1; num--){
            if(visited[num] != -1){
                continue;
            }

            int j = num + i; // next place for the same integer occuring twice

            // try
            visited[num] = 1;
            result[i] = num;


            // explore
            if(num == 1){
                if(solve(i+1, n, result, visited, len)){
                    return true;
                }
            }
            else{
                if(j < len && result[j] == -1){
                    result[j] = num;

                    // try
                    if(solve(i+1, n, result, visited, len)){
                        return true;
                    }
                    
                    result[j] = -1;
                }
            }

            // backtrack
            visited[num] = -1;
            result[i] = -1;
        }

        return false;
    }

    public int[] constructDistancedSequence(int n) {
        int len = n*2 - 1;
        int[] result = new int[len];
        Arrays.fill(result, -1);

        int[] visited = new int[n+1];
        Arrays.fill(visited, -1); // n

        // result[] = {_, _, _, _, _, _}
        // for every place we have n options.
        // so t.c. - n * n-1, * n-2, ....
        // therefore, Total T.C. - (n!)
        solve(0, n, result, visited, len);

        return result;
    }
}






// Approach 2 - Backtracking (Slight change in Approach 1)
// T.C. - O(n!)
// S.C. - O(n + n)
class Solution {
    public boolean getValidSequence(int i, int[] result, boolean[] used, int n){
        if(i >= result.length){
            return true;
        }

        if(result[i] != 0){
            return getValidSequence(i+1, result, used, n);
        }

        for(int num = n; num >= 1; num--){
            if(used[num]){
                continue;
            }        

            if(num == 1){ // only one insertion
                if(!used[num]){
                    used[num] = true; // if we take these common parts out, then it get converted
                    result[i] = num; // back to approach 1

                    // checking for next index
                    if(getValidSequence(i+1, result, used, n)){
                        return true;
                    }

                    // if we cannot find answer
                    // BACKTRACK
                    result[i] = 0;
                    used[num] = false;
                }
            }
            // result[i] == 0, this check is not needed here as are here after checking this
            else if(i+num < result.length && result[i+num] == 0){ // two insertions
                used[num] = true;
                result[i] = num;
                result[i+num] = num;

                // checking for next index
                if(getValidSequence(i+1, result, used, n)){
                    return true;
                }

                // if we cannot find answer
                // BACKTRACK
                result[i] = 0;
                result[i+num] = 0; // if we take these common parts out, then it get converted
                used[num] = false; // back to approach 1
            }
        }

        return false;
    }

    public int[] constructDistancedSequence(int n) {
        int len = n*2 - 1;
        boolean used[] = new boolean[n+1];

        int result[] = new int[len];

        getValidSequence(0, result, used, n);

        return result;
    }
}