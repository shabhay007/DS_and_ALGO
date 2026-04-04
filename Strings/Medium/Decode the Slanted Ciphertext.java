// LeetCode - 2075



// Approach 1 - Building Matrix first then traversing diagonally
// T.C. - O(rows * cols)
// S.C. - O(rows * cols)
class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        int cols = n/rows;

        char[][] mat = new char[rows][cols];
        int k = 0; // to traverse in encodedText;
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                mat[i][j] = encodedText.charAt(k++);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int col = 0; col<cols; col++){
            int i = 0;
            int j = col;

            while(i < rows && j < cols){
                sb.append(mat[i][j]);
                i++;
                j++;
            }
        }

        // Removing trailing spaces from the result
        while(sb.length() > 0 && sb.charAt(sb.length() - 1) == ' '){
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}





// Approach 2 - simulating diagonal traversal (Directly traversing in encodedText)
// T.C. - O(L)
// S.C. - O(1)
class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int l = encodedText.length();
        int n = encodedText.length();
        int cols = n/rows;

        StringBuilder sb = new StringBuilder();
        for(int col = 0; col<cols; col++){
            for(int j = col; j<l; j += (cols+1)){
                sb.append(encodedText.charAt(j));
            }
        }

        // Removing trailing spaces from the result
        while(sb.length() > 0 && sb.charAt(sb.length() - 1) == ' '){
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}