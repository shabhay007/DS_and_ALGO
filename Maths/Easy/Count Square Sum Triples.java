// LeetCode - 1925



// Approach 1 - Brute Force
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public int countTriples(int n) {
        int noOfTriplets = 0;

        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                for(int k = 1; k<=n; k++){
                    if((i*i) + (j*j) == (k*k)){
                        noOfTriplets++;
                    }
                }
            }
        }

        return noOfTriplets;
    }
}




// Approach 2 - Better Using Set
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public int countTriples(int n) {
        Set<Integer> set = new HashSet<>();

        for(int i = 1; i<=n; i++){
            set.add(i*i);
        }

        int noOfTriplets = 0;

        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                if(set.contains((i*i) + (j*j))){
                    noOfTriplets++;
                }
            }
        }

        return noOfTriplets;
    }
}





// Approach 3 - Better
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int countTriples(int n) {
        int noOfTriplets = 0;

        for(int i = 1; i<=n; i++){
            for(int j = i+1; j<=n; j++){
                int s = (i*i) + (j*j);
                int sq = (int) Math.sqrt(s);

                if((sq * sq) == s && sq <= n){
                    noOfTriplets += 2; // (a,b,c) and (b,a,c)
                }
            }
        }

        return noOfTriplets;
    }
}