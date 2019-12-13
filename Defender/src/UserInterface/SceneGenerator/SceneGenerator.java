package UserInterface.SceneGenerator;

import GameObjects.*;
import UserInterface.MyApplication;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class SceneGenerator extends Scene {
    static private SceneGenerator sceneGenerator = null;

    private int width;
    private int height;
    //private Canvas canvas;
    private Image bgImage;
    private GraphicsContext graphics;

    synchronized static public SceneGenerator getInstance() {
        if (sceneGenerator == null) {
                Group root = new Group();
                sceneGenerator = new SceneGenerator( root);
        }
        return sceneGenerator;
    }

    private SceneGenerator (Group root) {
        super(root);
        width = MyApplication.WIDTH;
        height = MyApplication.HEIGHT;

        Canvas canvas = new Canvas(width, height);
        graphics = canvas.getGraphicsContext2D();
        graphics.setFont(Font.font("Times New Roman", FontWeight.BOLD, 28));
        graphics.setFill(Color.WHITE);

        bgImage = new Image("bg_image.jpg");

        root.getChildren().add(canvas);
    }

    public void createMap(MotherShip motherShip, ArrayList<Alien> aliens, ArrayList<Human> humans) {
        updateMap(motherShip, aliens, humans, null, 0, 1, 100);
    }

    public void updateMap(MotherShip motherShip, ArrayList<Alien> aliens, ArrayList<Human> humans,
                          ArrayList<Projectile> projectiles, int score, int level, int health) {
        graphics.drawImage(bgImage, 0, 0, width, height);
        graphics.fillText("Score: " + score, 80, 35);
        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.CENTER);
        graphics.fillText("Level: " + level, MyApplication.WIDTH/2, 35);
        graphics.fillText("Health: " + health, 500, 35);

        if(motherShip.getDirection() == MotherShip.moveDirection.RIGHT)
            graphics.drawImage(motherShip.getImage(), motherShip.getX() - 15, motherShip.getY() - 15);
        else
            graphics.drawImage(motherShip.getImage(), motherShip.getX() + 15, motherShip.getY() + 15, -30, -30);

        for(Alien alien : aliens)
            graphics.drawImage(alien.getImage(), alien.getX() - 15, alien.getY() - 15);

        for(Human human : humans)
            graphics.drawImage(human.getImage(), human.getX() - 15, human.getY() - 15);

        if(projectiles != null)
            for(Projectile projectile : projectiles) {
                if(projectile.getDirection() == Projectile.moveDirection.RIGHT)
                    graphics.drawImage(projectile.getImage(), projectile.getX() - 5, projectile.getY() - 5);
                else
                    graphics.drawImage(projectile.getImage(), projectile.getX() + 5, projectile.getY() + 5, -10, -10);
            }
    }
}
