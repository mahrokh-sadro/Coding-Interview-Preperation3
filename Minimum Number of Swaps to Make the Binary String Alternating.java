// Given a binary string s, return the
// minimum number of character swaps to
// make it alternating, or -1 if it is impossible.

// The string is called alternating if no two
// adjacent characters are equal. For example,
// the strings "010" and "1010" are alternating,
// while the string "0100" is not.

// Any two characters may be swapped, even if
// they are not adjacent.

// Input: s = "111000"
// Output: 1
// Explanation: Swap positions 1 and 4: "111000" -> "101010"
// The string is now alternating.

// Input: s = "010"
// Output: 0
// Explanation: The string is already alternating,
// no swaps are needed.

// Input: s = "1110"
// Output: -1

// 1 <= s.length <= 1000
// s[i] is either '0' or '1'.
public int minSwaps(String s) {
    int n = s.length();
    int ones = 0;

    for (char c : s.toCharArray()) {
        if (c == '1') ones++;
    }

    int zeros = n - ones;

    if (Math.abs(zeros - ones) > 1) return -1;

    if (n % 2 == 0) {
        return Math.min(calc(s, '0'), calc(s, '1'));
    }

    if (zeros > ones) return calc(s, '0');
    else return calc(s, '1');
}

private int calc(String s, char start) {
    int mismatch = 0;
    char expected = start;

    for (char c : s.toCharArray()) {
        if (c != expected) mismatch++;
        expected = expected == '0' ? '1' : '0';
    }

    return mismatch / 2;
}
//time:o(n)
//space:o(1)