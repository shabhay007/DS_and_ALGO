// LeetCode Hard - 2071



// Approach - Binary Search on Answers
// T.C. - O(n.log(n) + m.log(m) + log(k) * k.log(k)); k = Min(n, m)
// S.C. - O(k)
class Solution {
    public boolean check(int[] tasks, int[] workers, int pills, int strength, int mid){
        int p = pills;

        // choosing best worker to assign toughest task
        TreeMap<Integer, Integer> ws = new TreeMap<>();

        for (int i = workers.length - mid; i < workers.length; ++i) {
            ws.put(workers[i], ws.getOrDefault(workers[i], 0) + 1);
        }

        for (int i = mid - 1; i >= 0; --i) {
            Integer key = ws.lastKey();

            if (key >= tasks[i]) {
                ws.put(key, ws.get(key) - 1);

                if (ws.get(key) == 0) {
                    ws.remove(key);
                }
            } 
            else {
                if (p == 0) {
                    return false;
                }

                key = ws.ceilingKey(tasks[i] - strength);

                if (key == null) {
                    return false;
                }

                ws.put(key, ws.get(key) - 1);

                if (ws.get(key) == 0) {
                    ws.remove(key);
                }

                --p;
            }
        }

        return true;
    }

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        int n = tasks.length;
        int m = workers.length;

        Arrays.sort(tasks); // we'll get tasks which requires less power
        Arrays.sort(workers); // to get powerfull workers first

        int l = 0;
        int r = Math.min(n, m);
        int result = 0;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(check(tasks, workers, pills, strength, mid)){
                result = mid;
                l = mid + 1;
            }
            else{
                r = mid - 1;
            }
        }

        return result;
    }
}