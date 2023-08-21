package codinggame;

/*
Pendant les soldes d'hiver, les bonnes affaires partent vite. C'est souvent le premier arrivé sur place qui déniche les bonnes occasions. Une seule solution : se rendre dans un maximum de magasins en quelques heures.

Afin d'optimiser au maximum votre temps, vous décidez de créer votre propre comparateur de prix. Vous alimentez votre comparateur depuis une base de données contenant des catalogues de produits provenant de plusieurs enseignes différentes.

L'objectif de ce challenge est de déterminer le prix le plus bas pour un produit donné. Un produit peut apparaître plusieurs fois dans le comparateur avec des prix différents (en fonction du prix affiché par les différentes enseignes).
*/

import java.util.Arrays;
import java.util.Scanner;

public class PrixLePlusBas {
    public static void solve()
    {
        int n=readInt();
        String nomProduit = readString();
        int minimum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            String[] line = readStringArray();
            String produitCourant = line[0];
            int prix = Integer.parseInt(line[1]);
            if (produitCourant.equals(nomProduit) && prix < minimum) {
                minimum = prix;
            }
        }
        System.out.println(minimum);
    }

    public static Scanner scanner;

    public static void mainF(String[] argv) throws Exception
    {
        scanner = new Scanner(System.in);
        solve();
        scanner.close();
    }

    //
    public static int readInt() { int num =scanner.nextInt(); scanner.nextLine();  return num;}
    public static int[] readIntArray() { return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray(); }
    public static String readString() { return scanner.nextLine(); }
    public static String[] readStringArray() { return scanner.nextLine().split("[ \t]"); }
    public static String[] readLines(int quantity) { String[] lines = new String[quantity]; for (int i = 0; i < quantity; i++) lines[i] = scanner.nextLine().trim(); return lines; }
    public static int[][] readIntMatrix(int numberOfRows) { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readIntArray(); return matrix; }

}
