# Is Palindrome Valid

> Time Limit: 1sec
> Space Limit: 256MB
> Difficulty: Easy
> Link: [LeetCode 125 – Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)

## Description

Given a string that may contain letters, digits, spaces, and punctuation, decide if it reads the same forward and backward after stripping all non-alphanumeric characters. The comparison is case-insensitive.

**Example**:

```
Input:  s = "a dog! a panic in a pagoda."
Output: true
```

```
Input:  s = "abc123"
Output: false
```

**Input Format**:
A single string `s` containing lowercase or uppercase letters, digits, spaces, and punctuation.

**Output Format**:
`true` if the cleaned string is a palindrome, `false` otherwise.

**Constraints**:
- Non-alphanumeric characters are ignored
- Comparison is case-insensitive
- An empty string or a string of only punctuation counts as a palindrome

**Code Template**:
```java
class Solution {
    public boolean isPalindrome(String s) {
        // your code here
    }
}
```

**Hint**: Place one pointer at each end. Skip non-alphanumeric characters, compare what's left, and move inward.

## Solution

<details>
<summary>Click to view the solution</summary>

**Code**:
```java
public boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;

    while (left < right) {
        // Skip non-alphanumeric characters from the left
        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
            left++;
        }

        // Skip non-alphanumeric characters from the right
        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
            right--;
        }

        // Characters don't match -- not a palindrome
        if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
            return false;
        }

        left++;
        right--;
    }

    return true;
}
```

> An alternative pre-processes the string first and then does a straightforward two-pointer pass. More readable, but costs $O(n)$ extra space:

```java
public boolean isPalindromeClean(String s) {
    String cleaned = s.toLowerCase().replaceAll("[^a-z0-9]", "");
    int left = 0, right = cleaned.length() - 1;
    while (left < right) {
        if (cleaned.charAt(left) != cleaned.charAt(right)) return false;
        left++;
        right--;
    }
    return true;
}
```

> The direct version is the better interview answer. It operates on the original string without allocating a cleaned copy. The pre-processing version is easier to read at the cost of that $O(n)$ allocation. Worth asking the interviewer which they prefer.

**Approach**: Two Pointers (Inward Traversal)

**Intuition**: A palindrome mirrors itself around its center. The character at the start must equal the one at the end, the second must equal the second-to-last, and so on. Two pointers starting at opposite ends check these pairs naturally. When a non-alphanumeric character shows up, we skip it rather than stopping. The loop condition `left < right` handles both even-length strings (pointers cross without meeting) and odd-length strings (pointers meet at the middle character, which needs no comparison partner).

**Mathematical/Other Foundation**:

Even though there are nested `while` loops, each index is visited at most once. Across the entire execution, `left` advances at most $n$ times total and `right` retreats at most $n$ times total. The inner skip loops don't restart from the beginning — they pick up where the outer loop left off.

Let $k$ be the number of alphanumeric characters. The outer loop makes at most $\lfloor k/2 \rfloor$ comparisons, and all skip steps combined visit at most $n-k$ positions. Total work is $\lfloor k/2 \rfloor+(n-k) \leq n$, so time complexity is $O(n)$.

**Algorithm**:
1. Set `left = 0`, `right = s.length() - 1`.
2. While `left < right`:
   - Advance `left` forward past non-alphanumeric characters (inner condition: `left < right`).
   - Retreat `right` backward past non-alphanumeric characters (inner condition: `left < right`).
   - If `s.charAt(left)` and `s.charAt(right)` differ case-insensitively, return `false`.
   - Move both pointers inward: `left++`, `right--`.
3. Return `true`.

**Complexity**:
- Time: $O(n)$ — each character is visited at most once across all loop iterations.
- Space: $O(1)$ — only two integer pointers; no auxiliary strings or arrays created.

**Test Cases**:

| Input | Output | Notes |
|-------|--------|-------|
| `s = ""` | `true` | Empty string |
| `s = "a"` | `true` | Single character |
| `s = "aa"` | `true` | Two-character palindrome |
| `s = "ab"` | `false` | Two-character non-palindrome |
| `s = "!,(?)"` | `true` | Only non-alphanumeric — cleaned string is empty |
| `s = "12.02.2021"` | `true` | Digits with punctuation |
| `s = "21.02.2021"` | `false` | Non-palindrome with digits and punctuation |
| `s = "hello, world!"` | `false` | Non-palindrome with punctuation |

**Pro Tips**:
- Before coding, clarify: are non-alphanumeric characters ignored? Is the comparison case-sensitive? What about an empty string? These questions take seconds and show the interviewer you think through the problem before writing.
- `Character.isLetterOrDigit()` and `Character.toLowerCase()` are almost always acceptable in interviews, but a quick "Is it okay to use these built-ins?" goes a long way. If they say no, remember: `isLetterOrDigit(c)` is `(c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')`, and `toLowerCase(c)` is `(c >= 'A' && c <= 'Z') ? (char)(c + 32) : c`.
</details>

## Solutions Link

- [[JAVA] Two Pointers (Inward Traversal)](solutions/_03_IsPalindromeValid_Solution01.java)
