// LeetCode - 1128


// Approach 1 - Brute Force (Gives TLE)
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int numEquivDominoPairs(int[][] d) {
        int n = d.length;
        int pairs = 0;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                if((d[i][0] == d[j][0] && d[i][1] == d[j][1]) || 
                   (d[i][0] == d[j][1] && d[i][1] == d[j][0])){
                    pairs++;
                   }
            }
        }

        return pairs;
    }
}





// Approach 2 - Map
// T.C. - O(3n)
// S.C. - O(n)
class Solution {
    public int numEquivDominoPairs(int[][] d) {
        int n = d.length;

        // setting min value at 0th idx and max at 1st idx for consistency
        for(int i = 0; i<n; i++){
            if(d[i][0] > d[i][1]){
                int temp = d[i][0];
                d[i][0] = d[i][1];
                d[i][1] = temp;
            }
        }


        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i<n; i++){
            String str = d[i][0] + "-" + d[i][1];

            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        int totalPairs = 0;

        for(int val : map.values()){
            totalPairs += val * (val - 1)/2;
        }

        return totalPairs;
    }
}





// Approach 3 - Map (Slight optimization in Approach 2)
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public int numEquivDominoPairs(int[][] d) {
        int n = d.length;
        Map<String, Integer> map = new HashMap<>();

        // setting min value at 0th idx and max at 1st idx for consistency
        for(int i = 0; i<n; i++){
            if(d[i][0] > d[i][1]){ // swapping
                int temp = d[i][0];
                d[i][0] = d[i][1];
                d[i][1] = temp;
            }

            String str = d[i][0] + "-" + d[i][1];

            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        int totalPairs = 0;

        for(int val : map.values()){
            totalPairs += val * (val - 1)/2;
        }

        return totalPairs;
    }
}




// Approach 4 - Optimal
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int numEquivDominoPairs(int[][] d) {
        int n = d.length;
        int[] freq = new int[100]; // as (9, 9) -> (9*10 + 9) = 99 (constraints)
        int result = 0;

        // setting min value at 0th idx and max at 1st idx for consistency
        for(int i = 0; i<n; i++){
            if(d[i][0] > d[i][1]){ // swapping
                int temp = d[i][0];
                d[i][0] = d[i][1];
                d[i][1] = temp;
            }

            int num = d[i][0] * 10 + d[i][1]; // (a * 10 + b);
            result += freq[num];

            freq[num]++;
        }

        return result;
    }
}