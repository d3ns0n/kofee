# KoFee

## How to develop

### Format code

We use the maven-spotless-plugin to ensure consistent formatting across the entire code. During 'compile' phase the plugin checks that the
code is formatted properly. If not, the build will break!

To format the code, you can run `spotless:apply`

```shell
./mvnw spotless:apply
```

### Local environment

#### Keycloak

##### How to back up a realm

Due to a bug in recent keycloak version it is necessary to copy the db files to a temporary folder and run the export command with the temporary
folder as source. More information on the bug can be found on [GitHub](https://github.com/keycloak/keycloak/issues/33800).

```shell
docker exec -it keycloak.openid-provider sh -c \
  "cp -rp /opt/keycloak/data/h2 /tmp ; \
  /opt/keycloak/bin/kc.sh export --dir /opt/keycloak/data/import --realm kofee --users realm_file \
    --db dev-file \
    --db-url 'jdbc:h2:file:/tmp/h2/keycloakdb;NON_KEYWORDS=VALUE' \
    --http-management-port 9999"
```

