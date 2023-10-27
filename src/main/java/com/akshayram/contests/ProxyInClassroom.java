package com.akshayram.contests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProxyInClassroom {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		List<Integer> all = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			all.add(i);
		}
		
		List<Integer> input = new ArrayList<>();

		String lines = br.readLine();
		String[] present = lines.trim().split("\\s+");

		for (int i = 0; i < present.length; i++) {
			input.add(Integer.parseInt(present[i]));
		}
		for (int i = 0; i < all.size(); i++) {
			if (!input.contains(all.get(i))) {
				System.out.print(all.get(i));

				if (i != all.size() - 1) {
					System.out.print(" ");
				}
			}
		}
	}
}
