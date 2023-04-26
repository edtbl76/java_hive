package IntegOfJUnit5WithExternalFrameworks_5.example.junit5RESTWireMock;

import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Objects;

import static java.lang.invoke.MethodHandles.lookup;

public class RemoteFileService {

    static final Logger LOGGER = LoggerFactory.getLogger(lookup().lookupClass());

    private RemoteFileApi remoteFileApi;

    public RemoteFileService(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
        remoteFileApi = retrofit.create(RemoteFileApi.class);
    }

    public byte[] getFile(String file) throws IOException {
        Call<ResponseBody> openFile = remoteFileApi.openFile(file);
        Response<ResponseBody> execute = openFile.execute();
        String streamId = Objects.requireNonNull(execute.body()).string();
        LOGGER.info("Stream {} open ", streamId);

        Call<ResponseBody> readStream = remoteFileApi.readStream(streamId);
        byte[] content = Objects.requireNonNull(readStream.execute().body()).bytes();
        LOGGER.info("Recv'd {} bytes", content.length);

        remoteFileApi.closeStream(streamId).execute();
        LOGGER.info("Recv'd {} bytes", content.length);
        LOGGER.info("Stream {} closed", streamId);

        return content;
    }
}
