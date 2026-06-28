// LeetCode 1846



// Approach 1 - Greedy
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        
        Arrays.sort(arr);

        int curr = 1;
        for(int i = 1; i<n; i++){
            if(arr[i] - curr <= 1){
                curr = Math.max(curr, arr[i]);
                continue;
            }
            else{
                curr++;
            }
        }

        return curr;
    }
}