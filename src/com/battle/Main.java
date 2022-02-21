package com.battle;
import java.util.Scanner;

public class Main {
    public static int playerShip = 5;
    public static int compShip = 5;
    public static int numRow = 10;
    public static int numCol = 10;
    public static String[][] grid = new String[numRow][numCol];
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println();
        System.out.println("Right now, the sea is empty.");
        System.out.println();

        createOceanMap();

        deployPlayerShip();

        deployComputerShip();

        Battle();

    }

    public static void createOceanMap() {
        System.out.println("  0123456789");

        for (int row = 0; row < grid.length; row++) {
            System.out.print(row + "|");
            for (int col = 0; col < grid[row].length; col++) {

                if (grid[row][col] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(grid[row][col]);
                }
            }
            System.out.println("|" + row);
        }
        System.out.println("  0123456789");

    }

    public static void deployPlayerShip() {

        int shipCount = 1;

        System.out.println("Deploy your ships:");

        while (shipCount <= 5) {
            System.out.print("Enter X coordinate for your " + shipCount + "." + " ship:");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate for your " + shipCount + "." + " ship:");
            int y = input.nextInt();

            if ( (x >= 0 && x < numRow ) && ( y >= 0 && y < numCol) && (grid[x][y] == null)) {
                grid[x][y] = "@";
                shipCount++;
            }else if ( (x >= 0 && x < numRow ) && ( y >= 0 && y < numCol) && (grid[x][y] == "@")) {
                System.out.println("You can NOT place 2 or more ships on the same location");
            } else {
                System.out.println("You can NOT place ships outside the 10 by 10 grid");
            }


        }

        createOceanMap();

    }

    public static void deployComputerShip() {

        // generate random numbers between 0 and 9
        //int random = (int)(Math.random()*(9-0+1)) + 0;

        System.out.println("Computer is deploying ships");
        int CompShipCount = 1;

        while ( CompShipCount <= 5 ) {
            int x = (int)(Math.random()*(9-0+1)) + 0;
            int y = (int)(Math.random()*(9-0+1)) + 0;

            if ( (x >= 0 && x < numRow ) && ( y >= 0 && y < numCol) && (grid[x][y] == null) && (grid[x][y] != "@")) {
                System.out.println(CompShipCount + "." + "Ship DEPLOYED");
                grid[x][y] = " ";
                CompShipCount++;
            } else if ( (x >= 0 && x < numRow ) && ( y >= 0 && y < numCol) && (grid[x][y] == "@")) {
                //System.out.println("You cannot place the ship on a location taken by another ship");
                x = (int)(Math.random()*(9-0+1)) + 0;
                y = (int)(Math.random()*(9-0+1)) + 0;
            } else {
                //System.out.println("You cannot place the ship outside the 10 by 10 grid");
                x = (int)(Math.random()*(9-0+1)) + 0;
                y = (int)(Math.random()*(9-0+1)) + 0;
            }

        }
        System.out.println("-----------------------------------------");
        createOceanMap();

    }

    public static void Battle() {

            while ((playerShip !=0 && (compShip!=0))) {
                playerTurn();
                computerTurn();
                createOceanMap();
                System.out.println("Your ships: " + playerShip + " | " + "Computer ships : " + compShip);
                System.out.println("__________________________________________________________________");
            }

            gameOver();


    }

    public static void playerTurn() {

            System.out.println("YOUR TURN");
            System.out.print("Enter X coordinate: ");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate: ");
            int y = input.nextInt();

            if (grid[x][y] == " ") {
                System.out.println("Boom! You sunk the ship!");
                grid[x][y] = "!";
                compShip--;
            } else if (grid[x][y] == "@") {
                System.out.println("Oh no, you sunk your own ship :(");
                grid[x][y] = "x";
                playerShip--;
            } else if (grid[x][y] == null){
                System.out.println("Sorry, you missed");
                grid[x][y] = "-";
            } else {
                System.out.println("the location has been used");
            }

    }

    public static void computerTurn() {

        System.out.println("COMPUTER'S TURN");
        //System.out.print("Enter X coordinate: ");
        int x = (int)(Math.random()*(9-0+1)) + 0;
        //System.out.print("Enter Y coordinate: ");
        int y = (int)(Math.random()*(9-0+1)) + 0;

        if (grid[x][y] == "@") {
            System.out.println("The Computer sunk one of your ships!");
            grid[x][y] = "x";
            playerShip--;
        } else if (grid[x][y] == "2") {
            System.out.println("The Computer sunk one of its own ships!");
            grid[x][y] = "!";
            compShip--;
        }else if (grid[x][y] == null){
            System.out.println("Computer missed");
            grid[x][y] = "-";
        } else {
            System.out.println("the location has been used");
        }


    }

    public static void gameOver() {

        if (compShip == 0) {
            System.out.println("Your ships: " + playerShip + " | " + "Computer ships : " + compShip);
            System.out.println("Hooray! You win the battle :)");
        } else {
            System.out.println("Your ships: " + playerShip + " | " + "Computer ships : " + compShip);
            System.out.println("Computer Win");

        }
    }



}
