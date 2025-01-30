//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package uk.co.tandf.darts.ms.teams;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Section {
    private String title;
    private String activityTitle;
    private String activitySubtitle;
    private String activityImage;
    private String activityText;
    private String text;

    public Section() {
    }

    public Section(String text) {
        this.text = text;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivityTitle() {
        return this.activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivitySubtitle() {
        return this.activitySubtitle;
    }

    public void setActivitySubtitle(String activitySubtitle) {
        this.activitySubtitle = activitySubtitle;
    }

    public String getActivityImage() {
        return this.activityImage;
    }

    public void setActivityImage(String activityImage) {
        this.activityImage = activityImage;
    }

    public String getActivityText() {
        return this.activityText;
    }

    public void setActivityText(String activityText) {
        this.activityText = activityText;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString() {
        return "Section{title='" + this.title + '\'' + ", activityTitle='" + this.activityTitle + '\'' + ", activitySubtitle='" + this.activitySubtitle + '\'' + ", activityImage='" + this.activityImage + '\'' + ", activityText='" + this.activityText + '\'' + ", text='" + this.text + '\'' + '}';
    }
}
