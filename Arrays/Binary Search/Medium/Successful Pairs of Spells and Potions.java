// LeetCode - 2300



// Approach 1 - Brute Force (Gives TLE)
// T.C. - O(n*m)
// S.C. - O(1)
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            int successfullPairs = 0;

            for(int j = 0; j<m; j++){
                if((long) spells[i] * potions[j] >= success){
                    successfullPairs++;
                }
            }

            result[i] = successfullPairs;
        }

        return result;
    }
}





// Approach 2 - Binary Search
// T.C. - O(mlog(m) + nlog(m))
// S.C. - O(1)
class Solution {
    public int lowerBound(int spell, int[] spells, int[] potions, long success){
        int l = 0;
        int r = potions.length - 1;
        int index = -1;

        while(l <= r){
            int mid = l + (r - l)/2;

            if((long) spells[spell] * potions[mid] >= success){
                index = mid;
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return index;
    }

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        Arrays.sort(potions);

        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            int successfullPairs = 0;

            int idx = lowerBound(i, spells, potions, success);

            if(idx == -1){
                result[i] = 0;
            }
            else{
                result[i] = m-idx;
            }
        }

        return result;
    }
}