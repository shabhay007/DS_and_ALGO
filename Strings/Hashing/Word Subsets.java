// LeetCode Medium - 916


// Brute Force - Map
// T.C. - O((m * k) * (n * l)); k is avg length of words in words1, while l is for words2
// S.C. - O(26 * 26)
class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> list = new ArrayList<>();

        for(String str : words1){
            HashMap<Character, Integer> map1 = new HashMap<>();

            for(int i = 0; i<str.length(); i++){
                char ch = str.charAt(i);

                map1.put(ch, map1.getOrDefault(ch, 0) + 1);
            }

            boolean flag = true;
            for(String str2 : words2){
                HashMap<Character, Integer> map2 = new HashMap<>();

                for(int j = 0; j<str2.length(); j++){
                    char ch = str2.charAt(j);

                    map2.put(ch, map2.getOrDefault(ch, 0) + 1);
                }

                for(Map.Entry<Character, Integer> entry : map2.entrySet()){
                    char key = entry.getKey();
                    int value = entry.getValue();

                    if(map1.getOrDefault(key, 0) < value){
                        flag = false;
                        break;
                    }
                }

                if(!flag){
                    break;
                }
            }

            if(flag){
                list.add(str);
            }
        }

        return list;
    }
}




// Optimal - Map/Frequency array
// T.C. - O((n * l1) + (m * l2))
// S.C. - O(26) ~ O(1)
class Solution {
    // public boolean isSubset(int[] freq2, int[] freq1){
    //     for(int i = 0; i<26; i++){
    //         if(freq2[i] > freq1[i]){
    //             return false;
    //         }
    //     }

    //     return true;
    // }

    public List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> list = new ArrayList<>();

        int[] freq2 = new int[26];

        for(String str : words2){ // O(n * l1)
            int[] temp = new int[26];

            for(int i = 0; i<str.length(); i++){ // O(l); l is avg length
                char ch = str.charAt(i);
                temp[ch - 'a']++;

                freq2[ch - 'a'] = Math.max(freq2[ch - 'a'], temp[ch - 'a']);
            }
        }

        for(String str : words1){ // O(m * l2)
            int[] freq1 = new int[26];

            for(int i = 0; i<str.length(); i++){
                char ch = str.charAt(i);

                freq1[ch - 'a']++;
            }

            boolean flag = true;
            for(int j = 0; j<26; j++){
                if(freq2[j] > freq1[j]){
                    flag = false;
                    break;
                }
            }

            if(flag){
                list.add(str);
            }

            // OR

            // if(isSubset(freq2, freq1)){
            //     list.add(str);
            // }
        }

        return list;
    }
}