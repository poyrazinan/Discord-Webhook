package xyz.geik.webhook.discord;

import lombok.Getter;
import lombok.Setter;
import xyz.geik.webhook.discord.utils.EmbedObject;
import xyz.geik.webhook.discord.utils.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Webhook implements Cloneable {

    List<EmbedObject> embeds = new ArrayList<>();

    String dataId, url, userName, photoUrl, content;

    public Webhook(String dataId, String url, String userName, String photoUrl,
                   String thumbnail, String title, String author,
                   String description, String content, String footer, GColor color, String webUrl) {
        this.dataId = dataId;
        this.url = url;
        this.photoUrl = photoUrl;
        this.userName = userName;
        this.content = content;
        addEmbed(new EmbedObject()
                .setTitle(title)
                .setDescription(description)
                .setAuthor(author, webUrl, photoUrl)
                .setColor(getColor(color))
                .setThumbnail(thumbnail)
                .setFooter(footer, "")
                .setUrl(webUrl));
    }
    public Webhook clone() {
        try {
            return (Webhook) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addEmbed(EmbedObject embed) {
        this.embeds.add(embed);
    }

    public enum GColor {
        ORANGE,
        RED,
        BLACK,
        GREEN,
        YELLOW,
        CYAN,
        WHITE,
        BLUE
    }

    private static Color getColor(GColor color) {
        Color color1 = Color.BLUE;
        switch (color) {
            case ORANGE:
                color1 = Color.ORANGE;
                break;
            case RED:
                color1 = Color.RED;
                break;
            case BLACK:
                color1 = Color.BLACK;
                break;
            case GREEN:
                color1 = Color.GREEN;
                break;
            case YELLOW:
                color1 = Color.YELLOW;
                break;
            case CYAN:
                color1 = Color.CYAN;
                break;
            case WHITE:
                color1 = Color.WHITE;
                break;
            case BLUE:
                color1 = Color.BLUE;
                break;
        }
        return color1;
    }

    public void execute() {
        try {
            if (this.content == null && this.embeds.isEmpty()) {
                throw new IllegalArgumentException("Set content or add at least one EmbedObject");
            }

            JSONObject json = new JSONObject();

            json.put("username", this.getUserName());
            json.put("avatar_url", this.getPhotoUrl());
            json.put("tts", false);

            if (!this.embeds.isEmpty()) {
                List<JSONObject> embedObjects = new ArrayList<>();

                for (EmbedObject embed : this.embeds) {
                    JSONObject jsonEmbed = new JSONObject();

                    if (embed.getTitle() != null && !embed.getTitle().equals(""))
                        jsonEmbed.put("title", embed.getTitle());

                    if (embed.getDescription() != null && !embed.getDescription().equals(""))
                        jsonEmbed.put("description", embed.getDescription());
                    jsonEmbed.put("url", embed.getUrl());

                    if (embed.getColor() != null) {
                        Color color = embed.getColor();
                        int rgb = color.getRed();
                        rgb = (rgb << 8) + color.getGreen();
                        rgb = (rgb << 8) + color.getBlue();

                        jsonEmbed.put("color", rgb);
                    }

                    EmbedObject.Footer footer = embed.getFooter();
                    EmbedObject.Image image = embed.getImage();
                    EmbedObject.Thumbnail thumbnail = embed.getThumbnail();
                    EmbedObject.Author author = embed.getAuthor();
                    List<EmbedObject.Field> fields = embed.getFields();

                    if (footer != null && !footer.equals("")) {
                        JSONObject jsonFooter = new JSONObject();

                        jsonFooter.put("text", footer.getText());
                        jsonFooter.put("icon_url", footer.getIconUrl());
                        jsonEmbed.put("footer", jsonFooter);
                    }

                    if (image != null && !image.equals("")) {
                        JSONObject jsonImage = new JSONObject();

                        jsonImage.put("url", image.getUrl());
                        jsonEmbed.put("image", jsonImage);
                    }

                    if (thumbnail != null && !thumbnail.equals("")) {
                        JSONObject jsonThumbnail = new JSONObject();

                        jsonThumbnail.put("url", thumbnail.getUrl());
                        jsonEmbed.put("thumbnail", jsonThumbnail);
                    }

                    if (author != null && !author.equals("")) {
                        JSONObject jsonAuthor = new JSONObject();

                        jsonAuthor.put("name", author.getName());
                        jsonAuthor.put("url", author.getUrl());
                        jsonAuthor.put("icon_url", author.getIconUrl());
                        jsonEmbed.put("author", jsonAuthor);
                    }

                    List<JSONObject> jsonFields = new ArrayList<>();
                    for (EmbedObject.Field field : fields) {
                        JSONObject jsonField = new JSONObject();

                        jsonField.put("name", field.getName());
                        jsonField.put("value", field.getValue());
                        jsonField.put("inline", field.isInline());

                        jsonFields.add(jsonField);
                    }

                    jsonEmbed.put("fields", jsonFields.toArray());
                    embedObjects.add(jsonEmbed);
                }

                json.put("embeds", embedObjects.toArray());
            }

            if (this.content != null && !this.content.equals(""))
                json.put("content", this.content);

            URL url = new URL(this.url);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            OutputStream stream = connection.getOutputStream();
            stream.write(json.toString().getBytes(StandardCharsets.UTF_8));
            stream.flush();
            stream.close();
            connection.getInputStream().close();
            connection.disconnect();
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}