# SpringArq
Simple SpringBoot, rest, services oriented .

## Using Docker to simplify development (optional)

You can use Docker to improve your development experience. docker-compose files configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a Postgresql database in a docker container, run:

```
docker-compose -f src/main/docker/postgresql.yml up -d
```

To stop it and remove the container, run:

```
docker-compose -f src/main/docker/postgresql.yml down
```

## Web Site
To fully see this project you might want to checkout https://github.com/fedefailla18/react-work-helper. Following its README you are going to be able to see the web.