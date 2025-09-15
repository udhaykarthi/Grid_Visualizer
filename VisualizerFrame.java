import javax.swing.*;
import java.awt.*;


public class VisualizerFrame extends JFrame {
    private GridPanel grid;
    public VisualizerFrame(){
        setTitle("Pathfinding Visualizer");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        grid = new GridPanel(20,30);
        add(grid, BorderLayout.CENTER);
        JPanel controls = new JPanel();
        JButton bfsBtn = new JButton("BFS");
        JButton dfsBtn = new JButton("DFS");
        JButton AStar = new JButton("A*");
        JButton reset = new JButton("Reset");

        bfsBtn.addActionListener(e -> new Bfs().run(grid));
        dfsBtn.addActionListener(e -> new DFS().run(grid));
        AStar.addActionListener(e -> new AStar().run(grid));
        reset.addActionListener(e -> grid.reset());

        controls.add(bfsBtn);
        controls.add(dfsBtn);
        controls.add(AStar);
        controls.add(reset);

        add(controls, BorderLayout.SOUTH);

        setVisible(true);
    }
}

