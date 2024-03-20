package utils;

import java.util.Arrays;
import java.util.Scanner;

public class IO
{
    public static Scanner scanner;

    public static int readInt() { int tmp = scanner.nextInt(); scanner.nextLine(); return tmp; }
    public static int[] readIntArray() { return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray(); }
    public static long readLong() { return scanner.nextLong(); }
    public static long[] readLongArray() { return Arrays.stream(readStringArray()).mapToLong(Long::parseLong).toArray(); }
    public static double readDouble() { return scanner.nextDouble(); }
    public static double[] readDoubleArray() { return Arrays.stream(readStringArray()).mapToDouble(Double::parseDouble).toArray(); }
    public static int[][] readIntMatrix(int numberOfRows) { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readIntArray(); return matrix; }
    public static double[][] readDoubleMatrix(int numberOfRows) { double[][] matrix = new double[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readDoubleArray(); return matrix; }
    public static String readString() { return scanner.nextLine(); }
    public static String[] readStringArray() { return scanner.nextLine().split("[ \t]"); }
    public static String[] readLines(int quantity) { String[] lines = new String[quantity]; for (int i = 0; i < quantity; i++) lines[i] = scanner.nextLine().trim(); return lines; }
    public static String[][] readStringMatrix(int numberOfRows) { String[][] matrix = new String[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readStringArray(); return matrix; }
    public static char[] readChars() { return scanner.nextLine().toCharArray(); }
    public static char[][] readCharsMatrix(int numberOfRows) { char[][] matrix = new char[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readChars(); return matrix; }
}