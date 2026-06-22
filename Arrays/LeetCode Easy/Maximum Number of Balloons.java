// LeetCode 1189



// Approach 1 - Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maxNumberOfBalloons(String text) {
        int len = text.length();
        int a = 0;
        int b = 0;
        int l = 0;
        int o = 0;
        int n = 0;

        for(int i = 0; i<len; i++){
            char ch = text.charAt(i);

            if(ch == 'a'){
                a++;
            }
            else if(ch == 'b'){
                b++;
            }
            else if(ch == 'l'){
                l++;
            }
            else if(ch == 'o'){
                o++;
            }
            else if(ch == 'n'){
                n++;
            }
        }

        // now processing freq
        int one = Math.min(a, Math.min(b, n));
        int two = Math.min(l, o);

        while(one > 0){
            if(two >= one * 2){
                return one;
            }

            one--;
        }

        return 0;
    }
}