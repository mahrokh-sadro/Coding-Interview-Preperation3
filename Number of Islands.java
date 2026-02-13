// Given an m x n 2D binary grid grid which 
// represents a map of '1's (land) and 
// '0's (water), return the number of islands.
// An island is surrounded by water and is
// formed by connecting adjacent lands
// horizontally or vertically. You may 
// assume all four edges of the grid are
// all surrounded by water.

// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 300
// grid[i][j] is '0' or '1'.
class Solution {
    public int numIslands(char[][] grid) {
        int count=0;
        int m=grid.length;
        int n=grid[0].length;
        boolean[][] visited=new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1' && !visited[i][j]){
                    dfs(grid,i,j,visited);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid,int r,int c,boolean[][] visited){
        if(r<0 || r>=grid.length || c<0 || c>=grid[0].length || grid[r][c]!='1' || visited[r][c]){
            return ;
        }
        visited[r][c]=true;
        dfs(grid,r+1,c,visited);
        dfs(grid,r-1,c,visited);
        dfs(grid,r,c+1,visited);
        dfs(grid,r,c-1,visited);

    }
}
// time:O(M∗N)
// space:O(M∗N)

