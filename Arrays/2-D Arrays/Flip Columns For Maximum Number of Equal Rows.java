// LeetCode - 1072 - Medium


// Approach 1 - Brute Force
class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int maxRows = 0;

        for(int[] rows : matrix){
            int[] invertedRows = new int[n];

            for(int col = 0; col<n; col++){
                invertedRows[col] = (rows[col] == 0) ? 1 : 0;
            }

            int count = 0;
            for(int[] row : matrix){ // O(m)
                // O(n) - total n comparisons
                if(Arrays.equals(row, rows) || Arrays.equals(row, invertedRows)){ 
                    count++;
                }
            }

            maxRows = Math.max(maxRows, count);
        }

        return maxRows;
    }
}


// Approach 2 - HashSet
class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        HashMap<String, Integer> map = new HashMap<>();

        int maxCount = 0;
        for(int[] rows : matrix){
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();

            for(int col = 0; col < n; col++){
                sb1.append(rows[col]);
                sb2.append(rows[col] == 0 ? 1 : 0); // For inverted row checking
            }

            String str1 = sb1.toString();
            String str2 = sb2.toString();

            map.put(str1, map.getOrDefault(str1, 0) + 1);
            map.put(str2, map.getOrDefault(str2, 0) + 1);

            maxCount = Math.max(maxCount, map.get(str1));
        }

        // for(Map.Entry<String, Integer> entry : map.entrySet()){
        //     int val = entry.getValue();

        //     maxCount = Math.max(maxCount, val);
        // }

        return maxCount;
    }
}



// Approach 3 - HashSet - T.C - O((m*n) + k), S.C - O(k)
class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        HashMap<String, Integer> map = new HashMap<>();

        int maxCount = 0;
        for(int[] rows : matrix){ // O(m)
            StringBuilder natureOfRow = new StringBuilder();
            int firstVal = rows[0];

            for(int col = 0; col < n; col++){ // O(n)
                if(rows[col] == firstVal){
                    natureOfRow.append("Same");
                }
                else{
                    natureOfRow.append("Diff");
                }
            }

            map.put(natureOfRow.toString(), map.getOrDefault(natureOfRow.toString(), 0) + 1);
        }

        for(Map.Entry<String, Integer> entry : map.entrySet()){ // O(k)
            int val = entry.getValue();

            maxCount = Math.max(maxCount, val);
        }

        return maxCount;
    }
}