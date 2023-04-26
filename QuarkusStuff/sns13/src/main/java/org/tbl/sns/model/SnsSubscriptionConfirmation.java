package org.tbl.sns.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class SnsSubscriptionConfirmation {

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
    @JsonProperty("SubscribeURL")
    private String subscribeUrl;

    @Getter @Setter
    @JsonProperty("Timestamp")
    private String timestamp;

    @Getter @Setter
    @JsonProperty("Token")
    private String token;

    @Getter @Setter
    @JsonProperty("TopicArn")
    private String topicArn;

    @Getter @Setter
    @JsonProperty("Type")
    private String type;

}
