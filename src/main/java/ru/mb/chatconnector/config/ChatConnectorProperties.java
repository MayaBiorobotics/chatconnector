package ru.mb.chatconnector.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "chat.connector"
)
public class ChatConnectorProperties {
    private String username;
    private String password;
    private String host;
    private boolean ssl;
    private Integer port;
    private String keyStoreType;
    private String keyStore;
    private String ketStorePassword;
    private String trustStore;
    private String trustStorePassword;
    private String algorithm;

    private String token;
    private String queue;

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getQueue() {
        return queue;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getToken() {
        return token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getHost() {
        return host;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public boolean isSsl() {
        return ssl;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getKeyStore() {
        return keyStore;
    }

    public String getKetStorePassword() {
        return ketStorePassword;
    }

    public String getTrustStore() {
        return trustStore;
    }

    public String getTrustStorePassword() {
        return trustStorePassword;
    }

    public String getKeyStoreType() {
        return keyStoreType;
    }

    public void setKeyStoreType(String keyStoreType) {
        this.keyStoreType = keyStoreType;
    }

    public void setKeyStore(String keyStore) {
        this.keyStore = keyStore;
    }

    public void setKetStorePassword(String ketStorePassword) {
        this.ketStorePassword = ketStorePassword;
    }

    public void setTrustStore(String trustStore) {
        this.trustStore = trustStore;
    }

    public void setTrustStorePassword(String trustStorePassword) {
        this.trustStorePassword = trustStorePassword;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
