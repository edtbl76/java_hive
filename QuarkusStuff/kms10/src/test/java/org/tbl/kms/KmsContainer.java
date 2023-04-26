package org.tbl.kms;

import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class KmsContainer extends LocalStackContainer {

    private static final String HOSTNAME_EXTERNAL_ENV_VAR = "HOSTNAME_EXTERNAL";
    private static final Integer KMS_PORT = 4566;

    public KmsContainer() {
        super(DockerImageName.parse("localstack/localstack:0.11.2"));
    }

    @Override
    protected void configure() {
        withEnv("SERVICES", "kms");

        String hostnameExternalReason;
        if (getEnvMap().containsKey(HOSTNAME_EXTERNAL_ENV_VAR)) {
            hostnameExternalReason = "EV only";
        } else if (getNetwork() != null && getNetworkAliases() != null && getNetworkAliases().size() >= 1) {
            withEnv(HOSTNAME_EXTERNAL_ENV_VAR, getNetworkAliases().get(getNetworkAliases().size() - 1));
            hostnameExternalReason = "matches last network alias on container w/ non-default network";
        } else {
            withEnv(HOSTNAME_EXTERNAL_ENV_VAR, getHost());
            hostnameExternalReason = "matches host-routable address for container";
        }
        logger().info("{} env var set to {} ({})", HOSTNAME_EXTERNAL_ENV_VAR,
                getEnvMap().get(HOSTNAME_EXTERNAL_ENV_VAR), hostnameExternalReason);

        addExposedPort(KMS_PORT);
    }

    public URI getEndpointOverride() {
        try {
            final String address = getHost();
            String ip = address;
            ip = InetAddress.getByName(address).getHostAddress();
            return new URI("http://" + ip + ":" + getMappedPort(KMS_PORT));
        } catch (UnknownHostException | URISyntaxException e) {
            throw new IllegalStateException("Can't get endpoint URL", e);
        }
    }
}
