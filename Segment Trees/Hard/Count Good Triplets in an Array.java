// LeetCode Hard - 2179


// Approach 1 - Brute Force (Most probably Gives TLE)
/*
    Map nums2 in map (element -> index)
    Take triplet as (x, y, z) where i_x < i_y < i_z
    And for a triplet, we need to check for every y (0 < i_y < n) in nums1
    And then we have to find common elements in left of nums1 and nums2
    And then we have to find common elements in right of nums1 and nums2
    Then result += left * right
*/




// Approach 2 - Using Segment Tree
// T.C. - O(n + n.2log(n))
// S.C. - O(n + 4n)
class Solution {
    public void updateSegmentTree(int i, int l, int r, int idx, long[] segmentTree){ // O(log(n))
        if(l == r){
            segmentTree[i] = 1; // marked visited
            return;
        }

        int mid = l + (r - l)/2;

        if(idx <= mid){
            updateSegmentTree(2*i+1, l, mid, idx, segmentTree);
        }
        else{
            updateSegmentTree(2*i+2, mid+1, r, idx, segmentTree);
        }

        segmentTree[i] = segmentTree[2*i+1] + segmentTree[2*i+2];
    }

    // O(log(n))
    public long querySegmentTree(int queryStart, int queryEnd, int i, int l, int r, long[] segmentTree){
        if(l < queryStart || l > queryEnd){
            return 0;
        }

        if(l >= queryStart && r <= queryEnd){
            return segmentTree[i];
        }

        int mid = l + (r - l)/2;

        long left = querySegmentTree(queryStart, queryEnd, 2*i+1, l, mid, segmentTree);
        long right = querySegmentTree(queryStart, queryEnd, 2*i+2, mid+1, r, segmentTree);

        return left+right;
    }

    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;

        // step 1 : storing index of elements of nums2
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i<n; i++){ // O(n)
            map.put(nums2[i], i);
        }

        // step 2 : take a segment tree and find the count of common elements to get the result
        long[] segmentTree = new long[4 * n];
        long result = 0;

        updateSegmentTree(0, 0, n-1, map.get(nums1[0]), segmentTree);

        for(int i = 1; i<n; i++){ // O(n * 2log(n))
            int idx = map.get(nums1[i]); // index in nums2

            long leftCommonElementCount = querySegmentTree(0, idx, 0, 0, n-1, segmentTree); // O(log(n))
            long leftNotCommonElementCount = i - leftCommonElementCount;
            long elementsAfterIdxInNums2 = (n-1) - idx;
            long rightCommonElementCount = elementsAfterIdxInNums2 - leftNotCommonElementCount;

            result += leftCommonElementCount * rightCommonElementCount;

            updateSegmentTree(0, 0, n-1, idx, segmentTree); // O(log(n))
        }

        return result;
    }
}