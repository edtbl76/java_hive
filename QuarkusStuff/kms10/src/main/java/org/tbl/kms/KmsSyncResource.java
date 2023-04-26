package org.tbl.kms;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.DecryptResponse;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import static java.nio.charset.StandardCharsets.*;
import static javax.ws.rs.core.MediaType.*;

@Path("sync")
@Produces(TEXT_PLAIN)
@Consumes(TEXT_PLAIN)
public class KmsSyncResource {

    @Inject
    KmsClient client;

    @ConfigProperty(name = "key.arn")
    String keyArn;

    @POST
    @Path("encrypt")
    public String encrypt(String data) {
        SdkBytes bytes = client.encrypt(builder -> builder.keyId(keyArn).plaintext(SdkBytes.fromUtf8String(data)))
                .ciphertextBlob();
        return Base64.encodeBase64String(bytes.asByteArray());
    }

    @POST
    @Path("decrypt")
    public String decrypt(String data) {
        SdkBytes bytes = SdkBytes.fromByteArray(Base64.decodeBase64(data.getBytes(UTF_8)));
        DecryptResponse decryptResponse = client.decrypt(builder -> builder.keyId(keyArn).ciphertextBlob(bytes));

        return decryptResponse.plaintext().asUtf8String();
    }
}
