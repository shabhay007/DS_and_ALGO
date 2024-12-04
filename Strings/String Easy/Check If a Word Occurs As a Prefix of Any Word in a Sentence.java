// LeetCode Easy - 1455


class Solution {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] arr = sentence.split(" ");
        int n = arr.length;
        int len = searchWord.length();

        for(int i = 0; i<n; i++){
            if(arr[i].length() >= len && arr[i].substring(0, len).equals(searchWord)){
                return i+1;
            }
        }

        return -1;
    }
}