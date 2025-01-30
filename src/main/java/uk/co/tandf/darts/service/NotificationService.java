package uk.co.tandf.darts.service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.AmazonSNSException;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.tandf.darts.ms.teams.Card;
import uk.co.tandf.darts.ms.teams.TeamsDetails;
import uk.co.tandf.darts.slack.SlackDetails;
import uk.co.tandf.darts.slack.SlackService;

public class NotificationService {
    private static final Logger LOG = LogManager.getLogger(NotificationService.class);
    private static final ObjectWriter PRETTYJSON = (new ObjectMapper()).writerWithDefaultPrettyPrinter();
    private static final ObjectMapper JSON;
    private static final NotificationService instance;
    private final AmazonSNS snsClient = AmazonSNSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
    private SlackService slackService = new SlackService();

    public NotificationService() {
    }

    public static NotificationService getInstance() {
        return instance;
    }

    private PublishRequest createPublishRequest(String combinedMessage, String snsArn) {
        return (new PublishRequest()).withTopicArn(snsArn).withMessage(combinedMessage).withMessageAttributes(this.createFilterPolicyAttribute());
    }

    private Map<String, MessageAttributeValue> createFilterPolicyAttribute() {
        Map<String, MessageAttributeValue> filterPolicy = new HashMap();
        String filterPolicyJson = "Notification";
        MessageAttributeValue attributeValue = (new MessageAttributeValue()).withDataType("String").withStringValue(filterPolicyJson);
        filterPolicy.put("eventType", attributeValue);
        return filterPolicy;
    }

    private PublishResult publishMessage(PublishRequest publishRequest) {
        return this.snsClient.publish(publishRequest);
    }

