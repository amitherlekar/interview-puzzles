package com.amit.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Puzzles {

	/**
	 * A number is Happy (Yes, it is a thing!) if it follows a sequence that ends in
	 * 1 after following the steps given below : Beginning with the number itself,
	 * replace it by the sum of the squares of its digits until either the number
	 * becomes 1 or loops endlessly in a cycle that does not include 1.
	 * 
	 * For instance, 19 is a happy number. Sequence: 12 + 92 = 82 82 + 22 = 68 62 +
	 * 82 = 100 12 + 02 + 02 = 1
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isHappyNumber(int n) {
		int[] digitArray = getInputDigits(n);
		int sum;

		do {
			sum = 0;
			for (int i = 0; i < digitArray.length; i++) {
				sum = sum + digitArray[i] * digitArray[i];
			}
			digitArray = getInputDigits(sum);
		} while (sum > 9);

		if (sum == 1)
			return true;

		return false;
	}

	public static int[] getInputDigits(int n) {
		String s = String.valueOf(n);
		int[] digits = new int[s.length()];
		int i = 0;

		while (n > 0) {
			int m = n % 10;
			digits[i++] = m;
			n = n / 10;
		}
		return digits;
	}

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

	public static int[] maxContSequence(int[] arr) {
		int curr_starting_index = 0, curr_ending_index = 0, curr_sum = 0;
		int max_starting_index = 0, max_ending_index = -1, max_sum = 0;

		if (arr.length > 0) {
			curr_sum = arr[0];
			max_sum = arr[0];
			max_ending_index = 0;
		}
		for (int i = 1; i < arr.length; i++) {
			int sum = arr[i] + curr_sum;
			// If the maximum sum plus the current item is less than the item
			// Then we should set maximum sum to be the current item
			if (arr[i] > sum) {
				curr_starting_index = i;
				curr_ending_index = i;
				curr_sum = arr[i];
			} else { // Otherwise, include the current item into our subsequence
				curr_ending_index++;
				curr_sum += arr[i];
			}
			// If the sum of our subsequence is greater than global max so far
			if (curr_sum > max_sum) {
				max_sum = curr_sum; // Update the global max subsequence
				max_starting_index = curr_starting_index;
				max_ending_index = curr_ending_index;
			}
		}
		int[] result = { max_sum, max_starting_index, max_ending_index };
		return result;
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

	/**
	 * Write a method to return all valid combinations of n-pairs of parentheses.
	 * The method should return an ArrayList of strings, in which each string
	 * represents a valid combination of parentheses.
	 * 
	 * The order of the strings in the ArrayList does not matter.
	 * 
	 * @param pairs
	 * @return
	 */
	public static ArrayList<String> combParenthesis(int pairs) {
		ArrayList<String> res = new ArrayList<String>();
		if (pairs > 0) {
			combParenthesis(pairs, pairs, "", res);
		}
		return res;
	}

	public static void combParenthesis(int left, int right, String tmp, ArrayList<String> res) {
		if (left == 0 && right == 0) {
			res.add(tmp);
		} else {
			if (left > 0) {
				combParenthesis(left - 1, right, tmp + "(", res);
			}
			if (right > left) {
				combParenthesis(left, right - 1, tmp + ")", res);
			}
		}
		return;
	}

	public static int longestNonRSubstringLen(String input) {
		if (input == null)
			return 0;
		char[] array = input.toCharArray();
		int prev = 0;

		HashMap<Character, Integer> characterMap = new HashMap<Character, Integer>();

		for (int i = 0; i < array.length; i++) {
			if (!characterMap.containsKey(array[i])) {
				characterMap.put(array[i], i);
			} else {
				prev = Math.max(prev, characterMap.size());
				i = characterMap.get(array[i]);
				characterMap.clear();
			}
		}
		return Math.max(prev, characterMap.size());
	}

	// java.util.* and java.util.streams.* have been imported for this problem.
	// You don't need any other imports.

	public static boolean isBalanced(String in) {

		Stack<Character> s = new Stack<>();
		for (int i = 0; i < in.length(); i++) {
			char c = in.charAt(i);

			switch (c) {
			case '(':
			case '{':
			case '[':
				s.push(c);
				break;
			case '}':
				if (s.isEmpty())
					return false;
				if (s.pop() != '{')
					return false;
				break;
			case ']':
				if (s.isEmpty())
					return false;
				if (s.pop() != '[')
					return false;
				break;
			case ')':
				if (s.isEmpty())
					return false;
				if (s.pop() != '(')
					return false;
				break;
			}
		}

		if (s.isEmpty())
			return false;

		return true;

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

		/*
		 * int[] res = coupleSum(new int[] { 2, 3, 4, 7 }, 7); for (int i : res) {
		 * System.out.println(i + ", "); }
		 */

		System.out.println(longestNonRSubstringLen("aabbabc"));

	}
}
