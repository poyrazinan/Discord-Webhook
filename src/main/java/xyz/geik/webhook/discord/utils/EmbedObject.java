package xyz.geik.webhook.discord.utils;

import xyz.geik.webhook.discord.Webhook;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * OpenSource code which
 * found on Github
 * <p>
 * Dependency for Webhook
 */
public class EmbedObject implements Cloneable {
    private String title;
    private String description;
    private String url;
    private Color color;
    private Footer footer;
    private Thumbnail thumbnail;
    private Image image;
    private Author author;
    private List<Field> fields = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Color getColor() {
        return color;
    }

    public Footer getFooter() {
        return footer;
    }

    public Author getAuthor() {
        return author;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public Image getImage() {
        return image;
    }

    public List<Field> getFields() {
        return fields;
    }

    public EmbedObject setTitle(String title) {
        this.title = title;
        return this;
    }

    public EmbedObject setDescription(String description) {
        this.description = description;
        return this;
    }

    public EmbedObject setUrl(String url) {
        this.url = url;
        return this;
    }

    public EmbedObject setAuthor(String name, String url, String icon) {
        this.author = new Author(name, url, icon);
        return this;
    }

    public EmbedObject setColor(Color color) {
        this.color = color;
        return this;
    }

    public EmbedObject setFooter(String text, String icon) {
        this.footer = new Footer(text, icon);
        return this;
    }

    public EmbedObject setThumbnail(String url) {
        this.thumbnail = new Thumbnail(url);
        return this;
    }

    public EmbedObject setImage(String url) {
        this.image = new Image(url);
        return this;
    }

    public EmbedObject addField(String name, String value, boolean inline) {
        this.fields.add(new Field(name, value, inline));
        return this;
    }

    public EmbedObject clone() {
        try {
            return (EmbedObject) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public class Footer {
        private String text;
        private String iconUrl;

        private Footer(String text, String iconUrl) {
            this.text = text;
            this.iconUrl = iconUrl;
        }

        public String getText() {
            return text;
        }

        public String getIconUrl() {
            return iconUrl;
        }
    }

    public static class Thumbnail {
        private String url;

        private Thumbnail(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class Image {
        private String url;

        private Image(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class Field {
        private String name;
        private String value;
        private boolean inline;

        private Field(String name, String value, boolean inline) {
            this.name = name;
            this.value = value;
            this.inline = inline;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public boolean isInline() {
            return inline;
        }
    }

    public static class Author {
        private String name;
        private String url;
        private String iconUrl;

        private Author(String name, String url, String iconUrl) {
            this.name = name;
            this.url = url;
            this.iconUrl = iconUrl;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        public String getIconUrl() {
            return iconUrl;
        }
    }
}