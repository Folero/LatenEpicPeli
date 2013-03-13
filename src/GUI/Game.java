package GUI;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {

    Image plane = null;
    Image land = null;
    float x = 400;
    float y = 300;
    float speed = 0.1f;

    public Game() {
        super("Eeppinen seikkailupeli");
    }

    @Override
    public void init(GameContainer gc)
            throws SlickException {
        plane = new Image("images/hero.png");
        land = new Image("images/land.jpg");

    }

    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_A)) {
            x -= speed * delta;
        } else if (input.isKeyDown(Input.KEY_D)) {
            x += speed * delta;
        } else if (input.isKeyDown(Input.KEY_W)) {
            y -= speed * delta;
        } else if (input.isKeyDown(Input.KEY_S)) {
            y += speed * delta;
        }
    }

    public void render(GameContainer gc, Graphics g)
            throws SlickException {
        land.draw(0, 0);

        plane.draw(x, y);

    }

    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app =
                new AppGameContainer(new Game());

        app.setDisplayMode(800, 600, false);
        app.start();
    }
}