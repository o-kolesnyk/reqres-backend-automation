package com.reqres.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesConfig {

  private final Properties props;

  private PropertiesConfig() {
      props = new Properties();
      try (InputStream is = getClass().getClassLoader()
              .getResourceAsStream("config.properties")) {
          props.load(is);
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
  }

  /**
   * Thread-safe lazy initialization via Holder
   */
  public static PropertiesConfig getInstance() {
      return Holder.INSTANCE;
  }

  private static final class Holder {
      static final PropertiesConfig INSTANCE = new PropertiesConfig();
  }

  public String getBaseUrl() {
    return props.getProperty("baseUrl");
  }

  public String getBasePath() {
    return props.getProperty("basePath");
  }

  public String getApiKey() {
    return props.getProperty("apiKey");
  }
}
