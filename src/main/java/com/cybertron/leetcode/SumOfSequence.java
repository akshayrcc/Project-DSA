package com.cybertron.leetcode;

import java.io.*;

import static java.util.stream.Collectors.joining;

public class SumOfSequence {

	// Complete the getSequenceSum function below.
	static long getSequenceSum(int i, int j, int k) {
		long sum = 0;
		for (int l = i; l < j; l++) {
			System.out.println("First"+l);
			sum += l;
		}
		for (int m = j; m >= k; m--) {
			System.out.println("Second"+m);
			sum += m;
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("D:\\")));

		int i = Integer.parseInt(bufferedReader.readLine().trim());

		int j = Integer.parseInt(bufferedReader.readLine().trim());

		int k = Integer.parseInt(bufferedReader.readLine().trim());

		long res = getSequenceSum(i, j, k);

		System.out.println(res);
		//bufferedWriter.write(String.valueOf(res));
		//bufferedWriter.newLine();

		bufferedReader.close();
		//bufferedWriter.close();
	}
}
