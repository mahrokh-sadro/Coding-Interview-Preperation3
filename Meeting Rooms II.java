// Given an array of meeting time 
// intervals intervals where
// intervals[i] = [starti, endi],
// return the minimum number of
// conference rooms required.

// Input: intervals = [[0,30],[5,10],[15,20]]
// Output: 2
// Example 2:

// Input: intervals = [[7,10],[2,4]]
// Output: 1
// We sort meetings by start time to process
// them chronologically. For each meeting,
// we check if the earliest ending meeting
// has already finished. If so, we reuse 
// that room; otherwise, we allocate a new
// one. The maximum number of simultaneous
// meetings determines the number of rooms
// needed.
 class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int[] interval : intervals) {
        // Free room if earliest meeting finished
            if (!heap.isEmpty() && heap.peek() <= interval[0]) {
                heap.poll();
            }

        // Allocate room (add current meeting's end time)
            heap.add(interval[1]);
        }

        return heap.size();
    }
}


// 1 <= intervals.length <= 10^4
// 0 <= starti < endi <= 10^6