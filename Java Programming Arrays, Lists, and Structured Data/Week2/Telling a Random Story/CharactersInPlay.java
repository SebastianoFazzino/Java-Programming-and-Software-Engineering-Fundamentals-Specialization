import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> count;


    public CharactersInPlay() {
        /*
        The constructor assigns to the object two new ArrayLists
        that are used to count characters
         */
        characters = new ArrayList<String>();
        count = new ArrayList<Integer>();
    }

    private void update(String person) {
        // This method add a new character to characters and update the character count
        int index = characters.indexOf(person);
        if ( index == -1 ) {
            characters.add(person);
            count.add(1);
        }
        else {
            int value = count.get(index);
            count.set(index, value + 1);
        }
    }

    public void findAllCharacters() {
        // This method is used to find characters in macbeth.txt
        FileResource resource = new FileResource();
        characters.clear();
        count.clear();

        for (String l : resource.lines()) {
            int period = l.indexOf(".");
            if ( period != -1 ) {
                String person = l.substring(0, period);
                update(person);
            }
            else {
                continue;
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        // This method prints only character with a number of parts between num1 and num2
        for ( int i = 0; i < count.size(); i++ ) {
            if ( count.get(i) > num1 && count.get(i) < num2 ) {
                System.out.println(characters.get(i) + ": " + count.get(i));
            }
        }
    }


    public void tester() {
        // We print all characters found together with their number of parts
        findAllCharacters();
        System.out.println("# Characters: " + characters.size());
        for ( int i = 0; i < characters.size(); i++) {
            if ( count.get(i) > 1 ) {
                System.out.println(characters.get(i) + ": " + count.get(i));
            }
        }
        // Calling charactersWithNumParts, we print characters with a number of parts between 3 and 7
        charactersWithNumParts(3, 7);
    }


    public static void main(String[] args) {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
    }
}
