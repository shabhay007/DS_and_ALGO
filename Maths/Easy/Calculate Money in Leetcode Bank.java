// LeetCode - 1716



// Approach 1 - Maths + Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int totalMoney(int n) {
        int result = 0;
        int dayCount = 1;
        int counter = 0;
        int i = 1;

        while(n > 0){
            result += i;
            counter++;
            i++;
            n--;

            if(counter % 7 == 0){
                dayCount++;
                i = dayCount;
                counter = 0;
            }
        }

        return result;
    }
}





// Approach 2 - Maths + Simulation
// T.C. - O(n * 7)
// S.C. - O(1)
class Solution {
    public int totalMoney(int n) {
        int result = 0;
        int dayCount = 1;

        while(n > 0){
            int money = dayCount;

            for(int day = 1; day <= Math.min(n, 7); day++){
                result += money++;
            }

            dayCount++;
            n -= 7;
        }

        return result;
    }
}





// Approach 3 - Using AP
// T.C : O(1)
// S.C : O(1)
class Solution {
    public int totalMoney(int n) {
        int terms = n / 7; // Weeks

        int first = 28;
        int last = 28 + (terms - 1) * 7; // AP formula for n-th term

        int result = terms * (first + last) / 2; // Sum of nth terms in an AP

        // Remaining days (use AP formula)
        int remain = n % 7;
        int firstTerm = 1 + terms;
        int lastTerm = firstTerm + remain - 1;
        result += remain * (firstTerm + lastTerm) / 2;

        return result;
    }
}