// LeetCode - 1948



// Approach (Using trie)
// T.C : O(N * L * ClogC), N = total Paths, L = average length of each path, 
// C is the average number of children per node
// S.C : ~O(N * L), we store all the paths in the trie, approximated value.

import java.util.AbstractMap;

class Solution {
    
    static class Node {
        String val;
        String subFolder;
        Map<String, Node> children;

        Node(String val) {
            this.val = val;
            this.subFolder = "";
            this.children = new HashMap<>();
        }
    }

    private Node getNode(String val) {
        return new Node(val);
    }

    private void insert(Node root, List<String> path) {
        for (String folder : path) {
            root.children.putIfAbsent(folder, getNode(folder));
            root = root.children.get(folder);
        }
    }

    private String populateNodes(Node root, Map<String, Integer> subFolderMap) {
        List<Map.Entry<String, String>> subFolderPaths = new ArrayList<>();

        for (Map.Entry<String, Node> entry : root.children.entrySet()) {
            String subFolderResult = populateNodes(entry.getValue(), subFolderMap);
            subFolderPaths.add(new AbstractMap.SimpleEntry<>(entry.getKey(), subFolderResult));
        }

        subFolderPaths.sort(Comparator.comparing(Map.Entry::getKey));

        StringBuilder completePath = new StringBuilder();

        for (Map.Entry<String, String> entry : subFolderPaths) {
            completePath.append("(").append(entry.getKey()).append(entry.getValue()).append(")");
        }

        root.subFolder = completePath.toString();

        if (!completePath.toString().isEmpty()) {
            subFolderMap.put(completePath.toString(), subFolderMap.getOrDefault(completePath.toString(), 0) + 1);
        }

        return completePath.toString();
    }

    private void removeDuplicates(Node root, Map<String, Integer> subFolderMap) {
        Iterator<Map.Entry<String, Node>> it = root.children.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, Node> entry = it.next();
            Node child = entry.getValue();

            if (!child.subFolder.isEmpty() && subFolderMap.get(child.subFolder) > 1) {
                it.remove();
            } else {
                removeDuplicates(child, subFolderMap);
            }
        }
    }

    private void constructResult(Node root, List<String> path, List<List<String>> result) {
        for (Map.Entry<String, Node> entry : root.children.entrySet()) {
            path.add(entry.getKey());
            result.add(new ArrayList<>(path));
            constructResult(entry.getValue(), path, result);
            path.remove(path.size() - 1);
        }
    }

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Node root = getNode("/");

        // Construct trie
        for (List<String> path : paths) {
            insert(root, path);
        }

        Map<String, Integer> subFolderMap = new HashMap<>();
        populateNodes(root, subFolderMap);

        removeDuplicates(root, subFolderMap);

        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        constructResult(root, path, result);

        return result;
    }
}