// You are given a 0-indexed integer array 
// nums, where nums[i] is a digit between
// 0 and 9 (inclusive).

// The triangular sum of nums is the value
// of the only element present in nums after
// the following process terminates:

// Let nums comprise of n elements. 
// If n == 1, end the process. Otherwise, 
// create a new 0-indexed integer array
// newNums of length n - 1.
// For each index i, where 0 <= i < n - 1,
// assign the value of newNums[i] as
// (nums[i] + nums[i+1]) % 10, where %
// denotes modulo operator.
// Replace the array nums with newNums.
// Repeat the entire process starting from step 1.
// Return the triangular sum of nums.
// [1,2,3,4,5]
// [3,5,7,9]
// [8,2,6]
// [0,8]
// [8]
public int triangularSum(int[] nums) {
    // 'size' represents how many elements are currently valid
    // in the current row of the triangle.
    // Initially the whole array is the first row.
    for (int size = nums.length; size > 1; size--) {
        // For each row we compute the next row using adjacent pairs.
        // Example:
        // [1,2,3,4,5] → [3,5,7,9]
        // new[i] = (nums[i] + nums[i+1]) % 10
        for (int i = 0; i < size - 1; i++) {

            // Update the current position with the sum of
            // itself and the next element modulo 10.
            // We overwrite nums[i] because its old value
            // will never be needed again.
            nums[i] = (nums[i] + nums[i + 1]) % 10;
        }
        // After this loop finishes:
        // the first (size - 1) elements represent the next row
        // of the triangle, and the last element can be ignored.
    }
    // When size becomes 1, nums[0] holds the final triangular sum.
    return nums[0];
}
//time:o(n^2)
//space:o(1)