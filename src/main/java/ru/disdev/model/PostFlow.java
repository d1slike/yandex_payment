package ru.disdev.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;
import ru.disdev.VkApi;
import ru.disdev.entity.Post;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Component
@Scope("prototype")
public class PostFlow extends Flow<Post> {
    public PostFlow(long chatId) {
        super(chatId);
    }

    @Override
    public Post getResult() {
        return new Post();
    }

    @Autowired
    public VkApi vkApi;


    @Override
    public void finish() {
        super.finish();
    }

    private Consumer<Message> getTag() {
        return (message -> {
            if (message.hasText()) {
                String text = message.getText();
                result.setTags(text);
                nextState();
            } else
                sendMessage("Введите текст");
        });
    }

    private Consumer<Message> getInformation() {
        return message -> {
            if (message.hasText()) {
                String text = message.getText();
                result.setText(text);
                finish();
            } else
                sendMessage("Введите текст");
        };
    }

    @Override
    public Map<Integer, Action> getStateActions() {
        Map<Integer, Action> map = new HashMap<>();
        map.put(0, new Action(getTag(), "Введите теги через ,"));
        map.put(1, new Action(getInformation(), "Введите текст поста"));
        return map;
    }
}
