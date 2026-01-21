import java.util.ArrayList;

/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author Oliver Faris
 */

public class WeatherPatterns {
    private static ArrayList<Integer>[] adjacencyList;
    private static int[] longest;

    public static int longestWarmingTrend(int[] temperatures) {
        adjacencyList = new ArrayList[temperatures.length];
        longest = new int[temperatures.length];
        int count;

        // Fill adjacency list
        for (int i = 0; i < adjacencyList.length; i++) {
            // Initialize every arraylist
            adjacencyList[i] = new ArrayList<>();
            count = i;
            while (count > 0) {
                count--;
                int temp = temperatures[count];
                if (temp < temperatures[i])
                    adjacencyList[i].add(count);
            }
        }

        // Find longest path to each node
        int longestLength = -1;
        for (int i = 0; i < temperatures.length; i++) {
            int length = longestPathTo(i);
            if (length > longestLength)
                longestLength = length;
        }

        return longestLength;
    }

    public static int longestPathTo(int index) {
        int len = 1;

        // If longest length isn't saved yet find the longest length
        if (longest[index] == 0) {
            for (int in : adjacencyList[index]) {
                len = Math.max(len, longestPathTo(in) +1);
            }
            // Save to longest length array
            longest[index] = len;
        }
        else
            len = longest[index];

        return len;
    }
}
