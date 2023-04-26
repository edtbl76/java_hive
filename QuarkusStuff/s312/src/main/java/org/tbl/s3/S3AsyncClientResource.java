package org.tbl.s3;

import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Path("/async-s3")
public class S3AsyncClientResource extends CommonResource {

    @Inject
    S3AsyncClient client;

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Uni<Response> uploadFile(@MultipartForm FormData formData) throws Exception {

        if (formData.filename == null || formData.filename.isEmpty()) {
            return Uni.createFrom().item(Response.status(BAD_REQUEST).build());
        }

        if (formData.mimeType == null || formData.mimeType.isEmpty()) {
            return Uni.createFrom().item(Response.status(BAD_REQUEST).build());
        }

        return Uni.createFrom().completionStage(
                () -> client.putObject(buildPutRequest(formData),
                        AsyncRequestBody.fromFile(uploadToTemp(formData.data))))
                .onItem().ignore().andSwitchTo(Uni.createFrom().item(Response.created(null).build()))
                .onFailure().recoverWithItem(throwable -> {
                    throwable.printStackTrace();
                    return Response.serverError().build();
                });
    }

    @GET
    @Path("download/{objectKey}")
    @Produces(APPLICATION_OCTET_STREAM)
    public Uni<Response> downloadFile(@PathParam("objectKey") String objectKey) throws Exception {
        File tempFile = tempFilePath();

        return Uni.createFrom().completionStage(
                () -> client.getObject(buildGetRequest(objectKey), AsyncResponseTransformer.toFile(tempFile)))
                .onItem().transform(getObjectResponse -> Response.ok(tempFile)
                        .header("Content-Disposition", "attachment;filename=" + objectKey)
                        .header("Content-Type", getObjectResponse.contentType())
                        .build());
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Uni<List<FileObject>> listFiles() {
        ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder()
                .bucket(bucketName)
                .build();

        return Uni.createFrom().completionStage(() -> client.listObjects(listObjectsRequest))
                .onItem().transform(this::toFileItems);
    }

    private List<FileObject> toFileItems(ListObjectsResponse objectsResponse) {
        return objectsResponse
                .contents().stream()
                .sorted(Comparator.comparing(S3Object::lastModified).reversed())
                .map(FileObject::from)
                .collect(Collectors.toList());
    }
}
