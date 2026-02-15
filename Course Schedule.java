// There are a total of numCourses courses 
// you have to take, labeled from 0 to
// numCourses - 1. You are given an array
// prerequisites where prerequisites[i] = [ai, bi] 
// indicates that you must take course bi first 
// if you want to take course ai.
// For example, the pair [0, 1], indicates 
// that to take course 0 you have to first 
// take course 1.
// Return true if you can finish all courses.
// Otherwise, return false.

// 1 <= numCourses <= 2000
// 0 <= prerequisites.length <= 5000
// prerequisites[i].length == 2
// 0 <= ai, bi < numCourses
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph=new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            graph.add(new ArrayList<>());
        }

        for(int[] pre:prerequisites){
            graph.get(pre[1]).add(pre[0]);
        }

        int[] visited=new int[numCourses]; 
        for(int i=0;i<numCourses;i++){
           if(dfs(graph,i,visited)){
            return false;
           }
        }

        return true;
    }

    private boolean dfs(List<List<Integer>> graph,int node,int[] visited){
        if(visited[node]==1){
           return true;
        }

        if(visited[node]==2){
            return false;
        }

        visited[node]=1;
        for(var neighbor:graph.get(node)){
           if(dfs(graph,neighbor,visited)){
             return true;
           }
        }
        visited[node]=2;

        return false;

    }
}
//Time:O(V + E)
// V = number of courses
// E = number of prerequisites
// Each vertex is visited once.
// Each edge is explored once during DFS.
// The visited array prevents reprocessing nodes.
//Space:O(V + E)
// Adjacency list stores V vertices and E edges
// visited array → O(V)
// Recursion stack (worst case chain)