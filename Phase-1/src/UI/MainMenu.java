package UI;

import Products.ProductView;

import java.util.Scanner;

public class MainMenu {
    static Scanner sc = new Scanner(System.in);

    public static void mainMenu()
    {
        while (true)
        {
            System.out.println("--- Main Menu ---");
            System.out.println("1. Panel");
            System.out.println("2. Products");
            System.out.println("3. Exit");

            int n = sc.nextInt();

            switch (n)
            {
                case 1 -> ProfileMenu.panelMenu();

                case 2 -> ProductView.productMenu();

                case 3 -> System.exit(0);
            }
        }
    }
}
