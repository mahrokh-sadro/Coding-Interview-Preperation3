// Given an integer array nums, design an 
// algorithm to randomly shuffle the array.
// All permutations of the array should be
// equally likely as a result of the shuffling.
// Implement the Solution class:

// Solution(int[] nums) Initializes the 
// object with the integer array nums.
// int[] reset() Resets the array to its 
// original configuration and returns it.
// int[] shuffle() Returns a random shuffling
// of the array.

// Input
// ["Solution", "shuffle", "reset", "shuffle"]
// [[[1, 2, 3]], [], [], []]
// Output
// [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]

// 1 <= nums.length <= 50
// -106 <= nums[i] <= 106
// All the elements of nums are unique.
// At most 104 calls in total will be made
// to reset and shuffle.

class Solution {
    
    int[] original;
    int size;
    public Solution(int[] nums) {
        original=nums.clone();
        size=nums.length;
    }
    
    public int[] reset() {
        // Since arrays are mutable in Java, returning
        // the original array would expose internal
        // state. Returning a clone ensures defensive
        // copying and protects the integrity of the class.
        return original.clone();
    }
    
    public int[] shuffle() {
        int[] nums=original.clone();
        // We start from the end so that after placing
        //  a random element at position i, we never 
        //  modify it again. This ensures each element 
        //  is placed exactly once and guarantees uniform 
        //  randomness.
        //When i == 0 ---> j = random(0, 0)
        for(int i=size-1;i>0;i--){
            // We use (i + 1) because we need a random 
            // index from 0 to i inclusive. Since Math.random()
            // is exclusive of 1, multiplying by (i + 1)
            // ensures index i can also be selected.
            int j=(int)(Math.random() * (i+1));

            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
        }
        return nums;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
// time:O(N)
// space:O(N)






