// LeetCode - 869



// Approach-1 (Using sorting)
// T.C : O(dlogd), d = number of digits
// S.C : O(d)
class Solution {
    public String getSortedStr(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public boolean reorderedPowerOf2(int n) {
        String sortedStr = getSortedStr(n);

        // Check all powers of 2 up to 2^29
        for (int p = 0; p <= 29; p++) {
            if (sortedStr.equals(getSortedStr(1 << p))) {
                return true;
            }
        }

        return false;
    }
}





// Approach-2 (Using preprocessing and storing in a set)
// T.C : O(dlogd), d = number of digits
// S.C : O(d)
class Solution {
    private HashSet<String> set = new HashSet<>();

    private void buildSet() {
        for (int p = 0; p <= 29; p++) {
            char[] chars = String.valueOf(1 << p).toCharArray();
            Arrays.sort(chars);
            set.add(new String(chars));
        }
    }

    public boolean reorderedPowerOf2(int n) {
        if (set.isEmpty()) {
            buildSet(); // Call once
        }

        char[] chars = String.valueOf(n).toCharArray();
        Arrays.sort(chars);
        return set.contains(new String(chars));
    }
}