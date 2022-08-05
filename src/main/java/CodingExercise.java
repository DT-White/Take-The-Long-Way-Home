import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodingExercise {
    private int highestResult = 0;
    private int[] winningPath;
    private List<int[]> possiblePaths = new ArrayList<>();
    private String[][] grid;

    public String takeTheLongWayHome(String[][] input) {
        grid = input;
        int[] firstPath = new int[input[0].length - 1];
        winningPath = new int[firstPath.length + 1];
        determinePossiblePaths(firstPath, 0);
        for (int[] path : possiblePaths) {
            for (int i = 0; i < input.length; i++) {
                attemptRoute(path, i);
            }
        }
        return buildStringFromIntArray(winningPath);
    }

    private void determinePossiblePaths(int[] current, int index) {
        //uses recursion to find every possible combination of 0s, 1s, and -1s, representing lateral or diagonal moves
        while (true) {
            current[index] = 0;
            if (!isPathInList(current)) {
                possiblePaths.add(Arrays.copyOf(current, current.length));
            }
            if (index < current.length - 1) {
                determinePossiblePaths(current, index + 1);
            }
            current[index] = 1;
            if (!isPathInList(current)) {
                possiblePaths.add(Arrays.copyOf(current, current.length));
            }
            if (index < current.length - 1) {
                determinePossiblePaths(current, index + 1);
            }
            current[index] = -1;
            if (!isPathInList(current)) {
                possiblePaths.add(Arrays.copyOf(current, current.length));
            }
            if (index < current.length - 1) {
                determinePossiblePaths(current, index + 1);
            }
            return;
        }
    }

    private boolean isPathInList(int[] path) {
        //returns true if the path is already in the list
        for (int[] currentPath : possiblePaths) {
            if (Arrays.equals(path, currentPath)) {
                return true;
            }
        }
        return false;
    }

    private int doNextOperation(String str, int total){
        // parses and applies arithmetic operator and value
        String[] arr = str.split(" ");
        if (arr[0].equals("+")){
            total += Integer.parseInt(arr[1]);
        } else if (arr[0].equals("-")){
            total -= Integer.parseInt(arr[1]);
        } else if (arr[0].equals("/")){
            total /= Integer.parseInt(arr[1]);
        } else if (arr[0].equals("*")){
            total *= Integer.parseInt(arr[1]);
        }
        return total;
    }

    private void attemptRoute(int[] path, int startingIndex){
        // applies a path from the list of possibilities, accounting for roadblocks and wrapping
        // saves the path and result if the result was the new highest
        int total = 0;
        int currentIndex = startingIndex;
        int[] currentPath = new int[path.length + 1];
        currentPath[0] = startingIndex + 1;
        String nextStep = grid[startingIndex][0];
        if (nextStep.equals("X")){
            return;
        }
        total = doNextOperation(nextStep, total);
        for (int i = 0; i < path.length; i++){
            currentIndex += path[i];
            // Wrap around from top to bottom and bottom to top
            if (currentIndex < 0){
                currentIndex = grid.length - 1;
            } else if (currentIndex >= grid.length){
                currentIndex = 0;
            }
            nextStep = grid[currentIndex][i + 1];
            // Give up if you hit a roadblock
            if (nextStep.equals("X")){
                return;
            }
            total = doNextOperation(nextStep, total);
            currentPath[i + 1] = currentIndex + 1;
        }
        // Save path and result if the result is the new highest
        if (total > highestResult){
            winningPath = currentPath;
            highestResult = total;
        }
    }

    private String buildStringFromIntArray(int[] intArray){
        //converts the array of ints to a comma-separated string
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < intArray.length; i++) {
            newString.append(intArray[i]);
            if (i < intArray.length - 1){
                newString.append(", ");
            }
        }
        return newString.toString();
    }
}
