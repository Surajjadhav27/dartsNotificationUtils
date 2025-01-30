//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package uk.co.tandf.darts.ms.teams;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TeamsDetails {
    private static final Logger LOG = LogManager.getLogger(TeamsDetails.class);
    private boolean success;
    private String ResponseMessage;
    private String text;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TeamsDetails() {
    }

    public TeamsDetails(boolean success, String ResponseMessage) {
        this.success = success;
        this.ResponseMessage = ResponseMessage;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getResponseMessage() {
        return this.ResponseMessage;
    }
}
