package game.Actor;

import game.tiles.TileType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Challenge {

    private ArrayList<TapeCase> _cases;
    private int _currentIndex;
    private String _challengeInfo;

    public Challenge() {
        _cases = new ArrayList<>();
        _currentIndex = 0;
        _challengeInfo = "";
    }

    private void setChallengeInfo(String text) {
        _challengeInfo = text;
    }

    public String getChallengeInfo() {
        return _challengeInfo;
    }

    private void addCase(TapeCase tcase) {
        _cases.add(tcase);
    }

    public void reset() {
        _currentIndex = 0;

        for (int i = 0; i < _cases.size(); i++)
            _cases.get(i).reset();
    }

    public TapeCase consumeCase() {

        if (challengeCompleted())
            return null;

        return _cases.get(_currentIndex++);
    }

    public boolean challengeCompleted() {
        return _currentIndex == _cases.size();
    }


    public static Challenge parseChallenge(File file) throws IOException, NumberFormatException {

        if (file == null)
            throw new IOException("No file supplied");

        Scanner scanner = new Scanner(new FileInputStream(file));

        Challenge challenge = new Challenge();
        challenge.setChallengeInfo(scanner.nextLine());

        int numCases = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numCases; i++) {
            String passFail = scanner.nextLine();
            String tape = scanner.nextLine();

            TapeCase tcase = new TapeCase();
            tcase.setPassFail(passFail.charAt(0) == 'P');

            for (int j = 0; j < tape.length(); j++) {
                if (tape.charAt(j) == 'B')
                    tcase.addTape(TapeCase.Tape.BLUE);
                else
                    tcase.addTape(TapeCase.Tape.RED);
                tcase.appendString(tape.charAt(j));
            }

            challenge.addCase(tcase);
        }

        return challenge;
    }
}
