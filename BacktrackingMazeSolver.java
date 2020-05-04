import java.util.Stack;

public class BacktrackingMazeSolver {

    /**
     * Moves a rat from (x1,y1) to (x2,y2), filling in the cells as it goes, and
     * notifying a listener at each step.
     */
    public boolean solve(Maze maze, Maze.MazeListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null");
        }
        
        var path = new Stack<Maze.Location>();
        var current = maze.getInitialRatPosition();

        // TODO: initialize the current location to the initial rat location

        // Solution loop. At each step, place the rat and notify listener.
        while (true) {
            current.place(Maze.Cell.RAT);
            listener.mazeChanged(maze);

            if (current.isAt(maze.getInitialCheesePosition())) {
                return true;
            }
            

            if (current.above().canBeMovedTo()) {
                path.push(current);
                current.place(Maze.Cell.PATH);
                current = current.above();
            } else if (current.toTheRight().canBeMovedTo()) {
                path.push(current);
                current.place(Maze.Cell.PATH);
                current = current.toTheRight();
            } else if (current.below().canBeMovedTo()) {
                path.push(current);
                current.place(Maze.Cell.PATH);
                current = current.below();
            } else if (current.toTheLeft().canBeMovedTo()) {
                path.push(current);
                current.place(Maze.Cell.PATH);
                current = current.toTheLeft();
            } else {
                current.place(Maze.Cell.TRIED);
                if (path.empty()) {
                    return false;
                }
                else {
                    current = path.pop();
                }
            }
        }
    }
}
