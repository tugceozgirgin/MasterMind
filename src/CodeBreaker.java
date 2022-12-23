import java.util.ArrayList;

public class CodeBreaker {
    String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    ArrayList<String> allPossibilities = new ArrayList<String>(); //all of the possible 6-digit combinations will be held on this ArrayList
    String prewGuess;

    public CodeBreaker() { //initializing the possibilities and temp-guess [0,1,2,3,4,5,6,7,8,9]
        prewGuess = null;
        allPossibilities.addAll(generatePossibilities());

    }

    public ArrayList<String> generatePossibilities() {
        ArrayList<String> possibilities = new ArrayList<String>();
        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                for (int c = 0; c < 10; c++) {
                    for (int d = 0; d < 10; d++) {
                        for (int e = 0; e < 10; e++) {
                            for (int f = 0; f < 10; f++) {
                                String possibility= numbers[a]+numbers[b]+numbers[c]+numbers[d]+numbers[e]+numbers[f];
                                possibilities.add(possibility);

                            }
                        }
                    }
                }
            }
        }
        return possibilities;
    }
    public static int[] comparePossibilities(String currentGuess, String possibleGuess){
        int directHits=0;
        int indirectHits=0;

        char[] current=currentGuess.toCharArray();
        char[] possible=possibleGuess.toCharArray();

        //comparison for directHits
        for(int i=0;i<currentGuess.length();i++){
            if(currentGuess.charAt(i)==possible[i]){
                directHits++;

                current[i]='\0';
                possible[i]='\0';
            }
        }

        //comparison for indirectHits
        for(int j=0;j<current.length;j++){ //nested for loops because we don't care about position
            for(int k=0;k<possible.length;k++){
                if(current[j]==possible[k] && current[k]!='\0' && current[j]!='\0'){
                    possible[k]='\0';
                    indirectHits++;
                }
            }
        }

        int[] comparison={directHits,indirectHits};

        return comparison;

    }
    public void generateGuess(int indirectHits, int directHits) {
        for(int i = 0; i< allPossibilities.size(); i++){
            int[] feedbacks= comparePossibilities(prewGuess, allPossibilities.get(i));
            if(feedbacks[0]!=directHits || feedbacks[1]!=indirectHits){
                allPossibilities.remove(i);
                i--;
            }
        }
    }
    public void reset() {

        allPossibilities.clear();


    }


    public String getNextGuess() {
        prewGuess = allPossibilities.get(0);

        return allPossibilities.remove(0);
    }


}