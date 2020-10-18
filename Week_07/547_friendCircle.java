class Solution {
    public int findCircleNum(int[][] M) {
       /**
         使用一个数组
       */
       boolean[] isFriend = new boolean[M.length];
       int ret = 0;
       for(int i = 0; i<M.length;i++) {
           if(!isFriend[i]) {
              dfs(M,isFriend,i);
              ret++;
           }
       }
       return ret;
    }

     private void dfs(int[][] m, boolean[] isFriend, int i) {
        for(int j = 0; j < m.length; j++) {
            if(m[i][j] == 1 && !isFriend[j]) {
                isFriend[j] = true;
                dfs(m, isFriend, j);
            }
        }
    }
}