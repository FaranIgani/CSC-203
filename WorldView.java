import processing.core.PApplet;
import processing.core.PImage;

import java.util.Optional;

public final class WorldView {
    private PApplet screen;
    private WorldModel world;
    private int tileWidth;
    private int tileHeight;
    private Viewport viewport;


    public Viewport viewport(){return this.viewport;}
    public WorldView(int numRows, int numCols, PApplet screen, WorldModel world, int tileWidth, int tileHeight) {
        this.screen = screen;
        this.world = world;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.viewport = new Viewport(numRows, numCols);
    }
    public void drawBackground(WorldView view) {
        for (int row = 0; row < view.viewport.numRows(); row++) {
            for (int col = 0; col < view.viewport.numCols(); col++) {
                Point worldPoint = view.viewport.viewportToWorld(col, row);
                Optional<PImage> image = view.world.getBackgroundImage(worldPoint);
                if (image.isPresent()) {
                    view.screen.image(image.get(), col * view.tileWidth, row * view.tileHeight);
                }
            }
        }
    }

    public void drawEntities(WorldView view) {
        for (Entity entity : view.world.getEntities()) {
            Point pos = entity.getPosition();

            if (view.viewport.contains(pos)) {
                Point viewPoint = view.viewport.worldToViewport(pos.x(), pos.y());
                view.screen.image(entity.getCurrentImage(), viewPoint.x() * view.tileWidth, viewPoint.y() * view.tileHeight);
            }
        }
    }
    public void drawViewport() {
        drawBackground(this);
        drawEntities(this);
    }
    public void shiftView(int colDelta, int rowDelta) {
        int newCol = clamp(this.viewport.col() + colDelta, 0, this.world.getNumCols() - this.viewport.numCols());
        int newRow = clamp(this.viewport.row() + rowDelta, 0, this.world.getNumRows() - this.viewport.numRows());

        this.viewport.shift(newCol, newRow);
    }
    public int clamp(int value, int low, int high) {
        return Math.min(high, Math.max(value, low));
    }
}
