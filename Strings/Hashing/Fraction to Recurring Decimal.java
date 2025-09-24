// LeetCode - 166



// Approach 1 - Maths + Map + String
// T.C. - O(denominator)
// S.C. - O(denominator)
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0){
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        if((long) numerator * (long) denominator < 0){
            sb.append("-");
        }

        Long absNum = Math.abs((long) numerator);
        Long absDen = Math.abs((long) denominator);

        long result = absNum / absDen;

        sb.append(result);

        long remain = absNum % absDen;

        if(remain == 0){
            return sb.toString();
        }

        // in case of not decimal
        sb.append(".");

        Map<Long, Integer> map = new HashMap<>();

        // 0 <= remain < deno
        // O(denominator)
        while(remain != 0){
            if(map.containsKey(remain)){
                sb.insert(map.get(remain), "(");
                sb.append(")");
                break;
            }

            map.put(remain, sb.length());

            remain *= 10;

            long digit = remain / absDen;
            sb.append(digit);
            remain = remain % absDen;
        }

        return sb.toString();
    }
}