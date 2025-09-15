import java.util.*;
public class Bfs extends Algorithm {
    @Override
    public void run(GridPanel grid) {
        new Thread(() -> {
            Cell[][] cells = grid.getCells();
            Queue<Cell> q = new LinkedList<>();
            Cell start = cells[0][0];
            Cell end = grid.getEnd();
            q.add(start);

            while (!q.isEmpty()) {
                Cell cur = q.poll();
                if (cur.visited || cur.wall) continue;
                cur.visit();
                grid.repaint();
                try { Thread.sleep(50); } catch (Exception e) {}

                if (cur == end) {
                    reconstructPath(end, grid);
                    break;
                }

                int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
                for (int i = 0; i < 4; i++) {
                    int nr = cur.row + dr[i], nc = cur.col + dc[i];
                    if (nr >= 0 && nr < cells.length && nc >= 0 && nc < cells[0].length) {
                        Cell neighbour = cells[nr][nc];
                        if (!neighbour.visited && !neighbour.wall && neighbour.parent == null) {
                            neighbour.parent = cur;
                            q.add(neighbour);
                        }
                    }
                }
            }
        }).start();
    }

    private void reconstructPath(Cell end, GridPanel grid) {
        Cell cur = end.parent;
        while (cur != null && !cur.end) {
            cur.path = true;
            cur = cur.parent;
        }
        grid.repaint();
    }
}
