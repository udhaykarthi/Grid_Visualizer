import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class GridPanel extends JPanel {
    private int rows, cols;
    private Cell[][] cells;
    private int cellSize = 25;

    public GridPanel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new Cell[rows][cols];

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                cells[r][c] = new Cell(r, c);

        addMouseListener((MouseListener) new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int r = e.getY() / cellSize;
                int c = e.getX() / cellSize;
                if (SwingUtilities.isLeftMouseButton(e))
                    cells[r][c].toggleWall();
                else if (SwingUtilities.isRightMouseButton(e))
                    cells[r][c].setEnd(true);
                repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cells[r][c].draw(g, c * cellSize, r * cellSize, cellSize);
            }
        }
    }

    public Cell[][] getCells() { return cells; }

    public Cell getEnd(){
        for(Cell[] row : cells)
            for(Cell cell : row)
                if(cell.end) return cell;
        return cells[rows-1][cols-1];
    }

    public void reset() {
        for (Cell[] row : cells)
            for (Cell cell : row)
                cell.reset();
        repaint();
    }
}