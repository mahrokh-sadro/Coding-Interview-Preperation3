// Given an array of integers nums, find the 
// maximum length of a subarray where the 
// product of all its elements is positive.

// A subarray of an array is a consecutive
// sequence of zero or more values taken out
// of that array.

// Return the maximum length of a subarray
// with positive product.

// Input: nums = [1,-2,-3,4]
// Output: 4
// Explanation: The array nums already has 
// a positive product of 24.

// Input: nums = [0,1,-2,-3,-4]
// Output: 3
// Explanation: The longest subarray with
// positive product is [1,-2,-3] which has
// a product of 6.
// Notice that we cannot include 0 in the 
// subarray since that'll make the product
// 0 which is not positive.

// Input: nums = [-1,-2,-3,0,1]
// Output: 2
// Explanation: The longest subarray with
// positive product is [-1,-2] or [-2,-3].

// 1 <= nums.length <= 10^5
// -10^9 <= nums[i] <= 10^9

public int getMaxLen(int[] nums) {
    int posLen = 0, negLen = 0, maxLen = 0;

    for (int num : nums) {
        if (num == 0) {
            posLen = negLen = 0; // reset on zero
        } else if (num > 0) {
            posLen++;
            negLen = (negLen > 0) ? negLen + 1 : 0;
        } else { // num < 0
            int temp = posLen;
            posLen = (negLen > 0) ? negLen + 1 : 0;
            negLen = temp + 1;
        }
        maxLen = Math.max(maxLen, posLen);
    }

    return maxLen;
}

