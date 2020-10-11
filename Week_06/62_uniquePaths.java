class Solution {

    public static void main(String[] args) {
         
         int m = 7;
         int n = 3;
         int result = uniquePaths(7,3);
         System.out.println(result);
    }

//机器人一定会走m+n-2步，即从m+n-2中挑出m-1步向下走不就行了吗？即C（（m+n-2），（m-1））。
    public int uniquePaths(int m, int n) {
         int[][] dp  =new int[m][n];
         for(int i = 0; i<m;i++) {
             for(int j = 0; j < n; j++) {
                 if(i == 0 || j == 0) {
                    dp[i][j] = 1;
                 } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                 } 
             }
         }
         return dp[m-1][n-1];
    }
}