// LeetCode - 1432



// Approach 1 - Maths + Greedy
// T.C. - O(log(n) + log(n)); n = number of digits in num
// S.C. - O(log(n))
class Solution {
    public int maxDiff(int num) {
        String str = String.valueOf(num);
        int n = str.length();

        // for min
        if(str.charAt(0) != '1'){
            str = str.replace(str.charAt(0), '1');
        }
        else{
            for(int i = 1; i<n; i++){
                if(str.charAt(i) != '0' && str.charAt(i) != '1'){
                    str = str.replace(str.charAt(i), '0');
                    break;
                }
            }
        }

        int minNum = Integer.parseInt(str);
        // System.out.println(minNum);

        // for max
        String str2 = String.valueOf(num);

        for(int i = 0; i<n; i++){
            if(str2.charAt(i) != '9'){
                str2 = str2.replace(str2.charAt(i), '9');
                break;
            }
        }

        int maxNum = Integer.parseInt(str2);
        // System.out.println(maxNum);

        return maxNum - minNum;
    }
}





// Approach 2 - Maths + Greedy
// T.C. - O(log(n) + log(n)); n = number of digits in num
// S.C. - O(log(n))
class Solution {
    public int maxDiff(int num) {
        String str = String.valueOf(num);
        int n = str.length();

        // for min
        for(int i = 0; i<n; i++){
            if(i == 0 && str.charAt(i) != '1'){
                str = str.replace(str.charAt(i), '1');
                break;
            }
            else if(str.charAt(i) != '0' && str.charAt(i) != str.charAt(0)){
                str = str.replace(str.charAt(i), '0');
                break;
            }
        }

        int minNum = Integer.parseInt(str);
        // System.out.println(minNum);

        // for max
        String str2 = String.valueOf(num);

        for(int i = 0; i<n; i++){
            if(str2.charAt(i) != '9'){
                str2 = str2.replace(str2.charAt(i), '9');
                break;
            }
        }

        int maxNum = Integer.parseInt(str2);
        // System.out.println(maxNum);

        return maxNum - minNum;
    }
}