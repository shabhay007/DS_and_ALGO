// LeetCode Medium - 2471


// BFS, Map, Sorting
// S.C. - map, array in each level
class Solution {
    // O(levels * nlog(n) + n + n); n is the number of elements in level
    public int minSwapsToSort(int[] arr){
        int n = arr.length;
        int swap = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<n; i++){ // O(n)
            map.put(arr[i], i);
        }

        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr); // O(n * log(n))

        for(int i = 0; i<n; i++){ // O(n)
            if(arr[i] == sortedArr[i]){ // no swaps required
                continue;
            }

            int orgIdx = map.get(sortedArr[i]);
            map.put(arr[i], orgIdx);

            //swapping
            int temp = arr[i];
            arr[i] = arr[orgIdx];
            arr[orgIdx] = temp;

            // incrementing swap
            swap++;
        }

        return swap;
    }

    public int minimumOperations(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int minCount = 0;

        while(!q.isEmpty()){ // BFS - o(n)
            int currSize = q.size();
            int[] levelArr = new int[currSize];

            for(int i = 0; i<currSize; i++){
                TreeNode currNode = q.poll();
                levelArr[i] = currNode.val;

                if(currNode.left != null){
                    q.add(currNode.left);
                }

                if(currNode.right != null){
                    q.add(currNode.right);
                }

            }
            
            minCount += minSwapsToSort(levelArr);
        }

        return minCount;
    }
}