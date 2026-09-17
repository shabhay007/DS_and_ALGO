// LeetCode - 1477



// Approach 1 - Sliding Window
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int i = 0;
        int j = 0;

        int sum = 0;
        int currLen = 0;
        int bestMin = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;

        // But we need to take care of non-overlapping also
        // minLenTillIdx[i-1] = min subarray length where sum == target
        int[] minLenTillIdx = new int[n];
        Arrays.fill(minLenTillIdx, Integer.MAX_VALUE);

        while(j < n){
            sum += arr[j];

            while(i <= j && sum > target){
                sum -= arr[i];
                i++;
            }

            if(sum == target){
                currLen = j-i+1;

                if(i > 0 && minLenTillIdx[i-1] != Integer.MAX_VALUE){
                    result = Math.min(result, minLenTillIdx[i-1] + currLen);
                }

                bestMin = Math.min(bestMin, currLen);
            }

            minLenTillIdx[j] = bestMin;
            j++;
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}