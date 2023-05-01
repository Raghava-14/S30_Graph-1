/*
In this game, you are given a 2D grid representing a maze, where 0 represents a space that is empty and 1 represents a wall that cannot be crossed. 
You also start at a given starting point in the maze and your goal is to reach a given destination point.

The catch is that you can only move in one direction until you hit a wall or the edge of the maze, and then you can change direction and continue moving. 
More specifically, you can move up, down, left, or right until you hit a wall, and then you must turn to another direction and continue moving.

Your task is to write a function hasPath(int[][] maze, int[] start, int[] destination) that takes in the maze grid, the starting point, 
and the destination point as input, and returns a boolean value indicating whether or not it is possible to reach the destination point from the starting point 
using the rules described above

Example 1
Input:
maze = [[0, 0, 1, 0, 0],
        [0, 0, 0, 0, 0],
        [0, 0, 0, 1, 0],
        [1, 1, 0, 1, 1],
        [0, 0, 0, 0, 0]]
start = [0, 4]
destination = [4, 4]

Output:
true

Example2
Input:
maze = [[0, 0, 1, 0, 0],
        [0, 0, 0, 0, 0],
        [0, 0, 0, 1, 0],
        [1, 1, 0, 1, 1],
        [0, 0, 0, 0, 0]]
start = [0, 4]
destination = [3, 2]

Output:
false

Example3
Input:
maze = [[0, 0, 1, 0, 0],
        [0, 0, 0, 0, 0],
        [0, 0, 0, 1, 0],
        [1, 1, 0, 1, 1],
        [0, 0, 0, 0, 0]]
start = [0, 4]
destination = [4, 0]

Output:
true


*/

//Time = O(mn), where m and n are the dimensions of the maze grid
//Space = O(mn)

class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        
        // Use a boolean array to keep track of visited cells
        boolean[][] visited = new boolean[m][n];
        
        // Initialize the queue with the starting cell
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        
        // Define possible directions of movement
        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        
        // Keep exploring the maze until the queue is empty
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            // If the current cell is the destination, return true
            if (curr[0] == destination[0] && curr[1] == destination[1]) {
                return true;
            }
            
            // Try moving in each direction
            for (int[] dir : dirs) {
                int x = curr[0] + dir[0];
                int y = curr[1] + dir[1];
                
                // Continue in the same direction until a wall is reached
                while (x >= 0 && y >= 0 && x < m && y < n && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }
                
                // Go back one step to get the last valid position
                x -= dir[0];
                y -= dir[1];
                
                // If this cell has not been visited yet, add it to the queue and mark it as visited
                if (!visited[x][y]) {
                    queue.offer(new int[]{x,y});
                    visited[x][y] = true;
                }
            }
        }
        
        // If the queue becomes empty without reaching the destination, return false
        return false;
    }
}
