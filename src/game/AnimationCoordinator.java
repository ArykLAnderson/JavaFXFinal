package game;

/**
 * Created by aryka on 3/16/2017.
 */
public class AnimationCoordinator {

    private AnimationCoordinator _instance;

    private AnimationCoordinator() {}

    public AnimationCoordinator instance() {

        if (_instance == null)
            _instance = new AnimationCoordinator();

        return _instance;
    }
}
