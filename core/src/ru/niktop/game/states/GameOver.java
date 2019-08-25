package ru.niktop.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.niktop.game.FlyDemo;

public class GameOver extends State {

    private Texture background;
    private Texture gameOver;

    public GameOver(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlyDemo.WIDTH / 2, FlyDemo.HEIGHT / 2);
        background = new Texture("nightcity3.png");
        gameOver = new Texture("gameover.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void upDate(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(gameOver, camera.position.x - gameOver.getWidth() / 2, camera.position.y );
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        gameOver.dispose();

        System.out.println("GameOver Disposed");

    }
}
