// LeetCode - 2975



// Approach 1 - Using Set
// T.C. - O(h^2 + v^2); h = hFences.length, v = vFences.length
// S.C. - O(h^2 + v^2)
class Solution {
    static int mod = (int) 1e9 + 7;

    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        // Add boundaries to the fences
        List<Integer> hPoints = new ArrayList<>();
        hPoints.add(1);
        hPoints.add(m);

        for (int fence : hFences) {
            hPoints.add(fence);
        }
        
        List<Integer> vPoints = new ArrayList<>();
        vPoints.add(1);
        vPoints.add(n);

        for (int fence : vFences) {
            vPoints.add(fence);
        }
        
        // Get all possible horizontal distances
        Set<Integer> hDistances = new HashSet<>();

        for (int i = 0; i < hPoints.size(); i++) {
            for (int j = i + 1; j < hPoints.size(); j++) {
                hDistances.add(Math.abs(hPoints.get(j) - hPoints.get(i)));
            }
        }
        
        // Get all possible vertical distances
        Set<Integer> vDistances = new HashSet<>();

        for (int i = 0; i < vPoints.size(); i++) {
            for (int j = i + 1; j < vPoints.size(); j++) {
                vDistances.add(Math.abs(vPoints.get(j) - vPoints.get(i)));
            }
        }


        long maxSide = -1;

        for(int d : hDistances){
            if(vDistances.contains(d)){
                maxSide = Math.max(maxSide, d);
            }
        }

        if(maxSide == -1){
            return -1;
        }

        return (int) ((maxSide * maxSide) % mod);
    }
}