// Given an array of points where 
// points[i] = [xi, yi] represents a 
// point on the X-Y plane and an integer k,
// return the k closest points to the origin (0, 0).
// The distance between two points on 
// the X-Y plane is the Euclidean distance
// (i.e., √(x1 - x2)2 + (y1 - y2)2).

// You may return the answer in any order. 
// The answer is guaranteed to be unique
// (except for the order that it is in).

// 1 <= k <= points.length <= 10^4
// -10^4 <= xi, yi <= 10^4
class Solution {
    public int[][] kClosest(int[][] points, int k) {
       PriorityQueue<int[]> heap = new PriorityQueue<>(
            (a, b) -> Integer.compare(
                b[0]*b[0] + b[1]*b[1],
                a[0]*a[0] + a[1]*a[1]
            )
        );

        for(int[] p:points){
           heap.add(p);

           if(heap.size()>k){
             heap.poll();
           }
        }

        int[][] array=new int[heap.size()][2];
        int j=0;
        while(!heap.isEmpty()){
            array[j++]=heap.poll();
        }

        return array;
    }
}
//Time O(n log k)
//Space O(k)