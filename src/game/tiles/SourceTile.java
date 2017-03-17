package game.tiles;

import game.Actor.MovementBehavior.MovementBehavior;
import game.Actor.Robot;

public class SourceTile extends PlayTile {

    private static SourceTile _instance;

    private SourceTile() {
        super();
        this.getChildren().add(ImageLookupService.getTileImageView(ImageType.SOURCE));
    }

    public static SourceTile instance() {

        return new SourceTile();
//        if (_instance == null)
//            _instance = new SourceTile();
//
//        return _instance;
    }

    @Override
    public MovementBehavior getBehavior(Robot robot) {
        return null;
    }
}
