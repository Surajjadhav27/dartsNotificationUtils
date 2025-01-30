package uk.co.tandf.darts.slack;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SlackService {
    private static final Logger LOG = LogManager.getLogger(SlackService.class);
    private static final String prodChannelName = "darts_notifications";
    private static final String nonProdChannelName = "darts_notifications_u_q_d";
    private static final String channelName3pd = "test_darts_notification";
    static List<String> prodChannelData = Arrays.asList("darts_notifications", "https://hooks.slack.com/services/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");
    static List<String> nonProdChannelData = Arrays.asList("darts_notifications_u_q_d", "https://hooks.slack.com/services/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");
    static List<String> thirdPartyChannelData = Arrays.asList("test_darts_notification", "https://hooks.slack.com/services/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");

    public SlackService() {
    }

    public void sendMessage(SlackDetails message, String url) {
        HttpPost httpPost = new HttpPost(url);

        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            Throwable var5 = null;

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(message);
                StringEntity entity = new StringEntity(json);
                httpPost.setEntity(entity);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                client.execute(httpPost);
            } catch (Throwable var17) {
                var5 = var17;
                throw var17;
            } finally {
                if (client != null) {
                    if (var5 != null) {
                        try {
                            client.close();
                        } catch (Throwable var16) {
                            var5.addSuppressed(var16);
                        }
                    } else {
                        client.close();
                    }
                }

            }
        } catch (Exception var19) {
            Exception e = var19;
            e.printStackTrace();
        }

    }

    public void sendMessageProd(SlackDetails message) {
        message.setChannelName((String)prodChannelData.get(0));
        HttpPost httpPost = new HttpPost((String)prodChannelData.get(1));

        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            Throwable var4 = null;

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(message);
                StringEntity entity = new StringEntity(json);
                httpPost.setEntity(entity);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                client.execute(httpPost);
            } catch (Throwable var16) {
                var4 = var16;
                throw var16;
            } finally {
                if (client != null) {
                    if (var4 != null) {
                        try {
                            client.close();
                        } catch (Throwable var15) {
                            var4.addSuppressed(var15);
                        }
                    } else {
                        client.close();
                    }
                }

            }
        } catch (IOException var18) {
            IOException e = var18;
            e.printStackTrace();
        }

    }

    public void sendMessageNonProd(SlackDetails message) {
        message.setChannelName((String)nonProdChannelData.get(0));
        HttpPost httpPost = new HttpPost((String)nonProdChannelData.get(1));

        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            Throwable var4 = null;

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(message);
                StringEntity entity = new StringEntity(json);
                httpPost.setEntity(entity);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                client.execute(httpPost);
            } catch (Throwable var16) {
                var4 = var16;
                throw var16;
            } finally {
                if (client != null) {
                    if (var4 != null) {
                        try {
                            client.close();
                        } catch (Throwable var15) {
                            var4.addSuppressed(var15);
                        }
                    } else {
                        client.close();
                    }
                }

            }
        } catch (IOException var18) {
            IOException e = var18;
            e.printStackTrace();
        }

    }

    public void sendMessage3pd(SlackDetails message) {
        message.setChannelName((String)thirdPartyChannelData.get(0));
        HttpPost httpPost = new HttpPost((String)thirdPartyChannelData.get(1));

        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            Throwable var4 = null;

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(message);
                StringEntity entity = new StringEntity(json);
                httpPost.setEntity(entity);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                client.execute(httpPost);
            } catch (Throwable var16) {
                var4 = var16;
                throw var16;
            } finally {
                if (client != null) {
                    if (var4 != null) {
                        try {
                            client.close();
                        } catch (Throwable var15) {
                            var4.addSuppressed(var15);
                        }
                    } else {
                        client.close();
                    }
                }

            }
        } catch (IOException var18) {
            IOException e = var18;
            e.printStackTrace();
        }

    }
}
