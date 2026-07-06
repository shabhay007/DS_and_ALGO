// LeetCode Medium - 3394


// Optimal
// T.C. - O(nlogn)
// S.C. - O(n)
class Solution {
    public int[][] mergeIntervals(int[][] grid){
        // sorting
        Arrays.sort(grid, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> list = new ArrayList<>();
        list.add(grid[0]);

        for(int i = 1; i<grid.length; i++){
            if(grid[i][0] < list.get(list.size() - 1)[1]){
                list.get(list.size() - 1)[1] = Math.max(grid[i][1], list.get(list.size() - 1)[1]);
            }
            else{
                list.add(grid[i]);
            }
        }

        return list.toArray(new int[list.size()][]);
    }

    public boolean checkValidCuts(int n, int[][] rectangles) {
        int m = rectangles.length;
        List<int[]> xList = new ArrayList<>();
        List<int[]> yList = new ArrayList<>();

        for(int[] rect : rectangles){
            int x1 = rect[0];
            int y1 = rect[1];
            int x2 = rect[2];
            int y2 = rect[3];

            xList.add(new int[]{x1, x2});
            yList.add(new int[]{y1, y2});
        }

        int[][] xAxis = xList.toArray(new int[xList.size()][]);
        int[][] xInterval = mergeIntervals(xAxis);

        if(xInterval.length >= 3){
            return true;
        }

        int[][] yAxis = yList.toArray(new int[yList.size()][]);
        int[][] yInterval = mergeIntervals(yAxis);

        return yInterval.length >= 3;
    }
}