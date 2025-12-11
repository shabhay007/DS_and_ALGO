// LeetCode - 3531



// Approach 1 - Map + Sorting
// T.C. - O(n * nlog(n))
// S.C. - O(n)
class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        Map<Integer, List<Integer>> xCoordinates = new HashMap<>();
        Map<Integer, List<Integer>> yCoordinates = new HashMap<>();
        int result = 0;

        for(int[] point : buildings){ // O(n)
            int x = point[0];
            int y = point[1];

            xCoordinates.putIfAbsent(x, new ArrayList<>());
            xCoordinates.get(x).add(y);

            yCoordinates.putIfAbsent(y, new ArrayList<>());
            yCoordinates.get(y).add(x);
        }

        // System.out.println(xCoordinates);
        // System.out.println(yCoordinates);

        for(int[] point : buildings){
            int x = point[0];
            int y = point[1];

            List<Integer> xBlocks = xCoordinates.get(x);
            Collections.sort(xBlocks);
            boolean flag1 = false;

            if(xBlocks.size() > 2 && xBlocks.get(0) < y && xBlocks.get(xBlocks.size()-1) > y){
                flag1 = true;
            }

            List<Integer> yBlocks = yCoordinates.get(y);
            Collections.sort(yBlocks);
            boolean flag2 = false;

            if(yBlocks.size() > 2 && yBlocks.get(0) < x && yBlocks.get(yBlocks.size()-1) > x){
                flag2 = true;
            }

            if(flag1 && flag2){
                result++;
            }
        }

        return result;
    }
}





// Approach 2 - Map
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        Map<Integer, int[]> xCoordinates = new HashMap<>();
        Map<Integer, int[]> yCoordinates = new HashMap<>();
        int result = 0;

        for(int[] point : buildings){ // O(n)
            int x = point[0];
            int y = point[1];

            xCoordinates.putIfAbsent(x, new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
            int[] arr1 = xCoordinates.get(x);
            arr1[0] = Math.min(arr1[0], y);
            arr1[1] = Math.max(arr1[1], y);

            yCoordinates.putIfAbsent(y, new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
            int[] arr2 = yCoordinates.get(y);
            arr2[0] = Math.min(arr2[0], x);
            arr2[1] = Math.max(arr2[1], x);
        }

        for(int[] point : buildings){ // O(n)
            int x = point[0];
            int y = point[1];

            int[] xBlocks = xCoordinates.get(x);
            boolean flag1 = xBlocks[0] < y && xBlocks[1] > y;

            int[] yBlocks = yCoordinates.get(y);
            boolean flag2 = yBlocks[0] < x && yBlocks[1] > x;

            if(flag1 && flag2){
                result++;
            }
        }

        return result;
    }
}