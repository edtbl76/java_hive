package org.tbl.sns.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SnsNotification {

    @Getter @Setter
    @JsonProperty("Message")
    private String message;

    @Getter @Setter
    @JsonProperty("MessageId")
    private String messageId;

    @Getter @Setter
    @JsonProperty("Signature")
    private String signature;

    @Getter @Setter
    @JsonProperty("SignatureVersion")
    private String signatureVersion;

    @Getter @Setter
    @JsonProperty("SigningCertURL")
    private String signingCertUrl;

    @Getter @Setter
    @JsonProperty("Subject")
    private String subject;

    @Getter @Setter
    @JsonProperty("Timestamp")
    private String timestamp;

    @Getter @Setter
    @JsonProperty("TopicArn")
    private String topicArn;

    @Getter @Setter
    @JsonProperty("Type")
    private String type;

    @Getter @Setter
    @JsonProperty("UnsubscribeURL")
    private String unsubscribeUrl;
}
