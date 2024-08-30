import java.lang.reflect.Array;
import java.util.*;

public class Flooding {
    int[][] terrain;
    GridLocation[] sources;
    boolean[][] flooded;
    int height;
    int rows;
    int cols;

    Flooding(int[][] terrain, GridLocation[] sources, int height) {
        this.terrain = terrain;
        this.sources = sources;
        this.height = height;
        rows = terrain.length;
        cols = terrain[0].length;
    }


    public boolean[][] markFloodedR() {
        System.out.println("Flooded in Regions Recursive");
        flooded = new boolean[rows][cols];
        for (int i = 0; i < rows; i++)
            Arrays.fill(flooded[i], false);
        for (GridLocation g : sources) {
            markFloodedR(g);

        }
        return flooded;
    }

    void markFloodedR(GridLocation g) {
        try {
            // Checks if location is in bounds, has already been flooded, or is too tall to be flooded
            if (!validNeighbor(g) || flooded[g.row][g.col] || terrain[g.row][g.col] > height){
                return;
            }

            flooded[g.row][g.col] = true;
            markFloodedR(new GridLocation(g.row + 1, g.col));
            markFloodedR(new GridLocation(g.row - 1, g.col));
            markFloodedR(new GridLocation(g.row, g.col + 1));
            markFloodedR(new GridLocation(g.row, g.col - 1));

        } catch (StackOverflowError e) {
            System.err.println("Stack Overflow");
            System.exit(0);
        }


    }

    public boolean[][] markFlooded() {
        flooded = new boolean[rows][cols];
        for (int i = 0; i < rows; i++)
            Arrays.fill(flooded[i], false);

        LinkedList<GridLocation> queue = new LinkedList<>();
        for (GridLocation g: sources){
            if (terrain[g.row][g.col] <= height){
                flooded[g.row][g.col] = true;
                queue.insert(g);
            }
        }

        while (!queue.isEmpty()){
            GridLocation g = queue.remove();

            GridLocation[] neighbours = new GridLocation[4];
            neighbours[0]= new GridLocation(g.row + 1, g.col);
            neighbours[1]= new GridLocation(g.row - 1, g.col);
            neighbours[2]= new GridLocation(g.row, g.col + 1);
            neighbours[3]= new GridLocation(g.row, g.col - 1);

            for (GridLocation n : neighbours){
                if(validNeighbor(n)) {
                    if (terrain[n.row][n.col] <= height && !flooded[n.row][n.col]) {
                        flooded[n.row][n.col] = true;
                        queue.insert(n);
                    }
                }
            }
        }

        return flooded;
    }

    boolean validNeighbor(GridLocation g) {
        int row = g.row;
        int col = g.col;
        return (row >= 0 && col >= 0 && row < rows && col < cols);
    }


}
