package GUI;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends BasicGame {
    
    Image hero = null;
    TiledMap map;
    Image[] heroS = {null, null, null};
    Image[] heroD = {null, null, null};
    Image[] heroA = {null, null, null};
    Image[] heroW = {null, null, null};
    float x = 400;
    float y = 300;
    float speed = 0.07f;
    Music music;
    int currentFrame;

    public Game() {


        super("Eeppinen seikkailupeli");
    }

    @Override
    public void init(GameContainer gc)
            throws SlickException {

        map = new TiledMap("tiledmaps/map.tmx");
        hero = heroW[0];
        currentFrame = 0;
        music = new Music("sounds/musics/biisi.ogg");
        music.play();
        music.loop();
        



        for (int i = 0; i < 3; i++) {
            heroS[i] = new Image("images/heroS0" + i + ".png");
            // TODO muut heroU = new Image("images") ...
        }
        hero = heroS[0];


        for (int i = 0; i < 3; i++) {
            heroD[i] = new Image("images/heroD0" + i + ".png");
            // TODO muut heroU = new Image("images") ...
        }
        hero = heroD[0];

        for (int i = 0; i < 3; i++) {
            heroA[i] = new Image("images/heroA0" + i + ".png");
            // TODO muut heroU = new Image("images") ...
        }
        hero = heroA[0];

        for (int i = 0; i < 3; i++) {
            heroW[i] = new Image("images/heroW0" + i + ".png");
            // TODO muut heroU = new Image("images") ...
        }
       
    }

    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_A)) {
            currentFrame++;
            x -= speed * delta;
            if (currentFrame == 12) {
                if (hero == heroA[1]) {
                    hero = heroA[2];
                } else {
                    hero = heroA[1];
                }
                currentFrame = 0;
            }
        } else if (input.isKeyDown(Input.KEY_D)) {
            currentFrame++;
            x += speed * delta;
            if (currentFrame == 12) {
                if (hero == heroD[1]) {
                    hero = heroD[2];
                } else {
                    hero = heroD[1];
                }
                currentFrame = 0;
            }
        } else if (input.isKeyDown(Input.KEY_W)) {
            currentFrame++;
            y -= speed * delta;
            if (currentFrame == 12) {
                if (hero == heroW[1]) {
                    hero = heroW[2];
                } else {
                    hero = heroW[1];
                }
                currentFrame = 0;
            }
        } else if (input.isKeyDown(Input.KEY_S)) {
            currentFrame++;
            y += speed * delta;
            if (currentFrame == 12) {
                if (hero == heroS[1]) {
                    hero = heroS[2];
                } else {
                    hero = heroS[1];
                }
                currentFrame = 0;
            }
        } else {
            currentFrame = 11;
        }
    }

    public void render(GameContainer gc, Graphics g)
            throws SlickException {
        map.render(0, 0);

        hero.draw(x, y);

    }

    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app =
                new AppGameContainer(new Game());
        app.setDisplayMode(800, 600, false);
        app.start();


    }
}