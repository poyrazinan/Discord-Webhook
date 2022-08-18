# GDWebhook

Discord Webhook library which create object and execute it.

## Usage

[![Java CI with Maven](https://github.com/poyrazinan/GDWebhook/actions/workflows/maven.yml/badge.svg)](https://github.com/poyrazinan/GDWebhook/actions/workflows/maven.yml)
[![](https://jitpack.io/v/poyrazinan/GDWebhook.svg)](https://jitpack.io/#poyrazinan/GDWebhook)
### Maven Repo

#### Repository
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

#### Dependency
```xml
<dependency>
    <groupId>com.github.poyrazinan</groupId>
    <artifactId>GDWebhook</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle Repo
#### Repository
```
maven { url 'https://jitpack.io' }
```
#### Dependency
```
implementation 'com.github.poyrazinan:GDWebhook:1.0.0'
```

## Code Example

* Creating object and execute

```java
import xyz.geik.webhook.discord.Webhook;

public static class Test {
    public static void main(String[] args) {
        Webhook webhook = new Webhook(
                "dataId", // Not Necessary
                "webhookUrl", // Webhook url which should be taken from discord integrations tab
                "userName", // Webhook username who sends
                "photoUrl", // Webhook profile photo who sends
                "thumbnail", // Thumbnail photo in embed
                "authorName", // gets photo from photoUrl and gets link from webUrl
                "description", // Embed message
                "content", // which is not in embed.
                "footer", // Footer text
                "color", // Possibilities (ORANGE, RED, BLACK, GREEN, YELLOW, CYAN, WHITE and BLUE )
                "webUrl" // Website url
        );
        // Execute webhook
        webhook.execute();
    }
}
```

## Photo of Webhook

![](webhookexample.png)