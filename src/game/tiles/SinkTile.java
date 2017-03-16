package game.tiles;

import game.Actor.MovementBehavior.MovementBehavior;
import game.Actor.Robot;

public class SinkTile extends PlayTile {

    public SinkTile() {
        super();
        this.getChildren().add(ImageLookupService.getTileImageView(ImageType.SINK));
    }

    @Override
    public MovementBehavior getBehavior(Robot robot) {
        return null;
    }
}
