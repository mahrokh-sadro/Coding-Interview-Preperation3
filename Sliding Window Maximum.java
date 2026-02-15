// You are given an array of integers nums,
// there is a sliding window of size k
// which is moving from the very left of
// the array to the very right. You can
// only see the k numbers in the window. 
// Each time the sliding window moves 
// right by one position.
// Return the max sliding window.

// 1 <= nums.length <= 10^5
// -10^4 <= nums[i] <= 10^4
// 1 <= k <= nums.length

public int[] maxSlidingWindow(int[] nums, int k) {
    int n=nums.length;
    int[] array=new int[n-k+1];
    Deque<Integer> deque=new ArrayDeque<>();
    int j=0;
    for(int i=0;i<n;i++){
       while(!deque.isEmpty() && i-k+1>deque.peekFirst()){
          deque.removeFirst();
       }
       while(!deque.isEmpty() && nums[i]>nums[deque.peekLast()]){
          deque.removeLast();
       }
       deque.add(i);
       if(i>=k-1){
          array[j++]=nums[deque.peekFirst()];
       }
    }
    return array;
}
//time:O(N)
//space:o(k)