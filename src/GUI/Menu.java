package GUI;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Music;

public class Menu extends BasicGame {

    static int WIDTH = 1024;
    static int HEIGHT = 760;
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static String title = "menu";
    static int fpslimit = 60;
    Image New;
    Image Load;
    Image Save;
    Image exitGame;
    Image Resume;
    
 
 public String mouse = "No input yet!";
 
    public Menu(String title) {
        super(title);
    }
    

    public void init(GameContainer gc) throws SlickException {
      Resume = new Image("images/MainMenu/Resume.png");
      exitGame = new Image("images/MainMenu/Exit.png");
      New = new Image("images/MainMenu/New.png");
      Load = new Image("images/MainMenu/load.png");
      Save = new Image("images/MainMenu/save.png");
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
      int posX = Mouse.getX();
      int posY = Mouse.getY(); 
    
      mouse = "Mouse position x: " + posX + " y: " + posY;
     
        
      //exit game
      if((posX>101 && posX<317)&&(posY>146 && posY<255)){
         if(Mouse.isButtonDown(0)){
            System.exit(0);
         }
      }
      //New Game
      if((posX>101 && posX<317)&&(posY>347 && posY<454)){
         if(Mouse.isButtonDown(0)){
            
         }
      }
      //Resume
      if((posX>400 && posX<516)&&(posY>546 && posY<654)){
         if(Mouse.isButtonDown(0)){
            
         }
      }
      //Load
      if((posX>400 && posX<516)&&(posY>346 && posY<454)){
         if(Mouse.isButtonDown(0)){
            
         }
      }
      //Save
      if((posX>101 && posX<317)&&(posY>346 && posY<454)){
         if(Mouse.isButtonDown(0)){
            
         }
      }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
      Resume.draw(400,100);
      exitGame.draw(100,500);
      New.draw(100,100);
      Load.draw(400,300);
      Save.draw(100,300);
      
      g.drawString(mouse, 50, 50);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Menu(title));
        app.setDisplayMode(WIDTH, HEIGHT, fullscreen);
        app.setTargetFrameRate(fpslimit);
        app.setVSync(true);
        app.setShowFPS(showFPS);
        app.start();
    }

}