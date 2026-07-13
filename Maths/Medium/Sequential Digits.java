// LeetCode - 1291



// Approach 1 - Maths
// T.C. - O(n.log(n)); n = (high - low + 1) ~ 10^9
// S.C. - O(log(n))
class Solution {
    public boolean isSequential(int num){
        List<Integer> digit = new ArrayList<>();
        int n = 0;

        while(num > 0){
            int d = num % 10;
            digit.add(d);
            n++;
            num /= 10;
        }

        for(int i = 0; i<digit.size()-1; i++){
            if(digit.get(i) - 1 != digit.get(i+1)){
                return false;
            }
        }

        return true;
    }

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> list = new ArrayList<>();

        for(int i = low; i <= high; i++){
            if(isSequential(i)){
                list.add(i);
            }
        }

        return list;
    }
}






// Approach 2 - Maths
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        /**
            Generating all sequence withing range as total are 36
            2 digits -> 8 -> 12, 23, 34, 45, 56, 67, 78, 89
            3 digits -> 7 -> 123, 234, 345, 456, 567, 678, 789
            ...
            ...
            9 digits -> 1 -> 123456789
        */
        List<Integer> list = new ArrayList<>();

        for(int i = 1; i <= 9; i++){
            int num = 0;

            for(int digit = i; digit <= 9; digit++){
                num = (num * 10) + digit;

                if(num >= low && num <= high){
                    list.add(num);
                }
            }
        }

        Collections.sort(list);

        return list;
    }
}