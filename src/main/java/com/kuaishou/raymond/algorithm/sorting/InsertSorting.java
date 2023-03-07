package com.kuaishou.raymond.algorithm.sorting;

public class InsertSorting 
	publ[ic static void insertSorting(int[] numbers) {
		if (numbers == null || numbers.length == 0) {
			return;
		}
		for (int i = 1; i < numbers.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (numbers[j] >= numbers[i]) {
					numbers[j] = numbers[j - 1];
				}
			}
			numbers[]
		}
	}
}