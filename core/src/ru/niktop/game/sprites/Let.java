//Константа FLUCTUATION - диапозон отклонений, при создании препятсвий;
//Константа LET_GAP - хранит значений высоты просвета;
//Константа LOWEST_OPENING - хранит границу высоты нижнего просвета;
//Метод collides - отвечает за обнаружение столкновения объектов;

package ru.niktop.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Let {

    public static final int LET_WIDTH = 52;

    public static final int FLUCTUATION = 130;
    public static final int LET_GAP = 100;
    public static final int LOWEST_OPENING = 120;

    private Texture build, sturm;
    private Vector2 posbuild, posSturm;
    private Random random;
    private Rectangle boundsSturm, boundsBuild;

    public Texture getBuild() {
        return build;
    }

    public Texture getSturm() {
        return sturm;
    }

    public Vector2 getPosbuild() {
        return posbuild;
    }

    public Vector2 getPosSturm() {
        return posSturm;
    }

    public Let(float x){
        build = new Texture("build.png");
        sturm = new Texture("sturm.png");
        random = new Random();

        posSturm = new Vector2(x, random.nextInt(FLUCTUATION) + LET_GAP + LOWEST_OPENING);
        posbuild = new Vector2(x, posSturm.y - LET_GAP - build.getHeight());

        boundsSturm = new Rectangle(posSturm.x, posSturm.y, sturm.getWidth(), sturm.getHeight());
        boundsBuild = new Rectangle(posbuild.x, posbuild.y, build.getWidth(), build.getHeight());
    }

    public void reposition(float x){
        posSturm.set(x, random.nextInt(FLUCTUATION) + LET_GAP + LOWEST_OPENING);
        posbuild.set(x, posSturm.y - LET_GAP - build.getHeight());

        boundsSturm.setPosition(posSturm.x, posSturm.y);
        boundsBuild.setPosition(posbuild.x, posbuild.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsSturm) || player.overlaps(boundsBuild);
    }

    public void dispose() {
        build.dispose();
        sturm.dispose();
    }
}
