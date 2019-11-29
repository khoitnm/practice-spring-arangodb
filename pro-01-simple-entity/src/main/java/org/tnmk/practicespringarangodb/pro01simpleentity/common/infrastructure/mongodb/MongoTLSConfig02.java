package org.tnmk.practicespringarangodb.pro01simpleentity.common.infrastructure.mongodb;

import com.mongodb.MongoClientOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@Profile(("!componenttest")) //In component test, we don't need SSL configuration.
//@Configuration
public class MongoTLSConfig02 {
    private static final String SSL_CERTIFICATE = "/rds-combined-ca-bundle.pem";
    private static final String KEY_STORE_TYPE = "JKS";
    private static final String KEY_STORE_PROVIDER = "SUN";
    private static final String KEY_STORE_FILE_PREFIX = "sys-connect-via-ssl-test-cacerts";
    private static final String KEY_STORE_FILE_SUFFIX = ".jks";
    private static final String DEFAULT_KEY_STORE_PASSWORD = "changeit";

    @Bean
    public MongoClientOptions mongoClientOptions() throws Exception {
        String keyStoreFile = createKeyStoreFile(createCertificate()).getPath();
        System.setProperty ("javax.net.ssl.trustStore", keyStoreFile);
        System.setProperty ("javax.net.ssl.trustStoreType",KEY_STORE_TYPE);
        System.setProperty ("javax.net.ssl.trustStorePassword",DEFAULT_KEY_STORE_PASSWORD);

        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        // Use .sslInvalidHostNameAllowed(true) to enable local testing with port forwarding.
        return builder.sslEnabled(true).build();
    }

    /**
     *  This method generates the SSL certificate
     * @throws Exception
     */
    private static X509Certificate createCertificate() throws Exception {
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        URI sslCert = new ClassPathResource(SSL_CERTIFICATE).getURI();
        try (InputStream certInputStream = Files.newInputStream(Paths.get(sslCert))) {
            return (X509Certificate) certFactory.generateCertificate(certInputStream);
        }
    }

    /**
     * This method creates the Key Store File
     * @param rootX509Certificate - the SSL certificate to be stored in the KeyStore
     * @return
     * @throws Exception
     */
    private static File createKeyStoreFile(X509Certificate rootX509Certificate) throws Exception {
        File keyStoreFile = File.createTempFile(KEY_STORE_FILE_PREFIX, KEY_STORE_FILE_SUFFIX);
        URI keyStoreFileURI = keyStoreFile.toURI();
        try (OutputStream os = Files.newOutputStream(Paths.get(keyStoreFileURI))) {
            KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE, KEY_STORE_PROVIDER);
            ks.load(null);
            ks.setCertificateEntry("rootCaCertificate", rootX509Certificate);
            ks.store(os, DEFAULT_KEY_STORE_PASSWORD.toCharArray());
        }
        return keyStoreFile;
    }
}
