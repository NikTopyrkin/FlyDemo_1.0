//position - хранит позицию;
//velosity - хранит скорость;
//Rectangle bounds - переменная, отрисовывающая прямоугольник, для обнаружения столкновения;

package ru.niktop.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Fly {
    private static final int MOVEMENT = 100;
    public static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velosity;
    private Rectangle bounds;

    private Texture fly;

    public Fly(int x, int y){
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0,0,0);
        fly = new Texture("fly1.png");
        bounds = new Rectangle(x,y, fly.getWidth(), fly.getHeight());
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getFly() {
        return fly;
    }

    public void update(float dt){
        if (position.y > 0)
            velosity.add(0, GRAVITY,0);
        velosity.scl(dt);
        position.add(MOVEMENT * dt,velosity.y,0);
        if (position.y < 0)
            position.y = 0;

        velosity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);

    }

    public void jump(){
        velosity.y = 250;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose() {
        fly.dispose();
    }
}
