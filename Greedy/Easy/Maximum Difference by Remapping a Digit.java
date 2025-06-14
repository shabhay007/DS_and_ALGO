// LeetCode - 2566



// Approach 1 - String & char arr conversion and manipulation
// T.C. - O(4n)
// S.C. - O(log(num))
class Solution {
    public int minMaxDifference(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        char[] arr = str.toCharArray();

        // for min, replace 1st digit and its occurences with 0
        char first = arr[0];

        for(int i = 0; i<n; i++){ // finding the suitable digit to be replaced
            if(arr[i] != '0'){
                first = arr[i];
                break;
            }
        }

        for(int i = 0; i<n; i++){ // replacing the digit
            if(arr[i] == first){
                arr[i] = '0';
            }
        }

        String min = new String(arr);
        int minNum = Integer.parseInt(min);


        // for max, replace 1st digit and its occurences with 9
        arr = str.toCharArray();
        first = arr[0];

        for(int i = 0; i<n; i++){ // finding the suitable digit to be replaced
            if(arr[i] != '9'){
                first = arr[i];
                break;
            }
        }

        for(int i = 0; i<n; i++){ // replacing the digit
            if(arr[i] == first){
                arr[i] = '9';
            }
        }

        String max = new String(arr);
        int maxNum = Integer.parseInt(max);

        return maxNum - minNum;
    }
}




// Approach 2 - Java Collection Framework
// T.C. - O(log(num))
// S.C. - O(log(num))
class Solution {
    public int minMaxDifference(int num) {
        String str = String.valueOf(num);
        int n = str.length();

        // for min, replace 1st digit and its occurences with 0
        for(int i = 0; i<n; i++){ // finding the suitable digit to be replaced
            if(str.charAt(i) != '0'){
                str = str.replace(str.charAt(i), '0');
                break;
            }
        }

        int minNum = Integer.parseInt(str);


        // for max, replace 1st digit and its occurences with 9
        String str2 = String.valueOf(num);

        for(int i = 0; i<n; i++){ // finding the suitable digit to be replaced
            if(str2.charAt(i) != '9'){
                str2 = str2.replace(str2.charAt(i), '9');
                break;
            }
        }

        int maxNum = Integer.parseInt(str2);

        return maxNum - minNum;
    }
}