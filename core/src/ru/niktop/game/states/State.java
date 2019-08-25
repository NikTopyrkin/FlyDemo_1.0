package ru.niktop.game.states;

//OrthographicCamera camera - окно в нашу игру;
//GameStateManager gsm - управляет окнами;
//метод handleInput() - опрашивает ввод;
//метод upDate() - обновляет изображение, через определенный интервал времени;
//метод render() - отвечает за отрисовку экрана;
//метод dispose() - освобождает ресурсы, уничтожает экземпляр класса;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    public State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void upDate(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();


}
