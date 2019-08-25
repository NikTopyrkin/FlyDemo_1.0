//Константа LET_SPACING - расстояние по ширине между препятствиями;
//Константа LET_COUNT - комплекты препятствий;
//Константа ROAD_Y_OFFSET - опускает текстуру дорооги;
//Метод updateRoad - создает непрерывность текстуры road;
//(После того, как одна текстура уходит, сразу появляется другая и так по кругу);

package ru.niktop.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import ru.niktop.game.FlyDemo;
import ru.niktop.game.sprites.Fly;
import ru.niktop.game.sprites.Let;

public class PlayState extends State {

    private static final int LET_SPACING = 125;
    private static final int LET_COUNT = 8;
    private static final int ROAD_Y_OFFSET = -30;


    private Fly fly;
    private Texture fone;
    private Texture road;
    private Vector2 roadPos1, roadPos2;

    private Array<Let> lets;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        fly = new Fly(50, 300);
        camera.setToOrtho(false, FlyDemo.WIDTH /2, FlyDemo.HEIGHT / 2);
        fone = new Texture("fone.png");
        road = new Texture("road.png");
        roadPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, ROAD_Y_OFFSET);
        roadPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + road.getWidth(), ROAD_Y_OFFSET);

        lets = new Array<Let>();

        for (int i = 0; i < LET_COUNT; i++){
            lets.add(new Let(i * (LET_SPACING + Let.LET_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            fly.jump();

    }

    @Override
    public void upDate(float dt) {
        handleInput();
        updateRoad();
        fly.update(dt);
        camera.position.x = fly.getPosition().x + 80;

        for (int i =  0; i < lets.size; i++){

            Let let = lets.get(i);

            if (camera.position.x - (camera.viewportWidth / 2) > let.getPosSturm().x
                    + let.getSturm().getWidth()){
                let.reposition(let.getPosSturm().x + ((let.LET_WIDTH + LET_SPACING) * LET_COUNT));
            }

            if (let.collides(fly.getBounds()))
                gsm.set(new GameOver(gsm));
        }

        camera.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(fone, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(fly.getFly(), fly.getPosition().x, fly.getPosition().y);
        for (Let let : lets) {
            sb.draw(let.getBuild(), let.getPosSturm().x, let.getPosbuild().y);
            sb.draw(let.getSturm(), let.getPosSturm().x, let.getPosSturm().y);
        }
        sb.draw(road, roadPos1.x,roadPos1.y);
        sb.draw(road, roadPos2.x, roadPos2.y);

        sb.end();

    }

    @Override
    public void dispose() {
        fone.dispose();
        fly.dispose();
        road.dispose();
        for (Let let : lets)
            let.dispose();
        System.out.println("PlayState Disposed");

    }

    private void updateRoad(){
        if (camera.position.x - (camera.viewportWidth / 2) > roadPos1.x + road.getWidth())
            roadPos1.add(road.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > roadPos2.x + road.getWidth())
            roadPos2.add(road.getWidth() * 2, 0);
    }
}
