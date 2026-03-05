# Search a 2D Matrix

> Time Limit: 2s
> Space Limit: 256 MB
> Link: https://leetcode.com/problems/search-a-2d-matrix/

## Description

You are given an $m \times n$ matrix where each row is sorted in non-decreasing order. The first integer of each row is greater than or equal to the last integer of the previous row. Given a target integer, determine if it exists in the matrix.

**Example**:
Input: `matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]]`, `target = 3`
Output: `true`
Explanation: The target 3 is present in the first row of the matrix.

**Constraints**:
- $m == \text{matrix.length}$
- $n == \text{matrix[i].length}$
- $1 \le m, n \le 100$
- $-10^4 \le \text{matrix[i][j]}, \text{target} \le 10^4$

**Code Template**:
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Your code here
    }
}
```

**Hint**: Treat the matrix as a flattened sorted array and perform binary search using coordinate mapping.

## Solution

<details>
<summary>Click to view the solution</summary>

**Code**:
```java
class Solution {
    // Stage 1: Direct Translation
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int left = 0;
        int right = m * n - 1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            // Map 1D index to 2D coordinates
            int r = mid / n;
            int c = mid % n;
            
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return false;
    }
}

class OptimizedSolution {
    // Stage 2: Optimized
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        
        while (left <= right) {
            // Prevents potential integer overflow compared to (left + right) / 2
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / n][mid % n];
            
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
}
```

> The optimized version guards against empty input arrays, which prevents runtime exceptions. It also uses `left + (right - left) / 2` to calculate the midpoint. This is a safer practice in Java because `(left + right) / 2` can overflow if `left` and `right` are large enough to sum past `Integer.MAX_VALUE`. While unlikely with the given constraints, it is the standard idiom for binary search.

**Approach**: Binary Search on Virtual 1D Array.

**Intuition**:
The matrix constraints effectively make it a sorted list that has been wrapped across multiple rows. If we could flatten the matrix, we would just run a standard binary search. Since the matrix is already sorted in a predictable way, we don't actually need to allocate a new array. We can virtually flatten it by mapping a 1D index directly to its corresponding row and column in the matrix.

**Mathematical/Other Foundation**:
For a matrix with $n$ columns, an element at virtual index $i$ maps to coordinates:
$$ \text{row} = \lfloor \frac{i}{n} \rfloor $$
$$ \text{col} = i \pmod n $$
Since we perform a binary search on a search space of size $m \times n$, the time complexity is $O(\log(m \cdot n))$. Given that $\log(m \cdot n) = \log m + \log n$, this is efficient.

**Algorithm**:
1. Initialize `left = 0` and `right = m * n - 1`.
2. Loop while `left <= right`.
3. Calculate `mid`.
4. Convert `mid` to 2D indices: `row = mid / n`, `col = mid % n`.
5. Compare `matrix[row][col]` with the target.
   - If equal, return `true`.
   - If less than target, discard the left half: `left = mid + 1`.
   - If greater than target, discard the right half: `right = mid - 1`.
6. If the loop terminates, the target is not present, so return `false`.

**Complexity**:
- Time: $O(\log(m \cdot n))$ because we halve the search space every iteration.
- Space: $O(1)$ as we only store a few integer variables.

**Test Cases**:

| Input | Output | Notes |
|-------|--------|-------|
| `[[1,3,5,7],[10,11,16,20],[23,30,34,60]], 3` | `true` | Target in first row |
| `[[1,3,5,7],[10,11,16,20],[23,30,34,60]], 13` | `false` | Target not found |
| `[[1]], 1` | `true` | Single element matrix |
| `[[]], 1` | `false` | Empty matrix (handled by optimized check) |

**Pro Tips**:
- Always check if the matrix or the first row is empty before accessing `matrix[0].length` to avoid `ArrayIndexOutOfBoundsException`.
- In languages with fixed-size integers like Java, use `left + (right - left) / 2` for midpoint calculation to prevent overflow errors on very large arrays.
</details>

## Solutions Link

- [[JAVA] Binary Search on Virtual 1D Array.](solutions/_04_SearchA2DMatrix_Solution01.java)
