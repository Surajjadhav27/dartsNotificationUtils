//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package uk.co.tandf.darts.ms.teams;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class Card {
    @JsonIgnore
    private String type = "AdaptiveCard";
    @JsonIgnore
    private String context = "https://adaptivecards.io/schemas/adaptive-card.json";
    private String summary = "DARTS CARDS";
    private String themeColor;
    private String title;
    private String text;
    private List<Section> sections;

    public Card(String title) {
        this.title = title;
        this.themeColor = "0078D7";
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContext() {
        return this.context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getThemeColor() {
        return this.themeColor;
    }

    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Section> getSections() {
        return this.sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String toString() {
        return "Card{type='" + this.type + '\'' + ", context='" + this.context + '\'' + ", summary='" + this.summary + '\'' + ", themeColor='" + this.themeColor + '\'' + ", title='" + this.title + '\'' + ", text='" + this.text + '\'' + ", sections=" + this.sections + '}';
    }
}
