import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите матрицу:");

        try {
            int[][] matrix = fillMatrix(scn);

            System.out.println("Исходная матрица: ");
            printMatrix(matrix);

            int[][] transposed = transposeMatrix(matrix);

            System.out.println("Транспонированная матрица: ");
            printMatrix(transposed);

            int[][] result = multiplyMatrix(matrix, transposed);

            System.out.println("Результат умножения: ");
            printMatrix(result);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static int[][] fillMatrix(Scanner scn) throws Exception {
        String line = scn.nextLine();
        int[] numList = convertToNum(line);
        int len = numList.length;
        int[][] matrix = new int[len][len];

        matrix[0] = numList;

        for (int i = 1; i < len; i++) {
            String tempLine = scn.nextLine();
            int[] tempNumList = convertToNum(tempLine);

            if (tempNumList.length != len) {
                throw new Exception(String.format("Введено %d элементов в строке при вводе матрицы %dx%d", tempNumList.length, len, len));
            }

            matrix[i] = tempNumList;
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        int len = matrix.length;

        for (int s = 0; s < len; s++) {
            for (int el = 0; el < len; el++) {
                System.out.print(matrix[s][el] + " ");
            }            
            System.out.println();
        }
    }

    public static int[] convertToNum(String line) throws Exception {
        String[] strList = line.split(" ");
        int len = strList.length;
        int[] numList = new int[len];

        try {
            for (int i = 0; i < len; i++) {
                numList[i] = Integer.parseInt(strList[i]);
            }
        } catch (NumberFormatException nfe) {
            throw new Exception("Элементами матрицы могут быть только целые числа");
        }

        return numList;
    }

    public static int[][] transposeMatrix(int[][] matrix) {
        int len = matrix.length;
        int[][] transposed = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }

        return transposed;
    }

    public static int[][] multiplyMatrix(int[][] matrixA, int[][] matrixB) {
        int len = matrixA.length;
        int[][] result = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int sum = 0;

                for (int k = 0; k < len; k++) {
                    sum += matrixA[i][k] * matrixB[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }
}