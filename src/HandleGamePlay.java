import java.util.InputMismatchException;
import java.util.Scanner;

public class HandleGamePlay {
    static CodeBreaker codeBreaker;
    static Scanner myScanner;


    public static void play() {
        codeBreaker = new CodeBreaker();
        myScanner= new Scanner(System.in);


        int direct = 0;

        try {
            while (direct != 6) { //while all positions are not correct

                String guess = codeBreaker.getNextGuess();

                System.out.println("Mastermind's Guess: " + guess);


                System.out.println("Enter the #of direct hits");
                direct = myScanner.nextInt();

                System.out.println("Enter the #of indirect hits");
                int indirect = myScanner.nextInt();

                codeBreaker.generateGuess(indirect, direct); //analyze what to do with the response

            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Your answers are inconsistent");
            exceptionDetected();

        }catch (InputMismatchException e){
            System.out.println("Your answers are non-sense");
            exceptionDetected();
        }

        System.out.println("Computer has won!");

    }
    public static void exceptionDetected() {
        myScanner= new Scanner(System.in);

        System.out.println("Do you want to exit (press 0) or start a new game(press 1): ");
        int answer= myScanner.nextInt();
        if (answer==1){
            codeBreaker.reset();
            play();
        }if (answer==0){
            System.out.println("Bye!!");
            System.exit(0);
        }

    }
}
