package com.cybertron.contests;

import java.util.*;

class ContestProblemBeforeParty {

	public static void main(String[] args) {
		final int totalTimeInMins = 360; /* Total time before party */
		int sum = 0, i = 1, timeRemaining = 0, m = 0;
		ArrayList<Integer> list = new ArrayList();
		// HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
		for (int x = 3; sum < totalTimeInMins; x += 3) {
			sum += x;
			if (sum < totalTimeInMins) {
				// map.put(i,sum);
				list.add(sum);
			} else {
				break;
			}
			i++;
		}

		System.out.print("list:");
		for (int a : list) {
			System.out.print(a + " ");
		}
		System.out.println("list-size" + list.size());
		/*System.out.print("map:");
		for (Map.Entry m : map.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		} */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt(); /* Read total number of test cases */
		for (int t = 0; t < testCases; t++) {
			int n = sc.nextInt(); /* available n problems in the contest */
			int k = sc.nextInt(); /* time to reach to the party location */
			timeRemaining = totalTimeInMins - k; /* Time remaining to solve out of n problems in the contest */
			// System.out.println("timeRemaining= " + timeRemaining);

			/*
			 * for(m=0; m < list.size() && timeRemaining >= list.get(m) ; m++){
			 * //System.out.println("=list=>" + list.get(m)); }
			 */
			m = Collections.binarySearch(list, timeRemaining);

			if (m < 0) {
				m = Math.abs(m + 1);
			}
			// System.out.println("index= " + m);

			/* Final compare with the available problems */
			if (n < m) {
				System.out.println(n);
			} else {
				System.out.println(m);
			}
		}
	}
}