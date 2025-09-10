package me.manhunt.singletons;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class TextMaker {
    private final TextComponent text;

    public static TextComponent makeBasicText(ChatColor color, String text) {
        TextComponent output = new TextComponent(text);

        output.setColor(color.asBungee());

        return output;
    }

    private TextMaker(Builder builder) {
        Builder.TextData first = builder.first();

        text = new TextComponent(first.getText());

        text.setColor(first.getInitialColor().asBungee());

        if (builder.data.size() == 1)
            return;

        for (int i = 1; i < builder.data.size(); i++) {
            Builder.TextData data = builder.data.get(i);

            TextComponent subComponent = new TextComponent(data.getText());

            subComponent.setColor(data.getInitialColor().asBungee());

            if (data.isHoverable()) {
                TextComponent newLine = new TextComponent(ComponentSerializer.parse("{text: \"\n\"}"));
                TextComponent hoverComponent = new TextComponent();

                for (int ii = 0; ii < data.getHoverText().size(); ii++) {
                    String item = data.getHoverText().get(ii);

                    TextComponent temp = new TextComponent(item);

                    if (ii < data.getHoverText().size() - 1)
                        temp.addExtra(newLine);

                    hoverComponent.addExtra(temp);
                }

                Text hoverText = new Text(hoverComponent);

                subComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText));
            }

            if (data.isClickable())
                subComponent.setClickEvent(new ClickEvent(data.getClickAction(), data.getClickText()));

            text.addExtra(subComponent);
        }
    }

    public TextComponent getComponent() {
        return text;
    }

    public static final class Builder {
        private final List<TextData> data;

        public Builder(@NotNull Object initialText) {
            this(ChatColor.WHITE, initialText);
        }

        public Builder(@NotNull ChatColor initialColor, @NotNull Object initialText) {
            data = new ArrayList<>();

            addText(initialColor, initialText);
        }

        public Builder addText(@NotNull Object text) {
            return addText(data.get(data.size() - 1).initialColor, text);
        }

        public Builder addText(@NotNull ChatColor initialColor, @NotNull Object text) {
            data.add(new TextData(initialColor, text.toString()));

            return this;
        }

        public Builder addHoverableText(@NotNull Object text, Object[] hoverText) {
            return addHoverableText(data.get(data.size() - 1).initialColor, text, hoverText);
        }

        public Builder addHoverableText(@NotNull ChatColor initialColor, @NotNull Object text, Object[] hoverText) {
            data.add(new TextData(initialColor, text.toString(), hoverText));

            return this;
        }

        public Builder addClickableText(@NotNull Object text, String clickCommand) {
            return addClickableText(data.get(data.size() - 1).initialColor, text, ClickEvent.Action.RUN_COMMAND, clickCommand);
        }

        public Builder addClickableText(@NotNull Object text, ClickEvent.Action clickAction, String clickText) {
            return addClickableText(data.get(data.size() - 1).initialColor, text, clickAction, clickText);
        }

        public Builder addClickableText(@NotNull ChatColor initialColor, @NotNull Object text, String clickCommand) {
            return addClickableText(initialColor, text, ClickEvent.Action.RUN_COMMAND, clickCommand);
        }

        public Builder addClickableText(@NotNull ChatColor initialColor, @NotNull Object text, ClickEvent.Action clickAction, String clickText) {
            data.add(new TextData(initialColor, text.toString(), clickAction, clickText));

            return this;
        }

        public Builder addHoverableAndClickableText(@NotNull Object text, Object[] hoverText, String clickCommand) {
            return addHoverableAndClickableText(data.get(data.size() - 1).initialColor, text, hoverText, ClickEvent.Action.RUN_COMMAND, clickCommand);
        }

        public Builder addHoverableAndClickableText(@NotNull Object text, Object[] hoverText, ClickEvent.Action clickAction, String clickText) {
            return addHoverableAndClickableText(data.get(data.size() - 1).initialColor, text, hoverText, clickAction, clickText);
        }

        public Builder addHoverableAndClickableText(@NotNull ChatColor initialColor, @NotNull Object text, Object[] hoverText, String clickCommand) {
            return addHoverableAndClickableText(initialColor, text.toString(), hoverText, ClickEvent.Action.RUN_COMMAND, clickCommand);
        }

        public Builder addHoverableAndClickableText(@NotNull ChatColor initialColor, @NotNull Object text, Object[] hoverText, ClickEvent.Action clickAction, String clickText) {
            data.add(new TextData(initialColor, text.toString(), hoverText, clickAction, clickText));

            return this;
        }

        public TextMaker toText() {
            return new TextMaker(this);
        }

        private TextData first() {
            return data.getFirst();
        }

        private static final class TextData {
            private final ChatColor initialColor;
            private final String text;
            private final List<String> hoverText;
            private final ClickEvent.Action clickAction;
            private final String clickText;

            public TextData(@NotNull ChatColor initialColor, @NotNull String text) {
                this(initialColor, text, new Object[0], null, null);
            }

            public TextData(@NotNull ChatColor initialColor, @NotNull String text, Object[] hoverText) {
                this(initialColor, text, hoverText, null, null);
            }

            public TextData(@NotNull ChatColor initialColor, @NotNull String text, ClickEvent.Action clickAction, String clickText) {
                this(initialColor, text, new Object[0], clickAction, clickText);
            }

            public TextData(@NotNull ChatColor initialColor, @NotNull String text, Object[] hoverText, ClickEvent.Action clickAction, String clickText) {
                this.initialColor = initialColor;
                this.text = text;
                this.hoverText = new ArrayList<>();
                this.clickAction = clickAction;
                this.clickText = clickText;

                for (Object item : hoverText) {
                    if (item == null)
                        continue;

                    this.hoverText.add(item.toString());
                }
            }

            public ChatColor getInitialColor() {
                return initialColor;
            }

            public String getText() {
                return text;
            }

            public boolean isHoverable() {
                return !hoverText.isEmpty();
            }

            public List<String> getHoverText() {
                return hoverText;
            }

            public boolean isClickable() {
                return clickAction != null && clickText != null;
            }

            public ClickEvent.Action getClickAction() {
                return clickAction;
            }

            public String getClickText() {
                return clickText;
            }
        }
    }
}
