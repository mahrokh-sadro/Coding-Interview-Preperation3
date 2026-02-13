// You are given an m x n grid where 
// each cell can have one of three values:
// 0 representing an empty cell,
// 1 representing a fresh orange, or
// 2 representing a rotten orange.
// Every minute, any fresh orange that
// is 4-directionally adjacent to a 
// rotten orange becomes rotten.

// Return the minimum number of minutes
// that must elapse until no cell has a
// fresh orange. If this is impossible,
// return -1.

// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 10
// grid[i][j] is 0, 1, or 2.

class Solution {
    public int orangesRotting(int[][] grid) {
        
        Queue<int[]> queue=new LinkedList<>();
        int freshCount=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                   freshCount++;
                }
                else if(grid[i][j]==2){
                   queue.add(new int[]{i,j});
                }
            }
        }

        if(freshCount==0) {
            return 0;
        }

        int min=0;
        int[][] directions={{1,0},{0,1},{0,-1},{-1,0}};
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                int[] node=queue.poll();
                for(int[] dir:directions){
                    int x=node[0]+dir[0];
                    int y=node[1]+dir[1];
                    if(x>=0 && x<grid.length && y>=0 && y<grid[0].length && grid[x][y]==1){
                    freshCount--;
                    grid[x][y]=2;
                    queue.add(new int[]{x,y});
                    }
                }
            }
            min++;
        }

        return freshCount==0? min-1: -1;
    }
}
// time:O(m × n)
// space:O(m × n)