package com.circles24.JobsWorker.util;

public class StringSimilarity {

    public static int hammingDistance(String s1, String s2) {
        return hammingDistance(s1, s2, s1.length(), s2.length());
    }

    private static int hammingDistance(String s1, String s2, int n, int m) {
        // If first string is empty, the only option is to
        // insert all characters of second string into first
        if (m == 0)
            return n;

        // If second string is empty, the only option is to
        // remove all characters of first string
        if (n == 0)
            return m;

        // If last characters of two strings are same, nothing
        // much to do. Ignore last characters and get count for
        // remaining strings.
        if (s1.charAt(m-1) == s2.charAt(n-1))
            return hammingDistance(s1, s2, m - 1, n - 1);

        // If last characters are not same, consider all three
        // operations on last character of first string,
        // recursively compute minimum cost for all three
        // operations and take minimum of three values.
        return 1
                + Math.min(Math.min(hammingDistance(s1, s2, m, n - 1), // Insert
                hammingDistance(s1, s2, m - 1, n)), // Remove
                hammingDistance(s1, s2, m - 1,
                        n - 1) // Replace
        );
    }
}
