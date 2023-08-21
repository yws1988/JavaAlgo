package codinggame;
/*
Un soir, deux frères profitent de l'absence de leurs parents pour commander des burgers. Pour ne pas laisser de trace, l'un d'entre eux doit descendre les poubelles avant minuit. Ils décident de se départager au jeu de la bataille. Le principe est simple, chaque joueur a le même nombre de cartes et à chaque tour, chaque joueur présente une carte. Celui qui a la carte de plus grande valeur remporte le point. Si les deux cartes ont la même valeur, personne ne remporte le point. Le gagnant du jeu est celui qui a le plus de points à la fin.

L'objectif de ce challenge est de déterminer qui ne descendra pas les poubelles ce soir (gagnant de la partie).

On va nommer les deux frères A et B. Pour simplifier, nous allons considérer que les cartes ont des valeurs pouvant aller de 1 à 10. Nous vous garantissons qu'il y a bien un gagnant à la fin de la partie (pas d'égalité possible entre les deux joueurs).

Format des données
Entrée
Ligne 1 : un entier N compris entre 10 et 100 représentant le nombre de tours.

Lignes 2 à N+1 : deux entiers compris entre 1 et 10 séparés par un espace représentant la carte du joueur A et celle du joueur B.
 */

//Battail

import java.util.Arrays;
import java.util.Scanner;

public class Bataille
{
    public static int n;

    public static void solve()
    {
        n=readInt();
        int ap=0;
        int bp=0;

        for (int i = 0; i < n; i++) {
            int[] tmp = readIntArray();
            int a = tmp[0];
            int b = tmp[1];

            if(a>b){
                ap++;
            }else if(b>a){
                bp++;
            }
        }

        System.out.println(ap>bp ? "A":"B");
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

    public static class Pair{
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


