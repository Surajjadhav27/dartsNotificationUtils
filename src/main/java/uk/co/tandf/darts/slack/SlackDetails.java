package uk.co.tandf.darts.slack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SlackDetails {
    private static final Logger LOG = LogManager.getLogger(SlackDetails.class);
    private String channelName;
    private String username;
    private String text;
    private String icon_emoji;

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon_emoji() {
        return this.icon_emoji;
    }

    public void setIcon_emoji(String icon_emoji) {
        this.icon_emoji = icon_emoji;
    }

    public SlackDetails(String channelName, String username, String icon_emoji) {
        this.channelName = channelName;
        this.username = username;
        this.icon_emoji = icon_emoji;
    }

    public SlackDetails(String username, String icon_emoji) {
        this.username = username;
        this.icon_emoji = icon_emoji;
    }

    public SlackDetails() {
    }

    public String toString() {
        return "SlackDetails{channelName='" + this.channelName + '\'' + ", username='" + this.username + '\'' + ", text='" + this.text + '\'' + ", icon_emoji='" + this.icon_emoji + '\'' + '}';
    }
}
