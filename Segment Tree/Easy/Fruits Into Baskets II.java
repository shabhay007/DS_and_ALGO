// LeetCode - 3477



// Approach 1 - Brute Force (Using visited)
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        boolean visited[] = new boolean[n];

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(!visited[j] && baskets[j] >= fruits[i]){
                    visited[j] = true;
                    break;
                }
            }
        }

        int count = 0;

        for(boolean val : visited){
            if(!val){
                count++;
            }
        }

        return count;
    }
}





// Approach 2 - Brute Force (const. space but modifying input i.e. not recommended)
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        int count = n;

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(baskets[j] != -1 && baskets[j] >= fruits[i]){
                    baskets[j] = -1;
                    count--;
                    break;
                }
            }
        }

        return count;
    }
}





// Approach (Using Segment Tree Range Maximum Query + Binary Search)
// T.C : O(nlogn)
// S.C : O(n)
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        int[] segmentTree = new int[4 * n];

        // Build segment tree
        build(0, 0, n - 1, baskets, segmentTree);

        int unplaced = 0;

        for (int fruit : fruits) {
            if (!querySegmentTree(0, 0, n - 1, segmentTree, fruit)) {
                unplaced++;
            }
        }

        return unplaced;
    }

    private void build(int i, int l, int r, int[] baskets, int[] segmentTree) {
        if (l == r) {
            segmentTree[i] = baskets[l];
            return;
        }
        
        int m = (l + r) / 2;

        build(2 * i + 1, l, m, baskets, segmentTree);
        build(2 * i + 2, m + 1, r, baskets, segmentTree);

        segmentTree[i] = Math.max(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);
    }

    private boolean querySegmentTree(int i, int l, int r, int[] segmentTree, int val) {
        if (segmentTree[i] < val) {
            return false; // No basket in this segment
        }

        if (l == r) {
            segmentTree[i] = -1; // Mark basket as used
            return true;
        }

        int mid = (l + r) / 2;
        boolean placed;

        if (segmentTree[2 * i + 1] >= val) {
            placed = querySegmentTree(2 * i + 1, l, mid, segmentTree, val);
        } 
        else {
            placed = querySegmentTree(2 * i + 2, mid + 1, r, segmentTree, val);
        }

        segmentTree[i] = Math.max(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);

        return placed;
    }
}