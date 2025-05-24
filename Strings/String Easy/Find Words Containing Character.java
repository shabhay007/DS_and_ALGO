// LeetCode - 2942



// Approach 1 (Brute Force)
// T.C. - O(n.k); k = avg length of every words
// S.C. - O(1)
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        int n = words.length;
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i<n; i++){
            for(char ch : words[i].toCharArray()){
                if(ch == x){
                    list.add(i);
                    break;
                }
            }
        }

        return list;
    }
}




// Approach 2 (Super Brute Force) - Sorting + Binary Search
// T.C. - O(n * (k.log(k) + log(k))); k = avg length of every words
// S.C. - O(k)
class Solution {
    public boolean isPresent(String str, char x){ // O(klog(k) + log(k))
        char[] arr = str.toCharArray();
        Arrays.sort(arr);

        int l = 0;
        int r = str.length() - 1;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(arr[mid] == x){
                return true;
            }
            else if(arr[mid] > x){
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return false;
    }

    public List<Integer> findWordsContaining(String[] words, char x) {
        int n = words.length;
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i<n; i++){
            if(isPresent(words[i], x)){
                list.add(i);
            }
        }

        return list;
    }
}




// Approach 3 - Optimal
// T.C. - O(n.k); k = avg length of every words
// S.C. - O(1)
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        int n = words.length;
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i<n; i++){
            if(words[i].indexOf(x) != -1){
                list.add(i);
            }
        }

        return list;
    }
}




// Approach 4 - Optimal (Using Stream api & Lamda expression)
// T.C. - O(n.k); k = avg length of every words
// S.C. - O(1)
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        int n = words.length;
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i<n; i++){
            if(words[i].chars().anyMatch(ch -> ch == x)){
                list.add(i);
            }
        }

        return list;
    }
}