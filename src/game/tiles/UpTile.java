package game.tiles;

import game.Actor.MovementBehavior.MovementBehavior;
import game.Actor.Robot;

public class UpTile extends PlayTile {

    public UpTile() {
        super();
        this.getChildren().add(ImageLookupService.getTileImageView(ImageType.UP));
    }

    @Override
    public MovementBehavior getBehavior(Robot robot) {
        return null;
    }
}
