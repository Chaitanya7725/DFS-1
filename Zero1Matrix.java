import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// TC: O(m * n) in case of worst case reaching out to all elements. Fetching Dirs array is constant
// SC: O(m * n), the code updates the mat 2d matrix in place but queue is used to store the zero valued elements. 
// Dirs array don't contribute to space as it has constant length
public class Zero1Matrix {
    public static void main(String[] args) {
        for (int[] array : updateMatrix(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } })) {
            System.out.println(Arrays.toString(array));
        }
        System.out.println();
        for (int[] array : updateMatrix(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 1, 1 } })) {
            System.out.println(Arrays.toString(array));
        }
    }

    public static int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0)
            return mat;
        Queue<int[]> queue = new LinkedList<>();
        int m = mat.length;
        int n = mat[0].length;
        int[][] dirs = new int[][] { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } }; // U D L R
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0)
                    queue.offer(new int[] { i, j });
                if (mat[i][j] == 1)
                    mat[i][j] *= -1;
            }
        }
        int level = 0;
        while (!queue.isEmpty()) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];
                for (int[] dir : dirs) {
                    int nr = row + dir[0];
                    int nc = col + dir[1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && mat[nr][nc] == -1) {
                        queue.offer(new int[] { nr, nc });
                        mat[nr][nc] = level + 1;
                    }
                }
            }
            level += 1;
        }
        return mat;
    }
}
