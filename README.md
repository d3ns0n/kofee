# KoFee

---

## How to develop

### Requirements

- Java 21+
- Docker Compose
- IntelliJ IDEA (optional, but recommended)

### Format code

We use the maven-spotless-plugin to ensure consistent formatting across the entire code. During 'compile' phase the plugin checks that the
code is formatted properly. If not, the build will break!

To format the code, you can run `spotless:apply`

```shell
./mvnw spotless:apply
```

---

### Local environment

1. To start the required external services you can use:

   ```shell
   docker compose up -d
   ```
2. When all external services are up and running, you can start the application via:

   ```shell
   ./mvnw spring-boot:run
   ```
3. When the service is up and running you can use the HTTP example requests in the folder [http](http). Please use the `dev` environment. The example
   requests are written for the [IntelliJ HTTP Client](https://www.jetbrains.com/help/idea/exploring-http-syntax.html). If you want to use another
   client like Postman, check [./http/http-client.env.json](./http/http-client.env.json) for OAuth2 configuration parameters. Predefined credentials
   and roles can be found in [CREDENTIALS.md](CREDENTIALS.md)

---

#### Keycloak

Is an open-source identity provider, that supports OAuth2 support for local development with
a [WebUI](http://localhost:8083/admin/master/console/#/kofee)

**Credentials:**

| Username | Password |
|----------|----------|
| admin    | admin    |

##### How to back up a realm

Due to a bug in recent keycloak version it is necessary to copy the db files to a temporary folder and run the export command with the temporary
folder as source. More information on the bug can be found on [GitHub](https://github.com/keycloak/keycloak/issues/33800).

```shell
docker exec -it keycloak sh -c \
  "cp -rp /opt/keycloak/data/h2 /tmp ; \
  /opt/keycloak/bin/kc.sh export --dir /opt/keycloak/data/import --realm kofee --users realm_file \
    --db dev-file \
    --db-url 'jdbc:h2:file:/tmp/h2/keycloakdb;NON_KEYWORDS=VALUE' \
    --http-management-port 9999"
```

---

#### PostgreSQL

An open-source relational database to store our items in. You can access it with your favourite DB tool via: `jdbc:postgresql://localhost:5433/kofee`

---

#### AKHQ

Kafka GUI for Apache Kafka to manage topics, topics data, consumers group, schema registry, connect and more...

Access via [http://localhost:8089/](http://localhost:8089/)
