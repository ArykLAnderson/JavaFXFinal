package game.Actor;


import java.util.ArrayList;

public class TapeCase {

    public enum Tape {RED, BLUE, EMPTY};

    private ArrayList<Tape> _tapes = new ArrayList<>();
    private int _currentIndex;
    private boolean _passFail;
    private String _tapeString;

    public TapeCase() {
        _tapes = new ArrayList<>();
        _currentIndex = 0;
        _passFail = false;
        _tapeString = "";
    }

    public String getTapeString() {
        return _tapeString;
    }

    public void setString(String string) {
        _tapeString = string;
    }

    public void appendString(char character) {
        _tapeString += character;
    }

    public boolean isPass(boolean result) {
        return result == _passFail;
    }

    public void setPassFail(boolean p) {
        _passFail = p;
    }

    public void addTape(Tape tape) {
        _tapes.add(tape);
    }

    public Tape consumeTape() {

        if (endOfTape())
            return Tape.EMPTY;

        return _tapes.get(_currentIndex++);
    }

    public void reset() {
        _currentIndex = 0;
    }

    public boolean endOfTape() {
        return _currentIndex == _tapes.size();
    }

}
