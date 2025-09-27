// LeetCode - 812



// Approach 1 - Maths
// T.C. - O(n^3)
// S.C. - O(1)

// Not Recommended in java to use Heron's Formula because of so much lengthy
// calculations and validations
// Using Heron's Formula - sqrt(s * (s-a) * (s-b) * (s-c))
// s = semi perimeter
class Solution {
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double maxArea = 0.0;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                for(int k = j + 1; k < n; k++) {
                    int x1 = points[i][0], y1 = points[i][1];
                    int x2 = points[j][0], y2 = points[j][1];
                    int x3 = points[k][0], y3 = points[k][1];

                    double a = Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
                    double b = Math.sqrt((x3 - x2)*(x3 - x2) + (y3 - y2)*(y3 - y2));
                    double c = Math.sqrt((x3 - x1)*(x3 - x1) + (y3 - y1)*(y3 - y1));

                    double s = (a + b + c) / 2.0;
                    
                    // Heron's formula with validation
                    double areaSquared = s * (s - a) * (s - b) * (s - c);

                    // Ensure non-negative and finite value under sqrt
                    if (areaSquared >= 0 && Double.isFinite(areaSquared)) {
                        double area = Math.sqrt(areaSquared);
                        maxArea = Math.max(maxArea, area);
                    }
                }
            }
        }

        return maxArea;
    }
}





// Approach 2 - Maths
// T.C. - O(n^3)
// S.C. - O(1)

// Using ShoeLace Formula - sqrt(s * (s-a) * (s-b) * (s-c))
// s = semi perimeter
class Solution {
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double maxArea = 0.0;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                for(int k = j + 1; k < n; k++) {
                    int x1 = points[i][0], y1 = points[i][1];
                    int x2 = points[j][0], y2 = points[j][1];
                    int x3 = points[k][0], y3 = points[k][1];

                    double a = Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
                    double b = Math.sqrt((x3 - x2)*(x3 - x2) + (y3 - y2)*(y3 - y2));
                    double c = Math.sqrt((x3 - x1)*(x3 - x1) + (y3 - y1)*(y3 - y1));
                    
                    double shoelace = 0.5 * Math.abs((x1*(y2-y3) + x2*(y3-y1) + x3*(y1 - y2)));

                    maxArea = Math.max(maxArea, shoelace);
                }
            }
        }

        return maxArea;
    }
}