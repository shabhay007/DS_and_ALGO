// LeetCode - 788



// Approach 1 - StringBuilder
// T.C. - O(n * log(num))
// S.C. - O(log(num))
class Solution {
    public int rotate(int num){
        String str = String.valueOf(num);
        StringBuilder sb = new StringBuilder();

        for(char ch : str.toCharArray()){
            if(ch == '3' || ch == '4' || ch == '7'){
                return -1; // invalid case
            }

            if(ch == '2') sb.append('5');
            else if(ch == '5') sb.append('2');
            else if(ch == '6') sb.append('9');
            else if(ch == '9') sb.append('6');
            else sb.append(ch);
        }

        return Integer.parseInt(sb.toString());
    }

    public int rotatedDigits(int n) {
        int result = 0;

        for(int i = 1; i <= n; i++){
            int num = rotate(i);

            if(num == -1 || num == i){
                continue;
            }

            // System.out.println(num);
            result++;
        }

        return result;
    }
}






// Approach 2 - Maths
// T.C. - O(n * log(num))
// S.C. - O(log(num))
class Solution {
    public boolean isGood(int num){
        boolean isChanged = false;

        while(num > 0){
            int d = num % 10;

            if(d == 3 || d == 4 || d == 7){
                return false;
            }

            if(d == 2 || d == 5 || d == 6 || d == 9){
                isChanged = true;
            }

            num /= 10;
        }

        return isChanged;
    }

    public int rotatedDigits(int n) {
        int result = 0;

        for(int i = 1; i <= n; i++){
            if(isGood(i)){
                result++;
            }
        }

        return result;
    }
}