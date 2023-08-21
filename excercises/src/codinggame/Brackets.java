package codinggame;

import java.util.*;
import java.util.stream.Collectors;

public class Brackets {
    static boolean areBracketsBalanced(String expr)
    {
        // Using ArrayDeque is faster than using Stack class
        Stack<Character> stack
                = new Stack<Character>();

        // Traversing the Expression
        for (int i = 0; i < expr.length(); i++)
        {
            char x = expr.charAt(i);

            if (x == '(' || x == '[' || x == '{')
            {
                // Push the element in the stack
                stack.push(x);
                continue;
            }

            // If current character is not opening
            // bracket, then it must be closing. So stack
            // cannot be empty at this point.
            if (stack.isEmpty())
                return false;
            char check;
            switch (x) {
                case ')':
                    check = stack.pop();
                    if (check == '{' || check == '[')
                        return false;
                    break;

                case '}':
                    check = stack.pop();
                    if (check == '(' || check == '[')
                        return false;
                    break;

                case ']':
                    check = stack.pop();
                    if (check == '(' || check == '{')
                        return false;
                    break;
            }
        }

        // Check Empty Stack
        return (stack.isEmpty());
    }

    public static void mainF(String args[]) {
        int nbPersons;
        List<String> ingredients = new ArrayList<>();


        Scanner in = new Scanner(System.in);
        String expression = in.next();

        if (areBracketsBalanced(expression))
            System.out.println("Balanced ");
        else
            System.out.println("Not Balanced ");
    }
}
