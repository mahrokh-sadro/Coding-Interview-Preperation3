// You are given a 0-indexed integer array
// nums.
// Swaps of adjacent elements are able to
// be performed on nums.
// A valid array meets the following conditions:
// The largest element (any of the largest
// elements if there are multiple) is at
// the rightmost position in the array.
// The smallest element (any of the
// smallest elements if there are multiple)
// is at the leftmost position in the array.
// Return the minimum swaps required to 
// make nums a valid array.

// 1 <= nums.length <= 10^5
// 1 <= nums[i] <= 10^5
class Solution {
    public int minimumSwaps(int[] nums) {
        int n = nums.length;

// We want:
// 1) The smallest element at index 0
// 2) The largest element at index n-1
//
// Since we can only swap adjacent elements,
// the number of swaps needed equals the distance
// each element must travel.

        int minIndex = 0;  // index of leftmost minimum
        int maxIndex = 0;  // index of rightmost maximum

// Find:
// - The first occurrence of the minimum value
// - The last occurrence of the maximum value
//
// For max, we use >= so we keep updating it,
// ensuring we store the RIGHTMOST maximum.
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] >= nums[maxIndex]) { 
                maxIndex = i;
            }
        }

// Number of swaps to move minimum to index 0:
// It must move left minIndex positions.
//
// Number of swaps to move maximum to index n-1:
// It must move right (n - 1 - maxIndex) positions.
        int steps = minIndex + (n - 1 - maxIndex);

// Important adjustment:
//
// If minIndex > maxIndex,
// that means the minimum is originally to the RIGHT of the maximum.
//
// When we move the minimum to the front,
// it passes over the maximum,
// which shifts the maximum one position to the right automatically.
//
// So we overcounted by 1 swap and must subtract 1.
        if (minIndex > maxIndex) {
            steps--;
        }

        return steps;
    }
}
