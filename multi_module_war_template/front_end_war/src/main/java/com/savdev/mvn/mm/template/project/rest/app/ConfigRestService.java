package com.savdev.mvn.mm.template.project.rest.app;

import com.savdev.mvn.mm.template.project.commons.config.Configs;
import com.savdev.mvn.mm.template.project.config.Configuration;
import com.savdev.mvn.mm.template.project.rest.api.ConfigRestApi;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.nio.file.Paths;

public class ConfigRestService implements ConfigRestApi {

  @Override
  public Response config() {
    return Response.ok(
      Paths.get(Configs.propertiesFilePath(
        Configuration.CONFIG_FOLDER_VARIABLE,
        Configuration.CONFIG_FILE))
        .toFile())
      .header(
        HttpHeaders.CONTENT_DISPOSITION,
        String.format("attachment; filename=\"%s\"", Configuration.CONFIG_FILE))
      .build();
  }
}
