// LeetCode - 1404



// Approach 1 - Bit Manipulation + Simulation
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public void addOne(StringBuilder sb){
        int i = sb.length() - 1;

        while(i >= 0 && sb.charAt(i) == '1'){
            sb.setCharAt(i, '0');
            i--;
        }

        if(i < 0){
            sb.insert(0, '1');
        }
        else{
            sb.setCharAt(i, '1');
        }
    }

    public int numSteps(String s) {
        StringBuilder sb = new StringBuilder(s);
        int steps = 0;

        while(sb.length() > 1){ // O(log(num))
            int n = sb.length();

            if(sb.charAt(sb.length() - 1) == '0'){ // even
                sb.deleteCharAt(sb.length() - 1);
            }
            else{ // odd
                addOne(sb);
            }

            steps++;
        }

        return steps;
    }
}





// Approach 2 - Bit Manipulation + Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int numSteps(String s) {
        int n = s.length();
        int steps = 0;
        int carry = 0;

        for(int i = n-1; i > 0; i--){
            int last = s.charAt(i) - '0';

            if((last + carry) % 2 == 0){
                steps += 1;
            }
            else{
                steps += 2;
                carry = 1;
            }
        }

        return steps + carry;
    }
}