# Coreapi-Framework Boot

Core API Framework Boot it is a parent project with the dependence of the latest stable version of Spring Boot, for use in other projects. This project includes the modules [Library](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.library), [API](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.api), [Database](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.database), [Service](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.service), [Web](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.web), [Mail](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.mail).


## Features

* [Library](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.library) Library of utilities
* [API](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.api) Library define the basic classes for entities and dtos of projects
* [Database](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.database) Library with repository basic classes
* [Service](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.service) Library with service basic classes
* [Web](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.web) Library with controller basic classes
* [Audit](https://github.com/sylarsykes/coreapi-framework/tree/develop/coreapi-framework.audit) Library with audit basic classes
* [Mail](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.mail) Library with mail basic clasesses


## How to use libraries?

*  Add repository in pom

```
<!-- Core API Framework -->
<repositories>
	<repository>
		<id>coreapi-framework</id>
		<url>https://github.com/sylarsykes/coreapi-framework</url>
		<snapshots>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
		</snapshots>
	</repository>
</repositories>
```

*  Boot, import dependency

```
<parent>
	<groupId>org.sylrsykssoft</groupId>
  	<artifactId>coreapi-framework.boot</artifactId>
  	<version>0.0.1-SNAPSHOT</version>
  	<relativePath/>
</parent>
```


## Built with

*  [Maven](https://mvnrepository.com/) Dependency manager
*  [Spring Framework](https://github.com/spring-projects/spring-framework)
*  [Apache Commons](https://github.com/apache/commons-lang)
*  [Apache Commons Collections](https://github.com/apache/commons-collections/)
*  [Lombok](https://projectlombok.org/)
*  [Modelmapper](http://modelmapper.org/getting-started/)


## Version

0.0.1-SNAPSHOT. We use [SemVer](https://semver.org/) for versioning. For all available versions, see the [tags](https://github.com/sylarsykes/coreapi-framework/tags) in this repository.


## Authors

*  Juan González Fernández [juan.gonzalez.fernandez.jgf](https://github.com/sylarsykes)


## Related projects

- [Musbands](https://github.com/sylarsykes/java-musbands)


## License

The Core API Framework is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
