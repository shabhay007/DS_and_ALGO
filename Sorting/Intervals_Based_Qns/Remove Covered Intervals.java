// LeetCode Medium - 1288



// Approach 1 - Sorting
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int n = intervals.length;

        // custom sorting
        Arrays.sort(intervals, (a, b) -> (a[0] != b[0]) ? a[0] - b[0] : b[1] - a[1]);

        List<int[]> merged = new ArrayList<>();

        for(int[] interval : intervals){
            if(merged.isEmpty()){
                merged.add(interval);
            }
            else{
                int c = interval[0];
                int d = interval[1];

                int[] last = merged.get(merged.size() - 1);
                int a = last[0];
                int b = last[1];

                if(a <= c && b >= d){
                    continue;
                }
                else{
                    merged.add(interval);
                }
            }
        }

        return merged.size();
    }
}






// Approach 2 - Sorting
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int n = intervals.length;

        // custom sorting
        Arrays.sort(intervals, (a, b) -> (a[0] != b[0]) ? a[0] - b[0] : b[1] - a[1]);

        int count = 1;
        int i = 0;
        int j = 1;

        while(j < n){
            int a = intervals[i][0];
            int b = intervals[i][1];

            int c = intervals[j][0];
            int d = intervals[j][1];

            if(a <= c && b >= d){
                j++;
            }
            else{
                count++;
                i = j;
                j++;
            }
        }

        return count;
    }
}