import battleships.BattleShips;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");

        //Step 1 – Create the ocean map
        createOceanMap();

        //Step 2 – Deploy player’s ships
        deployPlayerShips();

        //Step 3 - Deploy computer's ships
        deployComputerShips();

        //Step 4 Battle
        do {
            Battle();
        } while (BattleShips.Battleships.playerShips != 0 && BattleShips.Battleships.computerShips != 0);

        //Step 5 - Game over
        gameOver();
    }

    public static void createOceanMap() {
        //First section of Ocean Map
        System.out.print("   ");
        for (int i = 0; i < Battleships.numCols; i++)
            System.out.print(i + " ");
        System.out.println();

        //Middle section of Ocean Map
        for (int i = 0; i < Battleships.grid.length; i++) {
            for (int j = 0; j < Battleships.grid[i].length; j++) {
                Battleships.grid[i][j] = "  ";
                if (j == 0)
                    System.out.print(i + "| " + Battleships.grid[i][j]);
                else if (j == Battleships.grid[i].length - 1)
                    System.out.print(Battleships.grid[i][j] + "|" + i);
                else
                    System.out.print(Battleships.grid[i][j]);
            }
            System.out.println();
        }

        //Last section of Ocean Map
        System.out.print("   ");
        for (int i = 0; i < Battleships.numCols; i++)
            System.out.print(i + " ");
        System.out.println();
    }

    public static void deployPlayerShips() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nDeploy your ships:");
        //Deploying five ships for player
        BattleShips.Battleships.playerShips = 5;
        for (int i = 1; i <= BattleShips.Battleships.playerShips; ) {
            System.out.print("Enter X coordinate for your " + i + " ship: ");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate for your " + i + " ship: ");
            int y = input.nextInt();

            if ((x >= 0 && x < Battleships.numRows) && (y >= 0 && y < Battleships.numCols) && (Battleships.grid[x][y] == " ")) {
                Battleships.grid[x][y] = "@";
                i++;
            } else if ((x >= 0 && x < Battleships.numRows) && (y >= 0 && y < Battleships.numCols) && Battleships.grid[x][y] == "@")
                System.out.println("You can't place two or more ships on the same location");
            else if ((x < 0 || x >= Battleships.numRows) || (y < 0 || y >= Battleships.numCols))
                System.out.println("You can't place ships outside the " + Battleships.numRows + " by " + Battleships.numCols + " grid");
        }
        printOceanMap();
    }

    public static void deployComputerShips() {
        System.out.println("\nComputer is deploying ships");
        //Deploying five ships for computer
        BattleShips.Battleships.computerShips = 5;
        for (int i = 1; i <= BattleShips.Battleships.computerShips; ) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);

            if ((x >= 0 && x < Battleships.numRows) && (y >= 0 && y < Battleships.numCols) && (Battleships.grid[x][y] == " ")) {
                Battleships.grid[x][y] = "x";
                System.out.println(i + ". ship DEPLOYED");
                i++;
            }
        }
        printOceanMap();
    }

    public static void Battle() {
        playerTurn();
        computerTurn();

        printOceanMap();

        System.out.println();
        System.out.println("Your ships: " + BattleShips.Battleships.playerShips + " | Computer ships: " + BattleShips.Battleships.computerShips);
        System.out.println();
    }

    public static void playerTurn() {
        System.out.println("\nYOUR TURN");
        int x = -1, y = -1;
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter X coordinate: ");
            x = input.nextInt();
            System.out.print("Enter Y coordinate: ");
            y = input.nextInt();

            if ((x >= 0 && x < Battleships.numRows) && (y >= 0 && y < Battleships.numCols)) //valid guess
            {
                if (Battleships.grid[x][y] == "x") //if computer ship is already there; computer loses ship
                {
                    System.out.println("Boom! You sunk the ship!");
                    Battleships.grid[x][y] = "!"; //Hit mark
                    --BattleShips.Battleships.computerShips;
                } else if (Battleships.grid[x][y] == "@") {
                    System.out.println("Oh no, you sunk your own ship :(");
                    Battleships.grid[x][y] = "x";
                    --BattleShips.Battleships.playerShips;
                    ++BattleShips.Battleships.computerShips;
                } else if (Battleships.grid[x][y] == " ") {
                    System.out.println("Sorry, you missed");
                    Battleships.grid[x][y] = "-";
                }
            } else if ((x < 0 || x >= Battleships.numRows) || (y < 0 || y >= Battleships.numCols))  //invalid guess
                System.out.println("You can't place ships outside the " + Battleships.numRows + " by " + Battleships.numCols + " grid");
        } while ((x < 0 || x >= Battleships.numRows) || (y < 0 || y >= Battleships.numCols));  //keep re-prompting till valid guess
    }

    public static void computerTurn() {
        System.out.println("\nCOMPUTER'S TURN");
        //Guess co-ordinates
        int x = -1, y = -1;
        do {
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);
            System.out.println("Hello world!!!!");
            System.out.println("Second Hello World");
            if ((x >= 0 && x < Battleships.numRows) && (y >= 0 && y < Battleships.numCols)) //valid guess
            {
                if (Battleships.grid[x][y] == "@") //if player ship is already there; player loses ship
                {
                    System.out.println("The Computer sunk one of your ships!");
                    Battleships.grid[x][y] = "x";
                    --BattleShips.Battleships.playerShips;
                    ++BattleShips.Battleships.computerShips;
                } else if (Battleships.grid[x][y] == "x") {
                    System.out.println("The Computer sunk one of its own ships");
                    Battleships.grid[x][y] = "!";
                } else if (Battleships.grid[x][y] == " ") {
                    System.out.println("Computer missed");
                    //Saving missed guesses for computer
                    if (Battleships.missedGuesses[x][y] != 1)
                        Battleships.missedGuesses[x][y] = 1;
                }
            }
        } while ((x < 0 || x >= Battleships.numRows) || (y < 0 || y >= Battleships.numCols));  //keep re-prompting till valid guess
    }

    public static void gameOver() {
        System.out.println("Your ships: " + BattleShips.Battleships.playerShips + " | Computer ships: " + BattleShips.Battleships.computerShips);
        if (BattleShips.Battleships.playerShips > 0 && BattleShips.Battleships.computerShips <= 0)
            System.out.println("Hooray! You won the battle :)");
        else
            System.out.println("Sorry, you lost the battle");
        System.out.println();
    }

    public static void printOceanMap() {
        System.out.println();
        //First section of Ocean Map
        System.out.print("  ");
        for (int i = 0; i < Battleships.numCols; i++)
            System.out.print(i);
        System.out.println();

        //Middle section of Ocean Map
        for (int x = 0; x < Battleships.grid.length; x++) {
            System.out.print(x + "|");

            for (int y = 0; y < Battleships.grid[x].length; y++) {
                System.out.print(Battleships.grid[x][y]);
            }

            System.out.println("|" + x);
        }

        //Last section of Ocean Map
        System.out.print("  ");
        for (int i = 0; i < Battleships.numCols; i++)
            System.out.print(i);
        System.out.println();
    }
}





