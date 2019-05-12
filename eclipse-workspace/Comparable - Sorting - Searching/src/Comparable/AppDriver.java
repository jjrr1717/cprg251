package Comparable;

import java.util.ArrayList;
import java.util.Collections;

public class AppDriver {
	public static void main(String[] args) {
		/*int[] arr = { 21, 12, 5, 64, 78, 23, 95, 84, 16, 56 }; // unsorted array
		int[] sarr = { 12, 45, 65, 66, 78, 99, 123, 257 }; // sorted array
		int n = 45;
		boolean result = seqSearch(arr, n);
		System.out.println(result);

		result = binSearch(sarr, n);
		System.out.println("*****");
		System.out.println(result);
		
		result = binSearch3(sarr, n, 0, 7);
		System.out.println("*****");
		System.out.println(result);
		
		bubbleIntSort(arr, 10);*/
		
		ArrayList<Student> students = new ArrayList<>();
		students.add(new Student(22, "Matt", 19));
		students.add(new Student(20, "Tom", 24));
		students.add(new Student(18, "Mike", 26));
		
		Collections.sort(students);
		for(Student s: students) {
			System.out.println(s);
		}
		
		
		
	}

	/*
	 * Sequential Search for unsorted array. Start from beginning and compare them.
	 * Not efficient.
	 */
	public static boolean seqSearch(int[] array, int number) {
		boolean result = false;
		for (int i = 0; i < array.length && !result; i++) {

			if (number == array[i]) {
				result = true;
			}
		}
		return result;
	}

	/*
	 * Binary Search. It searches through by comparing mid point.
	 * Divides it by two and determines which side to compare to
	 * next. This only works on sorted arrays. 
	 */
	public static boolean binSearch(int[] array, int number) {
		boolean result = false;

		int low = 0;
		int high = array.length - 1;
		int mid;

		while (low <= high && !result) {
			mid = (low + high) / 2;
			if (number == array[mid])
				result = true;
			else if (number < array[mid])
				high = mid - 1;
			else
				low = mid + 1;
		}
		return result;
	}
	
	/*
	 * Binary Search, but it is recursive
	 */
	public static boolean binSearch3(int[]array, int number, int low, int high) {
		boolean result = false;
		int mid = (low+high)/2;
		if(low <= high) {
			if(array[mid] == number) {
				result = true;
			}
			else if(array[mid] < number) {
				result = binSearch3(array, number, mid+1, high);
			}
			else {
				result = binSearch3(array, number, low, mid-1);
			}
		}
		return result;
	}
	
	public static void bubbleIntSort(int[] a, int n) {
		for(int i = 0; i < n; i++) {
			for(int j = n -1; j < i; j--) {
				if(a[j-1] > a[j]) {
					int temp = a[j-1];
					a[j-1] = a[j];
					a[j] = temp;
				}
			}
		}
		
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i] + ",");
		}
		
	}
}
