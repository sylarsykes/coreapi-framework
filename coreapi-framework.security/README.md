# Coreapi-Framework Security

Library with security basic configurations.

## How to use library?

* Add repository in pom

```
<dependency>
	<groupId>org.sylrsykssoft</groupId>
	<artifactId>coreapi.framework.security</artifactId>
	<version>${coreapi.framework.security.version}</version>
</dependency>
```

* Configuration class, add annotations in configuration class

```java
@Import({ CoreApiFrameworkSecurityWebSecurityConfiguration.class })
```

* application.properties configure default users

```
coreapi.framework.security.globalusers.user.name=
coreapi.framework.security.globalusers.user.password=

coreapi.framework.security.globalusers.admin.name=
coreapi.framework.security.globalusers.admin.password=
```

## Built with

* [Maven](https://mvnrepository.com/) Dependency manager
* [Spring Security](https://spring.io/projects/spring-security)

## Version

0.0.1-SNAPSHOT. We use [SemVer](https://semver.org/) for versioning. For all available versions, see the [tags](https://github.com/sylarsykes/coreapi-framework/tags) in this repository.

## Authors

* Juan González Fernández [juan.gonzalez.fernandez.jgf](https://github.com/sylarsykes)

## License

The Core API Framework is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
