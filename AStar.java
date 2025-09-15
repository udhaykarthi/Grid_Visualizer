import java.util.*;

public class AStar extends Algorithm {
    @Override
    public void run(GridPanel grid) {
        new Thread(() -> {
            Cell[][] cells = grid.getCells();
            Cell start = cells[0][0];
            Cell end = grid.getEnd();

            // Reset all cells before search
            for (Cell[] row : cells) {
                for (Cell cell : row) {
                    cell.g = Integer.MAX_VALUE;
                    cell.h = 0;
                    cell.f = 0;
                    cell.parent = null;
                    cell.visited = false;
                    cell.path = false;
                }
            }

            PriorityQueue<Cell> open = new PriorityQueue<>(Comparator.comparingInt(c -> c.f));
            start.g = 0;
            start.h = heuristic(start, end);
            start.f = start.h;
            open.add(start);

            while (!open.isEmpty()) {
                Cell cur = open.poll();

                if (cur.visited) continue;
                cur.visited = true;
                grid.repaint();

                // End found → reconstruct path
                if (cur == end) {
                    reconstructPath(end, grid);
                    break;
                }

                try { Thread.sleep(50); } catch (Exception e) {}

                int[] dr = {-1, 1, 0, 0};
                int[] dc = {0, 0, -1, 1};
                for (int i = 0; i < 4; i++) {
                    int nr = cur.row + dr[i], nc = cur.col + dc[i];
                    if (nr >= 0 && nr < cells.length && nc >= 0 && nc < cells[0].length) {
                        Cell neighbour = cells[nr][nc];
                        if (neighbour.wall || neighbour.visited) continue;

                        int tentativeG = cur.g + 1;
                        if (tentativeG < neighbour.g) {
                            neighbour.g = tentativeG;
                            neighbour.h = heuristic(neighbour, end);
                            neighbour.f = neighbour.g + neighbour.h;
                            neighbour.parent = cur;
                            open.add(neighbour);
                        }
                    }
                }
            }
        }).start();
    }

    private int heuristic(Cell a, Cell b) {
        
        return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
    }

    private void reconstructPath(Cell end, GridPanel grid) {
        Cell cur = end.parent;
        while (cur != null && !cur.end && !(cur.row == 0 && cur.col == 0)) {
            cur.path = true;
            cur = cur.parent;
        }
        grid.repaint();
    }
}
