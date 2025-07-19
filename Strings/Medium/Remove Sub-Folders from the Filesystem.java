// LeetCode - 1233



// Approach - Sorting
// T.C. - O(nlog(n) + n)
// S.C. - O(1)
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        int n = folder.length;
        Arrays.sort(folder);

        List<String> list = new ArrayList<>();
        list.add(folder[0]);

        for(int i = 1; i<n; i++){
            String currFolder = folder[i];
            String lastFolder = list.get(list.size() - 1);
            lastFolder += "/";

            if(!currFolder.startsWith(lastFolder)){
                list.add(currFolder);
            }
        }

        return list;
    }
}