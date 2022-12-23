import java.util.ArrayList;

public class CodeBreaker {
    String[] numbers= {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    int digit=6;
    ArrayList<String> allCombinations = new ArrayList<String>(); //all of the possible 6-digit combinations will be held on this ArrayList
    String prewGuess;

    public CodeBreaker() { //initializing the combinations and temp-guess [0,1,2,3,4,5,6,7,8,9], 6
        prewGuess = null;
        allCombinations.addAll(generateCombinations(numbers, digit));

    }

    public ArrayList<String> generateCombinations(String[] numbers, int positions) {
        ArrayList<String> combos = new ArrayList<String>();
        if (positions == 1) {  //base case
            for (int i = 0; i < numbers.length; i++) {
                combos.add(numbers[i]); //will add one color to a new position in the combos array

            }
            return combos;
        }
        else{
            for(int i=0; i<numbers.length;i++){
                ArrayList<String> looping= generateCombinations(numbers, positions-1); //this will provide the recursion for the other levels
                for(int j=0; j<looping.size();j++){
                    combos.add(numbers[i]+looping.get(j));
                }
            }

        }
        return combos;

    }
    public static int[] compare(String guessed, String newguess){ //method to compare the current computer's guess with all the remaining possible combos
        int directHits=0; //will stand for the number of characters that are the same and same position
        int indirectHits=0; //will stand for the number of char that are the same but not same position between the two strings

        char[] guessedNum=guessed.toCharArray(); //need to convert the strings into a character array to iterate
        char[] guessedNew=newguess.toCharArray();

        //comparison for directHits
        for(int i=0;i<guessedNum.length;i++){
            if(guessedNum[i]==guessedNew[i]){ //count direct hits
                directHits++;

                guessedNum[i]='\0'; //we set it to 0 because it should not count as indirect hit
                guessedNew[i]='\0';
            }
        }

        //comparison for indirectHits
        for(int j=0;j<guessedNum.length;j++){ // position of number doesn't important
            for(int k=0;k<guessedNew.length;k++){
                if(guessedNum[j]==guessedNew[k] && guessedNum[k]!='\0' && guessedNum[j]!='\0'){
                    guessedNew[k]='\0'; //set it into null so it won't get counted again
                    indirectHits++;
                }
            }
        }

        int[] comparison={directHits,indirectHits};

        return comparison;

    }
    public void generateGuess(int indirectHits, int directHits) { //will analyze what to do with the given response from the user
        for(int i=0;i<allCombinations.size();i++){  //go through all the remaining combinations
            int[] responses=compare(prewGuess, allCombinations.get(i)); //will get back the amount of same positions
            if(responses[0]!=directHits || responses[1]!=indirectHits){
                allCombinations.remove(i);  //will remove it
                i--; //but since it is removed, the count has decreased so we need to go back an i
            }


        }

    }
    public void reset() {

        allCombinations.clear(); //clearing all the leftover combos in the combo array


    }


    public String nextMove() { //returns the top combo of the possible combinations
        prewGuess = allCombinations.get(0);

        return allCombinations.remove(0);
    }


}