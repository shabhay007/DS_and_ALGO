// LeetCode - 1200



// Approach 1 - Sorting
// T.C. - O(n + nlog(n))
// S.C. - O(1)
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;

        for(int i = 0; i<n-1; i++){
            minDiff = Math.min(minDiff, arr[i+1] - arr[i]);
        }

        // System.out.println(minDiff);
        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i<n-1; i++){
            if(arr[i+1] - arr[i] == minDiff){
                result.add(Arrays.asList(arr[i], arr[i+1]));
            }
        }

        return result;
    }
}