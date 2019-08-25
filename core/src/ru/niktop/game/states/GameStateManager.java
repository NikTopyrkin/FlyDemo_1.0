//Класс GameStateManager отвечает за окна.
//Метод push, помещает элемент в вершину стэка;
//Метод pop извлекает верхний элемент, удаляя его из стэка;
//Метод set, удаляет из стэка верхний экран, очищает его ресурсы
//и помещает следующий экран в вершину стэка;
//Метод upDate, через определенные промежутки, выполняет метод peek;
//Метод render, отрисовывает игровое поле;
package ru.niktop.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }

    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void upDate(float dt){
        states.peek().upDate(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
