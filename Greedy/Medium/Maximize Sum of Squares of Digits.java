// LeetCode - 3723



// Approach 1 - Greedy
// T.C. - O(num)
// S.C. - O(1)
class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if(sum > num * 9){
            return "";
        }

        int nine = sum / 9;
        int rem = sum % 9;

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<num; i++){
            if(i < nine){
                sb.append('9');
            }
            else if(i == nine){
                sb.append(rem);
            }
            else{
                sb.append('0');
            }
        }

        return sb.toString();
    }
}