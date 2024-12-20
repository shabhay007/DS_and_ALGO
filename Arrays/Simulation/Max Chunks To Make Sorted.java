// LeetCode Medium - 769


// Approach 1 - Simulation
// T.C. - O(n)
// S.C. - O(2*n)
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int chunkCount = 0;

        int[] prefixMax = new int[n];
        prefixMax[0] = arr[0];
        for(int i = 1; i<n; i++){
            prefixMax[i] = Math.max(prefixMax[i-1], arr[i]);
        }

        int[] suffixMin = new int[n];
        suffixMin[n-1] = arr[n-1];
        for(int i = n-2; i>=0; i--){
            suffixMin[i] = Math.min(arr[i], suffixMin[i+1]);
        }

        for(int i = 0; i<n; i++){
            int prifixMaxTillIndex = (i>0) ? prefixMax[i-1] : -1;
            int suffixMinTillIndex = suffixMin[i];

            if(prifixMaxTillIndex < suffixMinTillIndex){
                chunkCount++;
            }
        }

        return chunkCount;
    }
}



// Approach 2 - Simulation, Cummulative Sum
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int chunkCount = 0;

        int currSum = 0; // sum of arr elements
        int orgSum = 0; // sum of indices i.e. 0+1+2+3+....+(n-1)

        for(int i = 0; i<n; i++){
            currSum += arr[i];
            orgSum += i;

            if(currSum == orgSum){
                chunkCount++;
            }
        }

        return chunkCount;
    }
}



// Approach 3 - Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int chunkCount = 0;

        int maxTillNow = -1;

        for(int i = 0; i<n; i++){
            maxTillNow = Math.max(maxTillNow, arr[i]);

            if(maxTillNow == i){
                chunkCount++;
            }
        }

        return chunkCount;
    }
}



// Approach 4 - Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        int chunkCount = 0;

        for(int i = 0; i<n; i++){
            max = Math.max(max, arr[i]);

            if(max < i+1){
                chunkCount++;
            }
        }

        return chunkCount;
    }
}