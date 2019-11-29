package org.tnmk.practicespringarangodb.pro04embeddeditems.common.infrastructure.mongodb;

import com.mongodb.MongoClientOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tnmk.practicespringarangodb.pro04embeddeditems.common.utils.IOUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * This class is used to connect to MongoDB via TLS which is required by AWS Document DB.
 * The regular Spring Boot Mongo TLS Configuration way doesn't work (using configuration in application.yml) (Spring Boot 2.1.8.RELEASE). Hopefully it will be changed in the near future.
 * That's why we have to write this custom class.
 */
@ConditionalOnProperty(value="spring.data.mongo.tls", havingValue = "true")
@Configuration
public class MongoTLSConfig {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String CERTIFICATE_ALGORITHM = "X.509";
    private static final String SSL_ALGORITHM = "TLS";
    /**
     * The key of CA Certificate in keystore.
     */
    private static final String CA_CERT_KEY = "caCert";
    private static final String RDS_COMBINED_CA_BUNDLE = "/rds-combined-ca-bundle.pem";

    @Bean
    public MongoClientOptions mongoClientOptions() {
        MongoClientOptions.Builder mongoClientOptions = MongoClientOptions.builder()
            .sslEnabled(true)
            //This option is used for localhost testing only.
            //In localhost, the host name will be different from the hostname which defined inside rds-combined-ca-bundle.pem.
            //That's why we have to turn this option on.
            .sslInvalidHostNameAllowed(true);
        try {
            SSLContext sslContext = createSslContext();
            mongoClientOptions.sslContext(sslContext);
        } catch (Exception e) {
            throw new RuntimeException("Cannot connect MongoDB via TLS: " + e.getMessage(), e);
        }
        return mongoClientOptions.build();
    }

    private static SSLContext createSslContext() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        X509Certificate caCert = createCaCertificate(RDS_COMBINED_CA_BUNDLE);

        KeyStore keyStore = createKeyStore(caCert);

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

        SSLContext sslContext = createSSLContext(trustManagerFactory);
        return sslContext;
    }

    private static X509Certificate createCaCertificate(String pemFileClassPath) throws CertificateException, IOException {
        try (InputStream is = IOUtils.loadInputStreamFileInClassPath(pemFileClassPath)) {
            CertificateFactory cf = CertificateFactory.getInstance(CERTIFICATE_ALGORITHM);
            X509Certificate caCert = (X509Certificate) cf.generateCertificate(is);
            return caCert;
        }
    }

    private static KeyStore createKeyStore(X509Certificate caCert) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null); // You don't need the KeyStore instance to come from a file.
        keyStore.setCertificateEntry(CA_CERT_KEY, caCert);
        return keyStore;
    }

    private static SSLContext createSSLContext(TrustManagerFactory trustManagerFactory) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance(SSL_ALGORITHM);
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
        return sslContext;
    }

}