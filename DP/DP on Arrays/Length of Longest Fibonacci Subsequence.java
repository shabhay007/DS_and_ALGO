// LeetCode Medium - 873


// Approach 1 - Using Set
// T.C. - O(n^3)
// S.C. - O(n)
class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        HashSet<Integer> set = new HashSet<>();

        for(int num : arr){
            set.add(num);
        }

        int maxCount = 0;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                long prev = arr[i];
                long curr = arr[j];
                int length = 2;

                while(set.contains((int) (prev + curr))){ // O(n)
                    long temp = prev + curr;
                    prev = curr;
                    curr = temp;
                    length++;
                }

                maxCount = Math.max(maxCount, length);
            }
        }

        return maxCount > 2 ? maxCount : 0;
    }
}





// Approach 2 - Recursion
// T.C. - O(n^3)
// S.C. - O(n + n)
class Solution {
    public int solve(int i, int j, int[] arr, HashMap<Integer, Integer> map){
        int target = arr[j] - arr[i];

        if(map.containsKey(target) && map.get(target) < i){
            // is one element is found in left, so in order to find next element in
            // left, we'll send elements idx and ith idx and excluding the jth
            // idx which means we have 1 prev element which is part of the sequence
            // so added 1 and called the recursion to find next
            return 1 + solve(map.get(target), i, arr, map);
        }

        // in case of no element found in left, then we have 2 elements 
        // from which recursion call is initiated
        return 2;
    }

    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;

        // map<num, index>
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<n; i++){
            map.put(arr[i], i);
        }

        int maxLength = 0;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                int length = solve(i, j, arr, map);

                maxLength = Math.max(maxLength, length);
            }
        }

        return maxLength >= 3 ? maxLength : 0;
    }
}




// Approach 3 - Recursion + Memoization
// T.C. - O(n^2)
// S.C. - O(n + n + n^2)
class Solution {
    public int solve(int i, int j, int[] arr, HashMap<Integer, Integer> map, int[][] dp){
        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int target = arr[j] - arr[i];

        if(map.containsKey(target) && map.get(target) < i){
            // is one element is found in left, so in order to find next element in
            // left, we'll send elements idx and ith idx and excluding the jth
            // idx which means we have 1 prev element which is part of the sequence
            // so added 1 and called the recursion to find next
            return dp[i][j] = 1 + solve(map.get(target), i, arr, map, dp);
        }

        // in case of no element found in left, then we have 2 elements 
        // from which recursion call is initiated
        return dp[i][j] = 2;
    }

    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;

        // map<num, index>
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<n; i++){
            map.put(arr[i], i);
        }

        int maxLength = 0;
        int[][] dp = new int[n][n];
        
        for(int row[] : dp){
            Arrays.fill(row, -1);
        }

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                int length = solve(i, j, arr, map, dp);

                maxLength = Math.max(maxLength, length);
            }
        }

        return maxLength >= 3 ? maxLength : 0;
    }
}





// Approach 4 - Bottom-Up(DP)
// T.C. - O(n^2 + n)
// S.C. - O(n + n^2)
class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;

        // map<num, index>
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<n; i++){ // O(n)
            map.put(arr[i], i);
        }

        int maxLength = 0;

        // dp[i][j] = max length of fibonacci sequence ending at i, j
        int[][] dp = new int[n][n];

        for(int[] row : dp){
            Arrays.fill(row, 2); // initially we have 2 elements one at i and other at j
        }

        for(int i = 0; i<n; i++){ // O(n^2)
            for(int j = i+1; j<n; j++){
                int target = arr[j] - arr[i];

                if(map.containsKey(target) && map.get(target) < i){
                    dp[i][j] = 1 + dp[map.get(target)][i];
                }
                
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }

        return maxLength >= 3 ? maxLength : 0;
    }
}






// Approach 6 - DP + Two Pointer
// T.C. - O(n^2)
// S.C. - O(n^2)
class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int maxLength = 0;

        // dp[i][j] = max length of fibonacci sequence ending at i, j
        int[][] dp = new int[n][n];

        for(int curr = 2; curr<n; curr++){ // O(n^2)
            int start = 0;
            int end = curr-1;

            while(start < end){
                int sum = arr[start] + arr[end];

                if(sum == arr[curr]){
                    dp[end][curr] = dp[start][end] + 1;
                    maxLength = Math.max(maxLength, dp[end][curr]);

                    start++;
                    end--;
                }
                else if(sum > arr[curr]){
                    end--;
                }
                else{
                    start++;
                }
            }
        }

        return maxLength == 0 ? 0 : maxLength+2;
    }
}