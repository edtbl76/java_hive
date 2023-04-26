package org.tbl.s3;

import com.sun.xml.bind.v2.util.ByteArrayOutputStreamEx;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
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
import javax.ws.rs.core.StreamingOutput;

import java.io.ByteArrayOutputStream;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.*;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/s3")
public class S3SyncClientResource extends CommonResource{

    @Inject
    S3Client client;

    @POST
    @Path("upload")
    @Consumes(MULTIPART_FORM_DATA)
    public Response uploadFile(@MultipartForm FormData formData) throws Exception {

        if (formData.filename == null || formData.filename.isEmpty()) {
            return Response.status(BAD_REQUEST).build();
        }

        if (formData.mimeType == null || formData.mimeType.isEmpty()) {
            return Response.status(BAD_REQUEST).build();
        }

        PutObjectResponse putObjectResponse = client.putObject(buildPutRequest(formData),
                RequestBody.fromFile(uploadToTemp(formData.data)));

        if (putObjectResponse != null) {
            return Response.ok().status(CREATED).build();
        } else {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("download/{objectKey}")
    @Produces(APPLICATION_OCTET_STREAM)
    public Response downloadFile(@PathParam("objectKey") String objectKey) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GetObjectResponse object = client.getObject(buildGetRequest(objectKey),
                ResponseTransformer.toOutputStream(baos));

        Response.ResponseBuilder response = Response.ok((StreamingOutput) baos::writeTo);
        response.header("Content-Disposition", "attachement;filename=" + objectKey);
        response.header("Content-Type", object.contentType());
        return response.build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<FileObject> listFiles() {
        ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder().bucket(bucketName).build();

        return client.listObjects(listObjectsRequest)
                .contents()
                .stream()
                .sorted(Comparator.comparing(S3Object::lastModified).reversed())
                .map(FileObject::from)
                .collect(Collectors.toList());
    }
}
