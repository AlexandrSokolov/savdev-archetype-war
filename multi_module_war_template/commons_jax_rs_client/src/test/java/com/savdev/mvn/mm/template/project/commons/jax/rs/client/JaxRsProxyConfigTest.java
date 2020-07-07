package com.savdev.mvn.mm.template.project.commons.jax.rs.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.collect.Lists;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.api.RestDto;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.api.ServiceApi;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.config.JAXRSConfiguration;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.config.ServerSideJacksonProvider;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.filter.AuthClientRequestFilterFactory;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.jackson.LocalDateTimeDeserializer;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.jackson.LocalDateTimeSerializer;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.jackson.TestObjectMapper;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.jwt.AuthJwtApi;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.service.AuthJwtService;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.service.ServiceWithAuthJwtCheck;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.savdev.mvn.mm.template.project.commons.jax.rs.client.service.AuthJwtService.JWT_LOGIN;
import static com.savdev.mvn.mm.template.project.commons.jax.rs.client.service.AuthJwtService.JWT_PASSWORD;

public class JaxRsProxyConfigTest extends JaxRsProxyConfigBaseTest {

  @BeforeClass
  public static void setUpServer() throws Exception {
    JaxRsProxyConfigBaseTest.setUpServer(
      Lists.newArrayList(
        AuthJwtService.class,
        ServiceWithAuthJwtCheck.class),
      Lists.newArrayList(
        JAXRSConfiguration.class,
        ServerSideJacksonProvider.class));
  }

  /**
   * In the log, you'll see that a request to Auth service at ${AUTH_JWT_PATH}: is sent only once!
   */
  @Test
  public void testJwtViaBasicAuth(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.builder()
      .withDomain(URI.toString())
      .withClazz4Logging(JaxRsProxyConfigBaseTest.class)
      .withObjectMapper(new TestObjectMapper())
      .withJwtViaBasicAuth(
        JWT_LOGIN,
        JWT_PASSWORD,
        AuthJwtApi.class,
        authJwtService -> authJwtService.authenticate().getJwtToken())
      .build();
    //fist a request to auth at: ${AUTH_JWT_PATH}
    //then a requst to ${CLIENT_API_PATH}
    RestDto dto = proxyConfig.proxy(ServiceApi.class).getDto();
    Assert.assertEquals(ServiceWithAuthJwtCheck.JWT_INSTANCE, dto);
    //auth is cached. so a request to auth is NOT sent to: ${AUTH_JWT_PATH}
    //only a request to ${CLIENT_API_PATH} is sent
    RestDto dto2 = proxyConfig.proxy(ServiceApi.class).getDto();
    Assert.assertEquals(ServiceWithAuthJwtCheck.JWT_INSTANCE, dto2);
  }

  /**
   * This version is a variation of the testJwtViaBasicAuth,
   *  but here 2 instances of JaxRsProxyConfig are created explicitly.
   *
   * In the log, you'll see that a request to Auth service at ${AUTH_JWT_PATH}: is sent only once!
   */
  @Test
  public void testJwt2FactorAuthCached(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.builder()
      .withDomain(URI.toString())
      .withClazz4Logging(JaxRsProxyConfigBaseTest.class)
      .withObjectMapper(new TestObjectMapper())
      .withCustomAuthFilter(AuthClientRequestFilterFactory.jwt2factorCachedAuthentication(
        JWT_LOGIN,
        JaxRsProxyConfig.builder()
          .withDomain(URI.toString())
          .withClazz4Logging(JaxRsProxyConfigBaseTest.class)
          .withBasicAuth(JWT_LOGIN, JWT_PASSWORD)
          .build(),
        AuthJwtApi.class,
        authJwtService -> authJwtService.authenticate().getJwtToken()))
      .build();
    //fist a request to auth at: ${AUTH_JWT_PATH}
    //then a requst to ${CLIENT_API_PATH}
    RestDto dto = proxyConfig.proxy(ServiceApi.class).getDto();
    Assert.assertEquals(ServiceWithAuthJwtCheck.JWT_INSTANCE, dto);
    //auth is cached. so a request to auth is NOT sent to: ${AUTH_JWT_PATH}
    //only a request to ${CLIENT_API_PATH} is sent
    RestDto dto2 = proxyConfig.proxy(ServiceApi.class).getDto();
    Assert.assertEquals(ServiceWithAuthJwtCheck.JWT_INSTANCE, dto2);
  }

  /**
   * In the log, you'll see that a request to Auth service at ${AUTH_JWT_PATH}: will be sent twice!
   */
  @Test
  public void testJwt2FactorAuthNoCache(){
    JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.builder()
      .withDomain(URI.toString())
      .withClazz4Logging(JaxRsProxyConfigBaseTest.class)
      .withObjectMapper(new TestObjectMapper())
      .withCustomAuthFilter(AuthClientRequestFilterFactory.jwt2factorAuthentication(
        JaxRsProxyConfig.builder()
          .withDomain(URI.toString())
          .withClazz4Logging(JaxRsProxyConfigBaseTest.class)
          .withObjectMapper(new TestObjectMapper())
          .withBasicAuth(JWT_LOGIN, JWT_PASSWORD)
          .build(),
        AuthJwtApi.class,
        authJwtService -> authJwtService.authenticate().getJwtToken()))
      .build();
    //fist a request to auth at: ${AUTH_JWT_PATH}
    //then a requst to ${CLIENT_API_PATH}
    RestDto dto = proxyConfig.proxy(ServiceApi.class).getDto();
    Assert.assertEquals(ServiceWithAuthJwtCheck.JWT_INSTANCE, dto);
    //auth is cached. so a request to auth is NOT sent to: ${AUTH_JWT_PATH}
    //only a request to ${CLIENT_API_PATH} is sent
    RestDto dto2 = proxyConfig.proxy(ServiceApi.class).getDto();
    Assert.assertEquals(ServiceWithAuthJwtCheck.JWT_INSTANCE, dto2);  }

  /**
   * Here we try to use basic auth only, but it is not enough for the server
   */
  @Test
  public void testJwt2FactorAuthError(){
    try {
      JaxRsProxyConfig proxyConfig = JaxRsProxyConfig.builder()
        .withDomain(URI.toString())
        .withClazz4Logging(JaxRsProxyConfigBaseTest.class)
        .withObjectMapper(new TestObjectMapper())
        .withBasicAuth(JWT_LOGIN, JWT_PASSWORD)
        .build();
      proxyConfig.proxy(ServiceApi.class).getDto();
      Assert.fail("'Not authenticated' error expected");
    } catch (Exception e){
      Assert.assertTrue(e.getMessage().contains("HTTP 500 Internal Server Error"));
    }
  }
}
