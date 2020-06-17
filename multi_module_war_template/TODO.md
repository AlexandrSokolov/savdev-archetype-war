- fix gitignore
- remove `<parent>` tag from the `deps` module, update docs regarding it
- consider using archetype-post-generate.groovy

- time-date issue yyyy-MM-dd'T'HH:mm:ss'Z', not sure if custom serializers are needed, see 
    The ISO 8601 format is the standard which is supported by JS by default and it's readable - 
    2011-12-03T10:15:30+01:00
    
    ```
      <dependency>
        <groupId>com.fasterxml.jackson.datatype</groupId>
        <artifactId>jackson-datatype-jsr310</artifactId>
        <scope>provided</scope>
      </dependency>
      ...
        objectMapper = new ObjectMapper()
          .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
          .disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
          .setSerializationInclusion(JsonInclude.Include.NON_NULL)
          .registerModule(new JavaTimeModule());
    ```
- insert data into database
    `insert into example values(1, 'some name', 'CREATED', 1, 22, 333, 4444, 5555555555, 100932.02, 25.3, 35.2, 42343.43253423, now(), now(), now(), now(), now(), now(), now(), now(), now(), now(), 'another name value');`