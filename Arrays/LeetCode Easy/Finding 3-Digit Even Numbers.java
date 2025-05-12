// LeetCode - 2094



// Brute Force
// T.C. - O(n^3 + k.logk); k = total no. of Integers formed
// S.C. - O(k)
class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int n = digits.length;
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                for(int k = 0; k<n; k++){
                    if(i == j || j == k || i == k){
                        continue;
                    }

                    if(digits[i] != 0 && digits[k] % 2 == 0){
                        set.add((digits[i] * 100) + (digits[j] * 10) + digits[k]);
                    }
                }
            }
        }

        int[] result = new int[set.size()];
        int i = 0;

        for(int num : set){
            result[i] = num;
            i++;
        }

        Arrays.sort(result);

        return result;
    }
}





// Optimal
// T.C. - O(n + 1 + k); k = total no. of Integers formed
// S.C. - O(k + 1)
class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int n = digits.length;
        List<Integer> list = new ArrayList<>();
        int[] freq = new int[10]; // O(1)

        for(int digit : digits){ // n
            freq[digit]++;
        }

        // O(9 * 10 * 4) ~ O(1)
        for(int i = 1; i <= 9; i++){ // O(9)
            if(freq[i] == 0) continue;
            freq[i]--;

            for(int j = 0; j <= 9; j++){ // O(10)
                if(freq[j] == 0) continue;
                freq[j]--;

                for(int k = 0; k <= 8; k+=2){ // O(4)
                    if(freq[k] == 0) continue;
                    freq[k]--;

                    list.add((i * 100) + (j * 10) + k);
                    freq[k]++;
                }

                freq[j]++;
            }

            freq[i]++;
        }

        // O(k)
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}