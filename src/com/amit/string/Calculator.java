package com.amit.string;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * An utility class that evaluates an expression of the form: (((a+b)*c)/d)%e
 *
 * @author Amit Herlekar
 * @version 1.0
 *
 */
public class Calculator {

	private String expression;

	public Calculator(String expression) {
		System.out.println("Expression = " + expression);
		this.expression = expression;
	}

	private String infixToPostfix(String infixExp) {
		StringBuffer buffer;
		StringBuffer temp;

		buffer = new StringBuffer();
		temp = new StringBuffer();

		Stack<String> stack = new Stack<String>();

		for (int i = 0, j = 0; i < infixExp.length(); i++) {

			char c = infixExp.charAt(i);

			if (c == '(') {
				stack.push(String.valueOf(c));
			} else if (c == ')') {
				while (!"(".equals(stack.peek())) {
					buffer.append(stack.pop());
				}
				// Pop '('
				stack.pop();
			} else if (!isOperator(String.valueOf(c))) {
				// Operand
				/*
				 * This block is entirely handled for multiple digits
				 */
				for (j = i; j < infixExp.length(); j++) {
					char c1 = infixExp.charAt(j);
					if (isDigit(String.valueOf(c1))
							|| isDecimalPoint(String.valueOf(c1)))
						temp.append(String.valueOf(c1));
					else
						break;
				}
				i = j - 1;
				temp.append(",");
				buffer.append(temp);
				temp.delete(0, temp.length());

			} else if (isOperator(String.valueOf(c))) {
				// Operator
				if (!stack.isEmpty()
						&& getPrecedence(stack.peek()) > getPrecedence(String
								.valueOf(c))) {
					buffer.append(stack.pop());
				}
				stack.push(String.valueOf(c));
			}

			/*
			 * } else if (stack.isEmpty() && isOperator(String.valueOf(c))) {
			 * stack.push(String.valueOf(c)); } else if
			 * ((isOperator(String.valueOf(c))) && (!stack.isEmpty())) {
			 * stack.push(String.valueOf(c)); if (getPrecedence(stack.peek()) >
			 * getPrecedence(String .valueOf(c))) { buffer.append(stack.pop());
			 * } }
			 */
		}
		while (!stack.isEmpty()) {
			buffer.append(stack.pop());
		}
		return buffer.toString();
	}

	private Double evaluatePostfix(String postFixExp) {

		Double operand1, operand2;
		StringBuffer temp = new StringBuffer();

		Stack<String> stack = new Stack<String>();

		for (int i = 0, j1 = 0; i < postFixExp.length(); i++) {

			if (!isOperator(String.valueOf(postFixExp.charAt(i)))) {

				for (j1 = i; j1 < postFixExp.length(); j1++) {
					if (isDigit(String.valueOf(postFixExp.charAt(j1)))
							|| isDecimalPoint(String.valueOf(postFixExp
									.charAt(j1))))
						temp.append(String.valueOf(postFixExp.charAt(j1)));
					else
						break;
				}
				i = j1;
				System.out.println(postFixExp.charAt(i));

				stack.push(temp.toString());
				temp.delete(0, temp.length());

			} else if (isOperator(String.valueOf(postFixExp.charAt(i)))) {
				switch (postFixExp.charAt(i)) {
				case '+':
					operand2 = Double.parseDouble(stack.pop());
					operand1 = Double.parseDouble(stack.pop());
					stack.push(String.valueOf(operand1 + operand2));
					break;
				case '-':
					operand2 = Double.parseDouble(stack.pop());
					operand1 = Double.parseDouble(stack.pop());
					stack.push(String.valueOf(operand1 - operand2));
					break;
				case '*':
					operand2 = Double.parseDouble(stack.pop());
					operand1 = Double.parseDouble(stack.pop());
					stack.push(String.valueOf(operand1 * operand2));
					break;
				case '/':
					operand2 = Double.parseDouble(stack.pop());
					operand1 = Double.parseDouble(stack.pop());
					stack.push(String.valueOf(operand1 / operand2));
					break;
				case '%':
					operand2 = Double.parseDouble(stack.pop());
					operand1 = Double.parseDouble(stack.pop());
					stack.push(String.valueOf(operand1 % operand2));
					break;
				case '^':
					Double temp1 = 1.0;
					operand2 = Double.parseDouble(stack.pop());
					operand1 = Double.parseDouble(stack.pop());
					for (int j = 0; j < operand2; j++)
						temp1 = temp1 * operand1;
					stack.push(String.valueOf(temp1));
					break;
				}

			}

		}

		return Double.parseDouble(stack.pop());
	}

	private boolean isOperator(String operator) {

		String operators = "^*/%+-";
		if (operators.indexOf(operator) != -1)
			return true;
		return false;

	}

	private int getPrecedence(String s) {
		char c = s.charAt(0);
		switch (c) {
		case '^':
			return 3;
		case '/':
		case '*':
		case '%':
			return 2;
		case '+':
		case '-':
			return 1;

		}
		return 0;
	}

	private boolean isDigit(String s) {

		String digit = "0123456789";

		if (digit.indexOf(s) != -1)
			return true;
		return false;
	}

	private boolean isDecimalPoint(String s) {
		if (".".indexOf(s) != -1)
			return true;
		return false;
	}

	private String removeSpaces(String expr) {

		StringTokenizer tokenizer = new StringTokenizer(expr, " ", false);
		StringBuffer t = new StringBuffer("");

		while (tokenizer.hasMoreElements())
			t.append(tokenizer.nextElement());
		return t.toString();

	}

	private Double evaluateExpression(String expression) throws Exception {

		if (expression == null || expression.isEmpty()) {
			throw new IllegalArgumentException("Expression is null");
		}

		String infixToPostFixExp = this.infixToPostfix(this
				.removeSpaces(expression));
		System.out.println("Infix ---> Postfix = " + infixToPostFixExp);
		return this.evaluatePostfix(infixToPostFixExp);

	}

	public Double evaluateExpression() throws Exception {
		return evaluateExpression(this.expression);
	}

	public static void main(String[] args) throws Exception {
		String exp = "((222*8)/2^2)-555";
		Calculator c = new Calculator(exp);
		System.out.println(c.evaluateExpression());
	}

}
