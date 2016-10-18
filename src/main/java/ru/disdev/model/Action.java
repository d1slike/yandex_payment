package ru.disdev.model;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;

import java.util.function.Consumer;

public class Action {
    private final Consumer<Message> messageConsumer;
    private final String informationForUser;
    private ReplyKeyboard keyBoard;

    public Action(Consumer<Message> messageConsumer, String informationForUser) {
        this.messageConsumer = messageConsumer;
        this.informationForUser = informationForUser;
    }

    public Action(Consumer<Message> messageConsumer, String informationForUser, ReplyKeyboard keyBoard) {
        this.messageConsumer = messageConsumer;
        this.informationForUser = informationForUser;
        this.keyBoard = keyBoard;
    }

    public Consumer<Message> getMessageConsumer() {
        return messageConsumer;
    }

    public String getInformationForUser() {
        return informationForUser;
    }

    public ReplyKeyboard getKeyBoard() {
        return keyBoard;
    }
}
