// LeetCode - 1861 - Medium



// Brute Force - Simulation - O(col * row * row)
class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        char[][] result = new char[n][m];

        // Transpose of the box matrix
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                result[i][j] = box[j][i];
            }
        }

        // To rotate 90 deg. reverse each row of the transposed matrix
        for(char[] row : result){
            int i = 0;
            int j = m-1;

            while(i < j){
                char temp = row[i];
                row[i] = row[j];
                row[j] = temp;

                i++;
                j--;
            }
        }

        // Now traversing in rotated result matrix, columnwise from last cell
        for(int col = 0; col<m; col++){ // O(m * n * n)
            for(int row = n-1; row >= 0; row--){
                if(result[row][col] == '.'){
                    int initialStonePos = -1;

                    for(int k = row - 1; k >= 0; k--){
                        if(result[k][col] == '*'){
                            break;
                        }
                        else if(result[k][col] == '#'){
                            initialStonePos = k;
                            break;
                        }
                    }

                    if(initialStonePos != -1){
                        result[row][col] = '#';
                        result[initialStonePos][col] = '.';
                    }
                }
            }
        }

        return result;
    }
}




// Optimal - O(row * col)
class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        char[][] result = new char[n][m];

        // Transpose of the box matrix
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                result[i][j] = box[j][i];
            }
        }

        // To rotate 90 deg. reverse each row of the transposed matrix
        for(char[] row : result){
            int i = 0;
            int j = m-1;

            while(i < j){
                char temp = row[i];
                row[i] = row[j];
                row[j] = temp;

                i++;
                j--;
            }
        }

        // Now traversing in rotated result matrix, columnwise from last cell
        for(int col = 0; col<m; col++){
            int initialStonePos = n-1;

            for(int row = n-1; row >= 0; row--){
                if(result[row][col] == '*'){
                    initialStonePos = row-1;
                    continue;
                }

                if(result[row][col] == '#'){
                    result[row][col] = '.';
                    result[initialStonePos][col] = '#';
                    initialStonePos--;
                }
            }
        }

        return result;
    }
}
