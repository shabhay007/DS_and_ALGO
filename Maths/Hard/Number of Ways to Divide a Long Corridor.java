// LeetCode - 2147



// Approach 1 - Maths
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int numberOfWays(String corridor) {
        int n = corridor.length();
        int mod = (int) 1e9 + 7;
        int noOfSeats = 0;
        List<Integer> position = new ArrayList<>();

        for(int i = 0; i<n; i++){
            char ch = corridor.charAt(i);

            if(ch == 'S'){
                noOfSeats++;
                position.add(i);
            }
        }

        if(noOfSeats <= 1 || noOfSeats % 2 != 0){
            return 0;
        }

        long result = 1;

        for(int i = 2; i<position.size(); i+=2){
            result = (result * (position.get(i) - position.get(i-1))) % mod;
        }

        return (int) result;
    }
}