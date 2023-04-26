package org.tbl.kms;

import io.smallrye.mutiny.Uni;
import org.apache.commons.codec.binary.Base64;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.KmsAsyncClient;
import software.amazon.awssdk.services.kms.model.DecryptResponse;
import software.amazon.awssdk.services.kms.model.EncryptResponse;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import static java.nio.charset.StandardCharsets.*;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("async")
@Produces(TEXT_PLAIN)
@Consumes(TEXT_PLAIN)
public class KmsAsyncResponse {

    @Inject
    KmsAsyncClient client;

    @ConfigProperty(name = "key.arn")
    String keyArn;

    @POST
    @Path("encrypt")
    public Uni<String> encrypt(String data) {
        return Uni.createFrom().completionStage(
                client.encrypt(builder -> builder.keyId(keyArn).plaintext(SdkBytes.fromUtf8String(data))))
                .onItem().transform(EncryptResponse::ciphertextBlob)
                .onItem().transform(blob -> Base64.encodeBase64String(blob.asByteArray()));
    }

    @POST
    @Path("decrypt")
    public Uni<String> decrypt(String data) {
        return Uni.createFrom().item(SdkBytes.fromByteArray(Base64.decodeBase64(data.getBytes(UTF_8))))
                .onItem().transformToUni(bytes -> Uni.createFrom().completionStage(
                        client.decrypt(builder -> builder.keyId(keyArn).ciphertextBlob(bytes))))
                .onItem().transform(DecryptResponse::plaintext)
                .onItem().transform(SdkBytes::asUtf8String);
    }
}
