// LeetCode - 757



// Approach 1 - Greedy + Sorting
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        int n = intervals.length;

        // sorting in asc of endpoint
        // if endpoint is same -> sort in desc order of starting point
        Arrays.sort(intervals, (a, b) -> (a[1] == b[1]) ? b[0]-a[0] : a[1]-b[1]);

        int result = 0;
        int first = -1;
        int second = -1;

        for(int i = 0; i<n; i++){
            int l = intervals[i][0];
            int r = intervals[i][1];

            if(l <= first){
                continue;
            }
            else if(l > second){
                // overlapping not possible
                result += 2;
                first = r-1;
                second = r;
            }
            else{
                // l <= second
                // overlapping might be possible
                // or one endpoint may be equal
                // so taking only one point
                first = second;
                second = r;
                result += 1;
            }
        }

        return result;
    }
}