// LeetCode - 1390



// Approach 1 - Brute Force
// T.C. - O(n * Max num)
// S.C. - O(1)
class Solution {
    public int divisorSum(int num){
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(num);

        for(int i = 2; i <= num/2; i++){
            if(num % i == 0 && !set.contains(i)){
                set.add(i);
                set.add(num/i);
            }
        }

        // System.out.println(set.toString());

        if(set.size() == 4){
            int sum = 0;

            for(int n : set){
                sum += n;
            }

            return sum;
        }

        return -1;
    }

    public int sumFourDivisors(int[] nums) {
        int resultSum = 0;

        for(int num : nums){
            int factorSum = divisorSum(num);

            if(factorSum != -1){
                resultSum += factorSum;
            }
        }

        return resultSum;
    }
}




// Approach 2 - Maths (Brute Force + Pruning)
// T.C. - O(n * M); M = 10^5 -> constraint, but will not go till 10^5
// S.C. - O(1)
class Solution {
    public int divisorSum(int num){
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(num);

        for(int i = 2; i <= num/2; i++){
            if(num % i == 0 && !set.contains(i)){
                set.add(i);
                set.add(num/i);
            }

            if(set.size() > 4){
                return -1;
            }
        }

        // System.out.println(set.toString());

        if(set.size() == 4){
            int sum = 0;

            for(int n : set){
                sum += n;
            }

            return sum;
        }

        return -1;
    }

    public int sumFourDivisors(int[] nums) {
        int n = nums.length;
        int resultSum = 0;

        for(int num : nums){
            int factorSum = divisorSum(num);

            if(factorSum != -1){
                resultSum += factorSum;
            }
        }

        return resultSum;
    }
}





// Approach 3 - Maths (Brute Force + Pruning)
// T.C. - O(n * sqrt(M)); M = 10^5 -> constraint
// S.C. - O(1)
class Solution {
    public int divisorSum(int num){
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(num);

        for(int i = 2; i*i <= num; i++){
            if(num % i == 0 && !set.contains(i)){
                set.add(i);
                set.add(num/i);
            }

            if(set.size() > 4){
                return -1;
            }
        }

        // System.out.println(set.toString());

        if(set.size() == 4){
            int sum = 0;

            for(int n : set){
                sum += n;
            }

            return sum;
        }

        return -1;
    }

    public int sumFourDivisors(int[] nums) {
        int n = nums.length;
        int resultSum = 0;

        for(int num : nums){
            int factorSum = divisorSum(num);

            if(factorSum != -1){
                resultSum += factorSum;
            }
        }

        return resultSum;
    }
}