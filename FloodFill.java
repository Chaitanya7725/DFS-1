import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// TC: O(n), in the worst case, all the elements will be traversed.
// SC: O(n), Queues are used to store the adjacent elements which satisfies the criteria.
// In the worst case, it will store all the elements. The code can be optimized little by using single queue.
public class FloodFill {

    public static void main(String[] args) {
        for (int[] array : floodFill(new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } }, 1, 1, 2)) {
            System.out.println(Arrays.toString(array));
        }

        for (int[] array : floodFill(new int[][] { { 0, 0, 0 }, { 0, 0, 0 } }, 0, 0, 0)) {
            System.out.println(Arrays.toString(array));
        }
        System.out.println();
        for (int[] array : floodFillWithSingleQueue(new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } }, 1, 1, 2)) {
            System.out.println(Arrays.toString(array));
        }

        for (int[] array : floodFillWithSingleQueue(new int[][] { { 0, 0, 0 }, { 0, 0, 0 } }, 0, 0, 0)) {
            System.out.println(Arrays.toString(array));
        }
    }

    private static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image == null || image.length == 0 || image[sr][sc] == color)
            return image;
        int m = image.length;
        int n = image[0].length;
        int oldColor = image[sr][sc];
        Queue<Integer> row = new LinkedList<>();
        Queue<Integer> col = new LinkedList<>();
        int[][] dirs = new int[][] { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } }; // U D L R
        row.offer(sr);
        col.offer(sc);
        while (!row.isEmpty()) {
            int currRow = row.poll();
            int currCol = col.poll();
            for (int[] dir : dirs) {
                int nr = currRow + dir[0];
                int nc = currCol + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && image[nr][nc] == oldColor) {
                    image[nr][nc] = color;
                    row.offer(nr);
                    col.offer(nc);
                }
            }
        }
        return image;
    }

    private static int[][] floodFillWithSingleQueue(int[][] image, int sr, int sc, int color) {
        if (image == null || image.length == 0 || image[sr][sc] == color)
            return image;
        int m = image.length;
        int n = image[0].length;
        int oldColor = image[sr][sc];
        Queue<int[]> row = new LinkedList<>();
        int[][] dirs = new int[][] { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } }; // U D L R
        row.offer(new int[] { sr, sc });
        while (!row.isEmpty()) {
            int[] current = row.poll();
            int currRow = current[0];
            int currCol = current[1];
            for (int[] dir : dirs) {
                int nr = currRow + dir[0];
                int nc = currCol + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && image[nr][nc] == oldColor) {
                    image[nr][nc] = color;
                    row.offer(new int[] { nr, nc });
                }
            }
        }
        return image;
    }

}