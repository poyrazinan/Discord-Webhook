package xyz.geik.webhook.discord;

public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        Webhook webhook = new Webhook("dataId", "https://discord.com/api/webhooks/840009672756559883/q5dn6sZNBmWyDRm2_QpBYSIB_uno9yMnoUrOFG21suN_c7w7g9tanWFJbrkkp1rWzauw",
                "username", "https://cdn.discordapp.com/attachments/901908081569579029/901908177833058314/logo.png",
                "https://cdn.discordapp.com/attachments/901908081569579029/901908177833058314/logo.png", "title",
                "author", "description", "content", "footer", Webhook.GColor.CYAN, "https://geik.xyz");
        Webhook x = webhook.clone();
        x.execute();

    }
}
