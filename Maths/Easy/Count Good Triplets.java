// LeetCode Easy - 1534


// Approach 1 (Brute Force)
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length;
        int count = 0;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                for(int k = j+1; k<n; k++){
                    int x = Math.abs(arr[i] - arr[j]);
                    int y = Math.abs(arr[j] - arr[k]);
                    int z = Math.abs(arr[i] - arr[k]);

                    if(x <= a && y <= b && z <= c){
                        count++;
                    }
                }
            }
        }

        return count;
    }
}




// Approach 2 - Slight optimization in Approach 1
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length;
        int count = 0;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                int x = Math.abs(arr[i] - arr[j]);

                if(x <= a){ // slight optimization
                    for(int k = j+1; k<n; k++){
                        int y = Math.abs(arr[j] - arr[k]);
                        int z = Math.abs(arr[i] - arr[k]);

                        if(y <= b && z <= c){
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }
}