# Coreapi-Framework Swagger

Library with Swagger2 basic configuratons.

## How to use library?

* Add repository in pom

```
<dependency>
	<groupId>org.sylrsykssoft</groupId>
	<artifactId>coreapi-framework.seagger</artifactId>
	<version>${coreapi-framework.swagger.version}</version>
</dependency>
```

* Configuration class, add annotations in configuration class

```java
@PropertySource("classpath:swagger2.properties")
@EnableConfigurationProperties(CoreApiFrameworkSwagger2ConfigProperties.class)
@EnableSwagger2
```

* Inject CoreApiFrameworkSwagger2ConfigProperties class in configuration class

```java
@Autowired
	private CoreApiFrameworkSwagger2ConfigProperties coreApiFrameworkSwagger2ConfigProperties;
```

* Define beans in configuration class

```java
private ApiInfo apiInfo() {}

/**
 * Docket api bean
 * 
 * @return Docket
 */
@Bean
public Docket musbandsAdminApi() {}

/**
 * Swagger UI configuration
  * 
  * @param configProperties
  * @return UiConfiguration
 */
@Bean
public UiConfiguration uiConfig() {}
```

* swagger2.properties configure properties

```
coreapi.framework.swagger.apiVersion=1.0
coreapi.framework.swagger.enabled=true
coreapi.framework.swagger.title=Musbands Admin API
coreapi.framework.swagger.description=Musbands Admin it is an API Rest with the administration of the master entities for Musbands API Rest. It is composed of the modules Function Member, Instument, Musical Genre, Application, Client.
coreapi.framework.swagger.contactName=Juan Gonzalez Fernandez
coreapi.framework.swagger.contactUri=https://github.com/sylarsykes
coreapi.framework.swagger.contactEmail=juan.gonzalez.fernandez.jgf@outlook.com
coreapi.framework.swagger.license=Apache License, Version 2.0
coreapi.framework.swagger.licenseUri=https://www.apache.org/licenses/LICENSE-2.0
coreapi.framework.swagger.useDefaultResponseMessages=false
coreapi.framework.swagger.enableUrlTemplating=false
coreapi.framework.swagger.deepLinking=true
coreapi.framework.swagger.defaultModelsExpandDepth=1
coreapi.framework.swagger.defaultModelExpandDepth=1
coreapi.framework.swagger.displayOperationId=false
coreapi.framework.swagger.displayRequestDuration=true
coreapi.framework.swagger.filter=false
coreapi.framework.swagger.maxDisplayedTags=0
coreapi.framework.swagger.showExtensions=false
```

## Built with

* [Maven](https://mvnrepository.com/) Dependency manager
* [Lombok](https://projectlombok.org/)
* [Spring boot](https://spring.io/projects/spring-boot)
* [Swagger2](https://swagger.io/)

## Version

0.0.1-SNAPSHOT. We use [SemVer](https://semver.org/) for versioning. For all available versions, see the [tags](https://github.com/sylarsykes/coreapi-framework/tags) in this repository.

## Authors

* Juan González Fernández [juan.gonzalez.fernandez.jgf](https://github.com/sylarsykes)

## License

The Core API Framework is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
