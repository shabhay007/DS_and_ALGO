// LeetCode Medium - 1792


// Approach 1 - Gives TLE
// T.C. - O(n + extraStudents * n)
// S.C. - O(n)
class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;
        double[] passRatio = new double[n];

        for(int i = 0; i<n; i++){
            double ratio = (double) classes[i][0]/classes[i][1];
            passRatio[i] = ratio;
        }

        while(extraStudents != 0){
            double[] updatedPassRatio = new double[n];

            for(int i = 0; i<n; i++){
                double updatedRatio = (double) (classes[i][0] + 1) / (classes[i][1] + 1);
                updatedPassRatio[i] = updatedRatio;
            }

            int bestClassIdx = 0;
            double bestDelta = 0; // highest diff from prev to curr pass ratio

            for(int i = 0; i<n; i++){
                double delta = updatedPassRatio[i] - passRatio[i];

                if(delta > bestDelta){
                    bestDelta = delta;
                    bestClassIdx = i;
                }
            }

            passRatio[bestClassIdx] = updatedPassRatio[bestClassIdx];
            classes[bestClassIdx][0]++;
            classes[bestClassIdx][1]++;

            extraStudents--;
        }

        double result = 0;
        for(int i = 0; i<n; i++){
            result += passRatio[i];
        }

        return (double) result/n;
    }
}



// Approach 2 - Optimal
// T.C - O(n + extraStudents * log(n))
// S.C - O(n)
class Solution {
    public class Pair {
        double maxDelta;
        int maxDeltaClassIdx;

        public Pair(double delta, int idx){
            this.maxDelta = delta;
            this.maxDeltaClassIdx = idx;
        }
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;
        double[] passRatio = new double[n];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Double.compare(b.maxDelta, a.maxDelta));

        for(int i = 0; i<n; i++){
            double currPassRatio = (double) classes[i][0]/classes[i][1];
            double newPassRatio = (double) (classes[i][0] + 1)/(classes[i][1] + 1);
            double delta = newPassRatio - currPassRatio;
            pq.offer(new Pair(delta, i));
        }

        // O(extraStudents * log(n))
        while(extraStudents != 0){
            Pair p = pq.poll(); // O(log(n))
            double maxD = p.maxDelta;
            int idx = p.maxDeltaClassIdx;
            
            // incrementing total passing students in that class
            classes[idx][0]++;
            // incrementing total students in that class
            classes[idx][1]++;


            // for next students
            double currPassRatio = (double) classes[idx][0]/classes[idx][1];
            double newPassRatio = (double) (classes[idx][0] + 1)/(classes[idx][1] + 1);
            double delta = newPassRatio - currPassRatio;
            pq.offer(new Pair(delta, idx)); // O(log(n));

            extraStudents--;
        }

        double result = 0.0;
        for(int i = 0; i<n; i++){
            result += (double) classes[i][0]/classes[i][1];
        }

        return result/n;
    }
}