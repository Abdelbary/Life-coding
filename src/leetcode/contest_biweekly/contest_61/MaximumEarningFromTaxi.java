package leetcode.contest_biweekly.contest_61;

import java.util.*;

/**
 * There are n points on a road you are driving your taxi on. The n points on the road are labeled from 1 to n
 * in the direction you are going, and you want to drive from point 1 to point n to make money by picking up passengers.
 * You cannot change the direction of the taxi.
 *
 * The passengers are represented by a 0-indexed 2D integer array rides, where rides[i] = [starti, endi, tipi] denotes
 * the ith passenger requesting a ride from point starti to point endi who is willing to give a tipi dollar tip.
 * For each passenger i you pick up, you earn endi - starti + tipi dollars. You may only drive at most one passenger at a time.
 *
 * Given n and rides, return the maximum number of dollars you can earn by picking up the passengers optimally.
 *
 * Note: You may drop off a passenger and pick up a different passenger at the same point.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, rides = [[2,5,4],[1,5,1],[1,2,1]]
 * Output: 7
 * Explanation: We can pick up passenger 0 to earn 5 - 2 + 4 = 7 dollars.
 * Example 2:
 *
 * Input: n = 20, rides = [[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]
 * Output: 20
 * Explanation: We will pick up the following passengers:
 * - Drive passenger 1 from point 3 to point 10 for a profit of 10 - 3 + 2 = 9 dollars.
 * - Drive passenger 2 from point 10 to point 12 for a profit of 12 - 10 + 3 = 5 dollars.
 * - Drive passenger 5 from point 13 to point 18 for a profit of 18 - 13 + 1 = 6 dollars.
 * We earn 9 + 5 + 6 = 20 dollars in total.
 * */
public class MaximumEarningFromTaxi {
    public static void main(String[] args) {
        MaximumEarningFromTaxi maximumEarningFromTaxi = new MaximumEarningFromTaxi();
        long res = maximumEarningFromTaxi.maxTaxiEarnings(20, new int[][]{{1, 6, 1}, {3, 10, 2}, {10, 12, 3}, {11, 12, 2}, {12, 15, 2}, {13, 18, 1}});
        System.out.println(res);

        long res1 = maximumEarningFromTaxi.maxTaxiEarnings(10, new int[][]{{9, 10, 2}, {4, 5, 6}, {6, 8, 1}, {1, 5, 5}, {4, 9, 5}, {1, 6, 5}, {4, 8, 3}, {4, 7, 10}, {1, 9, 8}, {2, 3, 5}});
        System.out.println(res1);

        long res2 = maximumEarningFromTaxi.maxTaxiEarnings(10, new int[][]{{2,5,4},{1,5,1},{1,2,1}});
        System.out.println(res2);
    }

    public long maxTaxiEarnings(int n, int[][] rides) {
        Map<Integer,List<int[]>> map=new HashMap<>();
        for(int[] ride:rides){
            map.compute(ride[1],(k,v)->v==null?new ArrayList<>():v).add(ride);
        }
        long[] cache=new long[n+1];
        for (int i = 1; i <=n; i++) {
            // how many ride options, I have at this station
            List<int[]> passengers= map.get(i);
            cache[i]=cache[i-1]; // whatever my earning was in previous station, would be same
            if(passengers==null) continue;
            for(int[] p:passengers){
                int start=p[0];
                int end=p[1];
                int tip=p[2];
                int earning=end-start+tip;
                cache[i]=Math.max(cache[i],earning+cache[start]);
            }
        }
        return cache[n];
    }
}
