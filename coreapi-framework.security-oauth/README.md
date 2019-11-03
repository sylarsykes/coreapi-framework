# Coreapi-Framework Security Oauth

Library with security oauth basic configurations.

## How to use library?

* Add repository in pom

```
<dependency>
	<groupId>org.sylrsykssoft</groupId>
	<artifactId>coreapi.framework.security-oauth</artifactId>
	<version>${coreapi.framework.security.oauth.version}</version>
</dependency>
```

* Configuration class, add annotations in configuration class

```java
@Import({ CoreApiFrameworkSecurityWebSecurityConfiguration.class,
	CoreApiFrameworkSecurityOAuth2DataSourceConfiguration.class,
	CoreaApiFrameworkSecurityOAuth2Configuration.class })
@EnableAuthorizationServer
```

## Built with

* [Maven](https://mvnrepository.com/) Dependency manager
* [Spring Security](https://spring.io/projects/spring-security)
* [CoreApi Framework Security](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.security)

## Version

0.0.1-SNAPSHOT. We use [SemVer](https://semver.org/) for versioning. For all available versions, see the [tags](https://github.com/sylarsykes/coreapi-framework/tags) in this repository.

## Authors

* Juan González Fernández [juan.gonzalez.fernandez.jgf](https://github.com/sylarsykes)

## License

The Core API Framework is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
