- add a groovy installation check check
- arquillian test for LongRunningJobsService.java
- jax rs client README.md
- JEE Performance with JMeter, Prometheus and Grafana
  http://highcohesionloosecoupling.com/index.php/2017/10/08/jee-performance-jmeter-prometheus-grafana-complete-project-scratch/
- jpa tests for queries
- test all queries, defined in EntityExampleRepository
- mocking repository in tests
- creating real repositories for IT 
- rename repository
- repositories for publishing
- use rest api by rest client only to test how it works
- rest api unit tests for serialiation
- split dependencies by modules, to delete them if not needed
- update jpa queries with and without spring https://www.baeldung.com/spring-data-jpa-query
- add validation for entity 
- boolean field
- jms module
- selfexecutable jar module
- add push notification via SSE
- add create and update operations support frontend
- add unit tests for react: props, state, methods, events, rendering, content, code coverage
- file uploading
- fix isssue
  
    ```
    static final String CRON_EXPRESSION = "0 15 10 * * ? 2005";
    Caused by: java.lang.IllegalArgumentException: WFLYEJB0293: Could not parse: ? in schedule expression
    
    Tried to correct it to "0 15 10 * * * 2005"
    but it fails, in https://www.freeformatter.com/cron-expression-generator-quartz.html
    with:
    Support for specifying both a day-of-week AND a day-of-month parameter is not implemented.
    ```
- date and separate time unit tests for deserializer and serializer 
- deprecated date/time api, like: java.util.Date, java.sql.Date, java.sql.Timestamp:
  documentation
  swagger examples
  post new item with needed format
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
    `insert into example values(9991100, 'some name', 'CREATED', 1, 22, 333, 4444, 5555555555, 100932.02, 25.3, 35.2, 42343.43253423, now(), now(), now(), now(), now(), now(), now(), now(), now(), now(), 'another name value');`
- code generation: generate something based on something: table, entity, domain interface, rest dto