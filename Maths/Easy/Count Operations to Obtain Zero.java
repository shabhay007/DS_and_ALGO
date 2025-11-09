// LeetCode - 2169



// Approach 1 - Maths, Simulation
// T.C. - O(max); max = max(num1, num2)
// S.C. - O(1)
class Solution {
    public int countOperations(int num1, int num2) {
        int count = 0;

        while(num1 != 0 && num2 != 0){
            if(num1 > num2){
                num1 -= num2;
                count++;
            }
            else if(num2 > num1){
                num2 -= num1;
                count++;
            }
            else{
                count++;
                return count;
            }
        }

        return count;
    }
}





// Approach 2 - Maths (Iterative Euclidean version)
// T.C. - O(log(min)); min = min(num1, num2)
// S.C. - O(1)
class Solution {
    public int countOperations(int num1, int num2) {
        int count = 0;

        while(num1 > 0 && num2 > 0){
            count += num1/num2;
            
            // swaping with modification
            int temp = num1 % num2;
            num1 = num2;
            num2 = temp;
        }

        return count;
    }
}





// Approach 3 - Maths (Recursive Euclidean version)
// T.C. - O(log(min)); min = min(num1, num2)
// S.C. - O(1)
class Solution {
    public int countOperations(int num1, int num2) {
        if(num1 == 0 || num2 == 0){
            return 0;
        }

        if(num1 < num2){
            int temp = num2;
            num2 = num1;
            num1 = temp;
        }

        return num1/num2 + countOperations(num1 % num2, num2);
    }
}