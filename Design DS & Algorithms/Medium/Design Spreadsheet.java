// LeetCode - 3484



// Approach 1
// T.C. - O(n)
// S.C. - O(n)
class Spreadsheet {
    int[][] grid;

    public Spreadsheet(int rows) {
        this.grid = new int[rows][26];
    }
    
    public void setCell(String cell, int value) {
        int[] rowAndCol = findRowsAndColumns(cell);
        int row = rowAndCol[0];
        int col = rowAndCol[1];

        grid[row][col] = value;
    }
    
    public void resetCell(String cell) {
        int[] rowAndCol = findRowsAndColumns(cell);
        int row = rowAndCol[0];
        int col = rowAndCol[1];

        grid[row][col] = 0;
    }
    
    public int getValue(String formula) {
        if(formula.startsWith("=")){
            formula = formula.substring(1);
        }

        String[] arr = formula.split("\\+");
        int sum = 0;

        for(String str : arr){
            str = str.trim();

            if(Character.isLetter(str.charAt(0))){
                int[] rowAndCol = findRowsAndColumns(str);

                sum += grid[rowAndCol[0]][rowAndCol[1]];
            }
            else{
                sum += Integer.parseInt(str);
            }
        }

        return sum;
    }

    public int[] findRowsAndColumns(String cell){
        // 'A' → 0, 'B' → 1, 'C' → 2 … 'Z' → 25
        int col = cell.charAt(0) - 'A';

        int row = 0;

        for(int i = 1; i<cell.length(); i++){
            row = row * 10 + cell.charAt(i) - '0';
        }

        // "1" → row = 1
        // "10" → row = 10
        // to make row 0 based, returning row - 1
        return new int[]{row-1, col};
    }
}