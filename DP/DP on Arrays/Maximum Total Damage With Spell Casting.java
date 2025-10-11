// LeetCode - 3186



// Approach 1 - Recursion (Gives TLE)
// T.C. - O(2^n . log(n))
// S.C. - O(n)
class Solution {
    public int lowerBound(int l, List<Long> list, long target){
        int r = list.size() - 1;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(list.get(mid) >= target){
                r = mid-1;
            }
            else{
                l = mid + 1;
            }
        }

        return l;
    }

    public long getMaxDamage(int i, List<Long> list, Map<Long, Long> map){
        if(i >= list.size()){
            return 0;
        }

        long skip = getMaxDamage(i+1, list, map);

        int nextIdx = lowerBound(i+1, list, list.get(i) + 3);
        long take = list.get(i) * map.get(list.get(i)) + getMaxDamage(nextIdx, list, map);

        return Math.max(skip, take);
    }

    public long maximumTotalDamage(int[] power) {
        int n = power.length;
        Map<Long, Long> map = new HashMap<>();

        // storing the elements with it's frequencies
        for(long p : power){
            map.put(p, map.getOrDefault(p, 0L) + 1);
        }

        // storing the unique elements from the map
        List<Long> list = new ArrayList<>();

        for(long key : map.keySet()){
            list.add(key);
        }

        // now sorting the list
        Collections.sort(list);

        // now performing the recursion in sorted list of unique elements
        return getMaxDamage(0, list, map);
    }
}





// Approach 2 - Recursion + Memoization
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int lowerBound(int l, List<Long> list, long target){
        int r = list.size() - 1;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(list.get(mid) >= target){
                r = mid-1;
            }
            else{
                l = mid + 1;
            }
        }

        return l;
    }

    // O(n*log(n))
    public long getMaxDamage(int i, List<Long> list, Map<Long, Long> map, long[] dp){
        if(i >= list.size()){
            return 0;
        }

        if(dp[i] != -1){
            return dp[i];
        }

        long skip = getMaxDamage(i+1, list, map, dp);

        int nextIdx = lowerBound(i+1, list, list.get(i) + 3); // log(n)
        long take = list.get(i) * map.get(list.get(i)) + getMaxDamage(nextIdx, list, map, dp);

        return dp[i] = Math.max(skip, take);
    }

    public long maximumTotalDamage(int[] power) {
        int n = power.length;
        Map<Long, Long> map = new HashMap<>();

        // storing the elements with it's frequencies
        for(long p : power){
            map.put(p, map.getOrDefault(p, 0L) + 1);
        }

        // storing the unique elements from the map
        List<Long> list = new ArrayList<>();

        for(long key : map.keySet()){
            list.add(key);
        }

        // now sorting the list
        Collections.sort(list);

        long[] dp = new long[n+1];
        Arrays.fill(dp, -1);

        // now performing the recursion in sorted list of unique elements
        return getMaxDamage(0, list, map, dp);
    }
}





// Approach 3 - Bottom Up
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int lowerBound(int l, List<Long> list, long target){
        int r = list.size() - 1;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(list.get(mid) >= target){
                r = mid-1;
            }
            else{
                l = mid + 1;
            }
        }

        return l;
    }

    public long maximumTotalDamage(int[] power) {
        Map<Long, Long> map = new HashMap<>();

        // storing the elements with it's frequencies
        for(long p : power){
            map.put(p, map.getOrDefault(p, 0L) + 1);
        }

        // storing the unique elements from the map & sorting
        List<Long> list = new ArrayList<>(map.keySet());
        Collections.sort(list);

        int n = list.size();
        long[] dp = new long[n];
        long result = 0;

        for(int i = n-1; i >= 0; i--){
            long skip = (i+1 < n) ? dp[i+1] : 0;

            // because of this we need to traverse from right to left as
            // it is a future idx in the array, so ans depends on future
            int nextIdx = lowerBound(i+1, list, list.get(i) + 3);
            long take = list.get(i) * map.get(list.get(i)) + ((nextIdx < n) ? dp[nextIdx] : 0);

            dp[i] = Math.max(skip, take);
            result = Math.max(result, dp[i]);
        }

        return result;
    }
}