    public void sendTeamsMessage(Card message, String webHookUrl) {
        HttpPost httpPost = new HttpPost(webHookUrl);

        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            Throwable var5 = null;

            try {
                StringEntity entity = new StringEntity(JSON.writeValueAsString(message));
                httpPost.setEntity(entity);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                client.execute(httpPost);
            } catch (Throwable var16) {
                var5 = var16;
                throw var16;
            } finally {
                if (client != null) {
                    if (var5 != null) {
                        try {
                            client.close();
                        } catch (Throwable var15) {
                            var5.addSuppressed(var15);
                        }
                    } else {
                        client.close();
                    }
                }

            }
        } catch (Exception var18) {
            Exception e = var18;
            LOG.error("---error while posting to teams: " + e);
        }

    }

    public void sendTeamsMessage3PD(Card message) {
        HttpPost httpPost = new HttpPost("https://informaplc.webhook.office.com/webhookb2/107f0818-a174-421f-866b-03bf0eaf60a4@2567d566-604c-408a-8a60-55d0dc9d9d6b/IncomingWebhook/5b90d45031b9470a97a571429da4cff4/19352463-583d-48be-bd5e-f16591bf480b/V2D3OGb4_5NqFa5AXPiDDpkJ0enw9nYJkINuerL4tqdMA1");

        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            Throwable var4 = null;

            try {
                StringEntity entity = new StringEntity(JSON.writeValueAsString(message));
                httpPost.setEntity(entity);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                client.execute(httpPost);
            } catch (Throwable var15) {
                var4 = var15;
                throw var15;
            } finally {
                if (client != null) {
                    if (var4 != null) {
                        try {
                            client.close();
                        } catch (Throwable var14) {
                            var4.addSuppressed(var14);
                        }
                    } else {
                        client.close();
                    }
                }

            }
        } catch (Exception var17) {
            Exception e = var17;
            LOG.error("---error while posting to teams: " + e);
        }

    }

    public void sendSlackMessage(SlackDetails message, String webHookUrl) {
        HttpPost httpPost = new HttpPost(webHookUrl);

        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            Throwable var5 = null;

            try {
                StringEntity entity = new StringEntity(PRETTYJSON.writeValueAsString(message));
                httpPost.setEntity(entity);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                client.execute(httpPost);
            } catch (Throwable var15) {
                var5 = var15;
                throw var15;
            } finally {
                if (client != null) {
                    if (var5 != null) {
                        try {
                            client.close();
                        } catch (Throwable var14) {
                            var5.addSuppressed(var14);
                        }
                    } else {
                        client.close();
                    }
                }

            }
        } catch (Exception var17) {
            Exception e = var17;
            LOG.error("---error while posting message: " + e);
        }

    }

    public TeamsDetails sendSNSTeamsMessage(String mappedMessage, String snsArn) {
        String combinedMessage = this.combineMessage(mappedMessage);
        PublishRequest publishRequest = this.createPublishRequest(combinedMessage, snsArn);

        try {
            PublishResult publishResult = this.publishMessage(publishRequest);
            this.printMessageId(publishResult);
            return new TeamsDetails(true, "Message sent successfully");
        } catch (AmazonSNSException var6) {
            AmazonSNSException ex = var6;
            LOG.error("Error publishing message to SNS", ex);
            return new TeamsDetails(false, "Failed to send message");
        }
    }

    public Map<String, String> getMappedMessage(String environment, String subject, String heading, Throwable t, String stackTraces) {
        String cause = "";
        Map<String, String> mappedMessage = new LinkedHashMap();
        mappedMessage.put("System", "DARTS " + environment);
        mappedMessage.put("Subject", subject.toUpperCase());
        mappedMessage.put("Message", heading);
        if (t != null) {
            Throwable th = t.getCause();
            if (th != null) {
                cause = t.getCause().getMessage();
            }

            if (cause.isEmpty()) {
                cause = t.getMessage();
            }
        }

        mappedMessage.put("Cause", cause);
        if (stackTraces.isEmpty()) {
            mappedMessage.put("Occurred At", this.getDateTimeForSlack());
        }

        return mappedMessage;
    }

    public String getMapAsJson(Map<String, String> mappedMessage) {
        String jsonMessage = "";

        try {
            jsonMessage = PRETTYJSON.writeValueAsString(mappedMessage);
        } catch (JsonProcessingException var4) {
            JsonProcessingException j = var4;
            LOG.error("---error converting json from map :" + j.getMessage());
        }

        return jsonMessage;
    }

    public String getStyledJson(Map<String, String> mappedMessage) {
        String jsonMessage = "";
        HashMap<String, String> customizedMap = new HashMap();

        try {
            mappedMessage.forEach((k, v) -> {
                String var10000 = (String)customizedMap.put("**" + k + "**", v);
            });
            jsonMessage = PRETTYJSON.writeValueAsString(customizedMap);
        } catch (JsonProcessingException var5) {
            JsonProcessingException j = var5;
            LOG.error("---error converting json from map :" + j.getMessage());
        }

        return jsonMessage;
    }

    public String formatTeamsMessage(String message) {
        return message.isEmpty() ? "" : message.replaceAll(",", ",\n\n").replace("{", "").replace("}", "").replaceAll("\"", "");
    }

    private String getDateTimeForSlack() {
        String dateTime = ZonedDateTime.now(Clock.systemDefaultZone()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z"));
        return dateTime;
    }

    private void printMessageId(PublishResult publishResult) {
        LOG.info("SNS Teams MessageId: " + publishResult.getMessageId());
    }

    private String combineMessage(String rawMessage) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        Map<String, String> messageParts = this.buildMessage(rawMessage);
        String formattedHeader = (String)messageParts.get("header");
        String notificationMessage = (String)messageParts.get("message");
        jsonNode.put("tokenId", "darts-bau");
        jsonNode.put("header", formattedHeader);
        jsonNode.put("message", notificationMessage);
        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException var9) {
            JsonProcessingException e = var9;
            LOG.error("Error processing JSON", e);
        }

        LOG.info(jsonString);
        return jsonString;
    }

    private Map<String, String> buildMessage(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> result = new HashMap();

        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String system = jsonNode.get("System").asText();
            String subject = jsonNode.get("Subject").asText();
            String formattedOutput = String.format("%s (%s)", system, subject);
            ObjectNode remainingMessageNode = objectMapper.createObjectNode();
            jsonNode.fields().forEachRemaining((entry) -> {
                if (!((String)entry.getKey()).equals("System") && !((String)entry.getKey()).equals("Subject")) {
                    remainingMessageNode.set((String)entry.getKey(), (JsonNode)entry.getValue());
                }

            });
            String remainingMessage = remainingMessageNode.toString();
            result.put("header", formattedOutput);
            result.put("message", remainingMessage);
            LOG.info(formattedOutput);
            return result;
        } catch (JsonProcessingException var10) {
            JsonProcessingException e = var10;
            LOG.error("Error processing JSON", e);
            throw new RuntimeException(e);
        }
    }

    public SlackService getSlackService() {
        return this.slackService;
    }

    static {
        JSON = (new ObjectMapper()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        instance = new NotificationService();
    }
}
