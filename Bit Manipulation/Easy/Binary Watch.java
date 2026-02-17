// LeetCode - 401



// Approach 1 - Brute Force + Bit Manipulation
// T.C. - O(12 * 60) ~ O(1)
// S.C. - O(1)
class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> list = new ArrayList<>();

        for(int h = 0; h < 12; h++){
            for(int min = 0; min < 60; min++){
                if(Integer.bitCount(h) + Integer.bitCount(min) == turnedOn){
                    String minute = (min < 10 ? "0" : "") + min;
                    list.add(h + ":" + minute);
                }
            }
        }

        return list;
    }
}