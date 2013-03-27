package GUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

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
    
    public Hero(float x, float y, int width, int height, Image[][] image) {
        position = new Vector2f(x, y);
        rectangle = new Rectangle(x, y, width, height);
        hero = image[S][STILL];
        heroImages = new Image[4][3];
        setImages(image);
    }

    public void update(GameContainer gc, int mapWidth, int mapHeight, int delta, int tileWidth, int tileHeight) {
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
            trans.y = -1f * delta;
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
            trans.y = 1f * delta;
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
            trans.x = 1f * delta;
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
            trans.x = -1f * delta;
        } else {
            currentFrame = 11;
        }       
        
        if (trans.x != 0 && trans.y != 0) { 
            trans.set(trans.x / 1.5f, trans.y / 1.5f);
        }

        // Tarkistuksella varmistetaan, että sankari pysyy kartan sisällä.
        if (position.x + trans.x > tileWidth && position.x + trans.x < (mapWidth - (2*tileWidth))) {
            position.x += trans.x;
        }

        // Tarkistuksella varmistetaan, että sankari pysyy kartan sisällä.
        if (position.y + trans.y > tileHeight && position.y + trans.y < (mapHeight - (4*tileHeight))) {
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
}