package game.tiles;

import game.Actor.MovementBehavior.MovementBehavior;
import game.Actor.Robot;

public class DownSwitchTile extends PlayTile{

    public DownSwitchTile() {
        super();
        this.getChildren().add(ImageLookupService.getTileImageView(ImageType.DOWNSWITCH));
    }

    @Override
    public MovementBehavior getBehavior(Robot robot) {

        return MovementBehavior.DOWN;
    }
}
