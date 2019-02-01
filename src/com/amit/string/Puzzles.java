package com.amit.string;

import java.util.HashMap;
import java.util.Map;

public class Puzzles {

	public static int largestSum(int[] a) {
		int largestsum = 0;
		int currentSum = 0;

		for (int i : a) {
			currentSum = currentSum + i;
			if (currentSum < 0)
				currentSum = 0;
			if (currentSum > largestsum) {
				largestsum = currentSum;
			}
		}

		return largestsum;
	}

	public static String compress(String org) {
		int j = 1;
		int i = 1;
		StringBuffer b = new StringBuffer();
		char[] orgArr = org.toCharArray();
		for (i = 1; i < orgArr.length; i++) {

			if (orgArr[i] == orgArr[i - 1]) {
				j++;
			} else {
				b.append(orgArr[i - 1]).append(j);
				j = 1;
			}
		}
		b.append(orgArr[i - 1]).append(j);
		return b.toString();

	}

	public static void reverse(char[] a) {
		char f = '\0';
		char b = '\0';

		for (int i = 0; i < a.length / 2; i++) {
			f = a[i];
			b = a[a.length - i - 1];
			a[i] = b;
			a[a.length - i - 1] = f;
		}
	}

	public static String replaceSpaceWith20(String s) {
		int spaceCount = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' ') {
				spaceCount++;
			}
		}

		int newLength = s.length() + (3 * spaceCount);

		char[] chars = new char[newLength];
		int index = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' ') {
				chars[index++] = '%';
				chars[index++] = '2';
				chars[index++] = '0';
			} else {
				chars[index++] = c;
			}
		}

		return new String(chars);

	}

	/**
	 * Input: char[] chars = new char[] { 'a', 'm', 'i', 't', ' ', 'h', ' ', ' ', '
	 * ' }; stringToURL(chars, 6);
	 *
	 * @param str
	 *            = new char[] { 'a', 'm', 'i', 't', ' ', 'h', ' ', ' ', ' ' };
	 * @param length
	 *            = 6
	 */
	public static void stringToURL(char[] str, int length) {
		int spaceCount = 0, newLength, i;
		for (i = 0; i < length; i++) {
			if (str[i] == ' ') {
				spaceCount++;
			}
		}
		newLength = length + spaceCount * 2;
		str[newLength] = '\0';
		for (i = length - 1; i >= 0; i--) {
			if (str[i] == ' ') {
				str[newLength - 1] = '0';
				str[newLength - 2] = '2';
				str[newLength - 3] = '%';
				newLength = newLength - 3;
			} else {
				str[newLength - 1] = str[i];
				newLength = newLength - 1;
			}
		}
	}

	public static int stringToNumber(String numStr) {

		char ch[] = numStr.toCharArray();
		int sum = 0;
		// get ascii value for zero
		int zeroAscii = '0';
		System.out.println(zeroAscii);
		for (char c : ch) {
			int tmpAscii = c;
			System.out.println("c[" + c + "] = " + tmpAscii);
			sum = (sum * 10) + (tmpAscii - zeroAscii);
		}
		return sum;
	}

	public static boolean isPowerOfTwo(int number) {
		/*
		 * Any power of 2 minus 1 is all ones: (2 N - 1 = 111....b)
		 * 
		 * 2 = 2^1. 2-1 = 1 (1b) 4 = 2^2. 4-1 = 3 (11b) 8 = 2^3. 8-1 = 7 (111b) Take 8
		 * for example. 1000 & 0111 = 0000
		 */
		if ((number & (number - 1)) == 0)
			return true;

		return false;

	}

	public static String removeExtraSpaces(String text) {

		char[] strArr = text.toCharArray();
		int count = 0;
		int index = 0;
		for (int i = 0; i < strArr.length; i++) {
			char c = strArr[i];

			if (c == ' ') {
				// Its a space
				if (i == 0) {
					// The beginning of the string contains character
					int j;
					for (j = 1; j + 1 < strArr.length; j++) {
						strArr[j - 1] = strArr[j];
						strArr[j] = '\0';
					}
					// Shift the last character in the array
					strArr[j - 1] = strArr[j];
					strArr[j] = '\0';
					/*
					 * Reset i to 0 because there may be more spaces in the beginning. Here i
					 * becomes -1;
					 */
					i--;
				} else {
					count++;
					if (count == 2) {
						/*
						 * Preserve the position of the second space after a word in 'index'. This is
						 * the position from which extra spaces ahead are covered up.
						 */
						index = i;
					}
				}
			} else {
				// Non - space character
				if (count > 1) {
					for (int j = index; (j + (count - 1)) < strArr.length; j++) {
						/*
						 * Cover the extra spaces with next words in the character array starting from
						 * 'index' by shifting them to left side each time. The next word is at the
						 * distance of (count - 1) from index. Remember, 'index' is position of the
						 * second space after a word.
						 */
						strArr[j] = strArr[j + (count - 1)];
						strArr[j + (count - 1)] = '\0';
					}
					/*
					 * As 'index' is position from which the next word begins, reset i to index.
					 */
					i = index;
				}

				if (count != 0) {
					/*
					 * Reset the count of spaces after shifting all the words.
					 */
					count = 0;
				}
			}
		}

		return new String(strArr);
	}

	public static boolean isAnagram(String s1, String s2) {

		if (s1 == null || s2 == null) {
			return false;
		}

		if (s1.length() != s2.length()) {
			return false;
		}

		int[] buf = new int[26];

		for (int i = 0; i < s1.length(); i++) {
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			buf[c1 - 'a']++;
			buf[c2 - 'a']--;
		}

		for (int i : buf) {
			if (i != 0) {
				return false;
			}
		}

		return true;

	}

	/**
	 * Given an array of integers, find two numbers such that they sum up to a
	 * specific target. The method coupleSum should return the indices of the two
	 * numbers in the array, where index1 must be less than index2. Please note that
	 * the indices are not zero based, and you can assume that each input has
	 * exactly one solution. Target linear runtime and space complexity.
	 * 
	 * @param a
	 * @param target
	 * @return
	 */
	public static int[] coupleSum(int[] a, int target) {

		int[] res = new int[2];
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < a.length; i++) {

			int diff = target - a[i];
			map.put(diff, i);
			if (map.containsKey(a[i])) {
				return new int[] { map.get(a[i]) + 1, i + 1 };
			}
		}
		return res;

	}

	/**
	 * Implement a method that reverses an integer - without using additional heap
	 * space
	 */
	public static int reverseInt(int x) {

		int rev = 0;

		while (x != 0) {
			rev = rev * 10 + x % 10;
			x = x / 10;
		}
		return rev;

	}

	public static void main(String[] args) {
		// char[] chars = new char[] { 'a', 'm', 'i', 't', ' ', 'h', ' ', ' ',
		// ' ' };
		// stringToURL(chars, 6);
		// reverse(chars);
		// System.out.println(new String(chars));
		// System.out.println(replaceSpaceWith20("am i t "));
		// String s = new String(chars);
		// System.out.println(isPowerOfTwo(18));
		// System.out.println(15 >> 1);
		// System.out.println(removeExtraSpaces(" I am Amit Herlekar "));

		int[] res = coupleSum(new int[] { 2, 3, 4, 7 }, 7);
		for (int i : res) {
			System.out.println(i + ", ");
		}

	}
}
