import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class NumberOfAirplanesOnTheSky {
/**
 * https://www.lintcode.com/problem/number-of-airplanes-in-the-sky/description
 * @idea: meeting room2, record priority queue size的最大值就可以
 * @time: O(nlogn)
 * @space: O(n)
 * @testCase:
 */
    public static class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public int countOfAirplanes(List<Interval> airplanes) {
        if (airplanes == null || airplanes.size() == 0) {
            return 0;
        }
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> (a.end - b.end));
        Collections.sort(airplanes, (a, b) -> (a.start - b.start));
        pq.offer(airplanes.get(0));
        int max = 0;
        for (int i = 1; i < airplanes.size(); i++) {
            if (airplanes.get(i).start < pq.peek().end) {  //overlap
                pq.offer(airplanes.get(i));
            }
            else { // no overlap
                pq.poll();
                pq.offer(airplanes.get(i));
            }
            max = Math.max(max, pq.size());
        }
        return max;
    }

    public static void main(String[] args) {
        NumberOfAirplanesOnTheSky solution = new NumberOfAirplanesOnTheSky();
        Interval interval1 = new Interval(1, 10);
        Interval interval2 = new Interval(2, 3);
        Interval interval3 = new Interval(5, 8);
        Interval interval4 = new Interval(4, 7);
        List<Interval> airplanes = new ArrayList<>();
        airplanes.add(interval1);
        airplanes.add(interval2);
        airplanes.add(interval3);
        airplanes.add(interval4);

        System.out.println(solution.countOfAirplanes(airplanes));
    }
}