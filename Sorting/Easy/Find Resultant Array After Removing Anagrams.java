// LeetCode - 2273




// Approach 1 - Sorting
// T.C. - O(n * klog(k)); k = avg length of each word
// S.C. - O(k)
class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> list = new ArrayList<>(Arrays.asList(words));

        for(int i = list.size() - 1; i > 0; i--){
            char[] arr1 = list.get(i).toCharArray();
            Arrays.sort(arr1);
            
            char[] arr2 = list.get(i-1).toCharArray();
            Arrays.sort(arr2);

            if(Arrays.equals(arr1, arr2)){
                list.remove(i);
            }
        }

        return list;
    }
}





// Approach 2 - Frequency Array
// T.C. - O(n * k); k = avg length of each word
// S.C. - O(1)
class Solution {
    public boolean isAnagram(String str1, String str2){
        int[] freq = new int[26];

        for(int i = 0; i<str1.length(); i++){
            char ch = str1.charAt(i);
            freq[ch - 'a']++;
        }

        for(int i = 0; i<str2.length(); i++){
            char ch = str2.charAt(i);
            freq[ch - 'a']--;
        }

        for(int i = 0; i<26; i++){
            if(freq[i] != 0){
                return false;
            }
        }

        return true;
    }

    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        List<String> list = new ArrayList<>();
        list.add(words[0]);

        for(int i = 0; i<n; i++){
            if(!isAnagram(words[i], list.get(list.size() - 1))){
                list.add(words[i]);
            }
        }

        return list;
    }
}