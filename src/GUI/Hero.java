package GUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class Hero {

    protected Vector2f position;
    protected Rectangle rectangle;
    protected Image hero;
    protected Image[][] heroImages;
    public static final int D = 0;
    public static final int W = 1;
    public static final int A = 2;
    public static final int S = 3;
    public static final int STILL = 0;
    public static final int MOVELEFT = 1;
    public static final int MOVERIGHT = 2;
    private int currentFrame = 11;
    private static final float SPEED = 0.4f;
    boolean[][] blocked;
    private TiledMap map;

    public Hero(float x, float y, int width, int height, Image[][] image, TiledMap map) {
        position = new Vector2f(x, y);
        rectangle = new Rectangle(x, y, width, height);
        hero = image[S][STILL];
        heroImages = new Image[4][3];
        setImages(image);
        this.map = map;
        blocked = new boolean[map.getWidth()][map.getHeight()];
        initializeBlocked();
    }

    public void update(GameContainer gc, int delta) {
        Vector2f trans = new Vector2f(0, 0);
        Input input = gc.getInput();

        //Näillä toiminnoilla mahdollistetaan sankarin liikkuminen kartalla.
        if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
            currentFrame++;
            if (currentFrame == 12) {
                if (hero == heroImages[W][MOVELEFT]) {
                    hero = heroImages[W][MOVERIGHT];
                } else {
                    hero = heroImages[W][MOVELEFT];
                }
                currentFrame = 0;
            }
            if (!isBlocked(getX() + map.getTileWidth(), getY() - delta * SPEED) && !isBlocked(getX(), getY() - delta * SPEED)) {
                trans.y -= SPEED * delta;
            }

        } else if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
            currentFrame++;
            if (currentFrame == 12) {
                if (hero == heroImages[S][MOVELEFT]) {
                    hero = heroImages[S][MOVERIGHT];
                } else {
                    hero = heroImages[S][MOVELEFT];
                }
                currentFrame = 0;
            }
            if (!isBlocked(getX() + map.getTileWidth(), getY() + map.getTileHeight() + delta * SPEED) && !isBlocked(getX(), getY() + map.getTileHeight() + delta * SPEED)) {
                trans.y += delta * SPEED;
            }
        } else if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
            currentFrame++;
            if (currentFrame == 12) {
                if (hero == heroImages[D][MOVELEFT]) {
                    hero = heroImages[D][MOVERIGHT];
                } else {
                    hero = heroImages[D][MOVELEFT];
                }
                currentFrame = 0;
            }
            if (!isBlocked(getX() + map.getTileWidth() + delta * SPEED, getY() + map.getTileHeight()) && !isBlocked(getX() + map.getTileWidth() + delta * SPEED, getY())) {
                trans.x += delta * SPEED;
            }
        } else if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
            currentFrame++;
            if (currentFrame == 12) {
                if (hero == heroImages[A][MOVELEFT]) {
                    hero = heroImages[A][MOVERIGHT];
                } else {
                    hero = heroImages[A][MOVELEFT];
                }
                currentFrame = 0;
            }
            if (!isBlocked(getX() - delta * SPEED, getY()) && !isBlocked(getX() - delta * SPEED, getY() + map.getTileHeight())) {
                trans.x -= delta * SPEED;
            }
        } else {
            currentFrame = 11;
        }

        if (trans.x != 0 && trans.y != 0) {
            trans.set(trans.x / 1.5f, trans.y / 1.5f);
        }

        // Tarkistuksella varmistetaan, että sankari pysyy kartan sisällä.
        if (position.x + trans.x > map.getTileWidth() && position.x + trans.x < ((map.getWidth() * map.getTileWidth()) - (2 * map.getTileWidth()))) {
            position.x += trans.x;
        }

        // Tarkistuksella varmistetaan, että sankari pysyy kartan sisällä.
        if (position.y + trans.y > map.getTileHeight() && position.y + trans.y < ((map.getHeight() * map.getTileWidth()) - (4 * map.getTileHeight()))) {
            position.y += trans.y;
        }
    }

    //Renderöidään sankarin kuva peliin. Tämä tehdään aina updaten jälkeen.
    public void render() {
        hero.draw(position.x, position.y);
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Image getImage() {
        return hero;
    }

    private void setImages(Image[][] image) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                heroImages[y][x] = image[y][x];
            }
        }
    }

    private boolean isBlocked(float x, float y) {
        int xBlock = (int) x / map.getTileWidth();
        int yBlock = (int) y / map.getTileHeight();
        return blocked[xBlock][yBlock];
    }

    private void initializeBlocked() {
        for (int l = 0; l < map.getLayerCount(); l++) {
            String layerValue = map.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < map.getWidth(); c++) {
                    for (int r = 0; r < map.getHeight(); r++) {
                        if (map.getTileId(c, r, l) != 0) {
                            blocked[c][r] = true;
                        }
                    }
                }
            }
        }
    }
}