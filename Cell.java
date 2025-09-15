import java.awt.*;


public class Cell {
    int row , col;
    boolean wall = false,visited = false,start = false,end = false;
    int g,h,f;
    Cell parent;
    boolean path = false;

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
    }
    public void toggleWall(){
        wall = !wall;
    }
    public void setEnd(boolean end){
        this.end = end; 
    }
    public void visit(){
        visited = true;
    }
    public void reset(){
        wall = false;
        visited = false;
        start = false;
        g = h = f = Integer.MAX_VALUE;
        parent = null;
        path = false;
    }
    public void draw(Graphics g, int x, int y, int size){
        if(wall) g.setColor(Color.BLACK);
        else if(end) g.setColor(Color.RED);
        else if(visited) g.setColor(Color.GREEN);
        else if (path) g.setColor(Color.YELLOW);   
    else if (visited) g.setColor(Color.CYAN);
        else g.setColor(Color.WHITE);

        g.fillRect(x, y, size, size);
        g.setColor(Color.GRAY);
        g.drawRect(x, y, size, size);
    }
}