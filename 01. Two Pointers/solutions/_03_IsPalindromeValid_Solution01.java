/**
 * @file _03_IsPalindromeValid_Solution01.java
 *
 * @problem Is Palindrome Valid
 * @statement Given a string that may contain letters, digits, spaces, and
 * punctuation, decide if it reads the same forward and backward after stripping
 * all non-alphanumeric characters. The comparison is case-insensitive.
 *
 * @approach Two Pointers (Inward Traversal)
 *
 * @author ankitsamaddar
 * @date 2026-03-21
 *
 * Compile & Run: `javac _03_IsPalindromeValid_Solution01.java && Get-Content input.txt | java _03_IsPalindromeValid_Solution01 && rm -force _03_IsPalindromeValid_Solution01.class && rm -force Solution.class`
 *
 */

import java.io.*;
import java.util.*;

/* ── Solution ─────────────────────────────────────────────── */

class Solution {
  boolean isValidPalindrome(String s) {
    int lo = 0, hi = s.length() - 1;
    while (lo < hi) {
      while (lo < hi && !Character.isLetterOrDigit(s.charAt(lo)))
        lo++;
      while (lo < hi && !Character.isLetterOrDigit(s.charAt(hi)))
        hi--;
      if (Character.toLowerCase(s.charAt(lo)) !=
          Character.toLowerCase(s.charAt(hi)))
        return false;
      lo++;
      hi--;
    }
    return true;
  }
}

/* ── Driver ───────────────────────────────────────────────── */

class _03_IsPalindromeValid_Solution01 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine().trim());
    while (t-- > 0) {
      String str = br.readLine().trim();
      Solution obj = new Solution();
      System.out.println(obj.isValidPalindrome(str));
    }
  }
}
