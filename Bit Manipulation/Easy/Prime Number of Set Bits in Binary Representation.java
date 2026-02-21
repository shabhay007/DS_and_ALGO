// LeetCode - 762



// Approach 1 - Bit Manipulation + Primality Check
// T.C. - O((right - left + 1) * 32)  ~ O(n); max bits = 32
// S.C. - O(1)
class Solution {
    public boolean isPrime(int num){
        if(num <= 1){
            return false;
        }

        for(int i = 2; i*i <= num; i++){
            if(num % i == 0){
                return false;
            }
        }

        return true;
    }

    public int countPrimeSetBits(int left, int right) {
        int primeCount = 0;

        for(int i = left; i <= right; i++){
            int setBits = Integer.bitCount(i);

            if(isPrime(setBits)){
                primeCount++;
            }
        }

        return primeCount;
    }
}





// Approach 2 - Bit Manipulation + Set
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int countPrimeSetBits(int left, int right) {
        /*
            from constraints, we can see 2^10 = 1024 ~ 10^2
            sq. both side, 2^20 = 10^6
            so, we can say that 20 bits will accomodate values upto 10^6
            and in worst case all bits will be set, so, we need to check for prime
            upto 20
        */
        Set<Integer> set = new HashSet<>();
        set.add(2);
        set.add(3);
        set.add(5);
        set.add(7);
        set.add(11);
        set.add(13);
        set.add(17);
        set.add(19);

        int primeCount = 0;
        for(int i = left; i <= right; i++){
            int setBits = Integer.bitCount(i);

            if(set.contains(setBits)){
                primeCount++;
            }
        }

        return primeCount;
    }
}