// LeetCode Medium - 2657



// Brute Force
// T.C. - O(n^3)
// S.C. - O(1);
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            int count = 0;

            for(int a_i = 0; a_i <= i; a_i++){
                for(int b_i = 0; b_i <= i; b_i++){
                    if(A[a_i] == B[b_i]){
                        count++;
                        break;
                    }
                }
            }

            result[i] = count;
        }

        return result;
    }
}




// Better 1 - Using Set
// T.C. - O(n^2)
// S.C. - O(n);
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];
        HashSet<Integer> set = new HashSet<>();

        for(int i = 0; i<n; i++){
            set.add(A[i]);

            int count = 0;

            for(int j = 0; j <= i; j++){
                if(set.contains(B[j])){
                    count++;
                }
            }

            result[i] = count;
        }

        return result;
    }
}




// Better 2 - Using boolean array
// T.C. - O(n^2)
// S.C. - O(2n);
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];

        // since element is from 1 to n and index starts from 0
        // so, to maintain consistency, taking size n+1
        boolean[] isPresentInATillI = new boolean[n+1];
        boolean[] isPresentInBTillI = new boolean[n+1];

        for(int i = 0; i<n; i++){
            isPresentInATillI[A[i]] = true;
            isPresentInBTillI[B[i]] = true;

            int count = 0;
            for(int j = 0; j<=n; j++){
                if(isPresentInATillI[j] == true && isPresentInBTillI[j] == true){
                    count++;
                }
            }

            result[i] = count;
        }

        return result;
    }
}




// Optimal - Using HashMap
// T.C. - O(n)
// S.C. - O(n);
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];

        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for(int i = 0; i<n; i++){
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
            if(map.get(A[i]) == 2) count++;

            map.put(B[i], map.getOrDefault(B[i], 0) + 1);
            if(map.get(B[i]) == 2) count++;

            result[i] = count;
        }

        return result;
    }
}



