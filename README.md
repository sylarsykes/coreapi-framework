# Coreapi-Framework

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/4bafd7cfbd4140f8addf945bcfa65e06)](https://www.codacy.com/app/juan.gonzalez.fernandez.jgf/coreapi-framework?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=sylarsykes/coreapi-framework&amp;utm_campaign=Badge_Grade)

Core API Framework is a generic project that serves as the basis for other projects. It is composed of the subprojects [Library](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.library), [API](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.api), [Database](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.database), [Service](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.service), [Web](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.web), [Audit](https://github.com/sylarsykes/coreapi-framework/tree/develop/coreapi-framework.audit), [Mail](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.mail), [Boot](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.boot).

## Features

* [Library](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.library) Library of utilities
* [API](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.api) Library define the basic classes for entities and dtos of projects
* [Database](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.database) Library with repository basic classes
* [Service](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.service) Library with service basic classes
* [Web](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.web) Library with controller basic classes
* [Audit](https://github.com/sylarsykes/coreapi-framework/tree/develop/coreapi-framework.audit) Library with audit basic classes
* [Mail](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.mail) Library with mail basic clasesses
* [Boot](https://github.com/sylarsykes/coreapi-framework/tree/master/coreapi-framework.boot) Parent project with spring boot basic dependecy

## How to use libraries?

* Add repository in pom

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

* Library, import dependency

```
<dependency>	
	<groupId>org.sylrsykssoft</groupId>
	<artifactId>coreapi-framework.library</artifactId>
	<version>${coreapi-framework.library.version</version>
</dependency>
```

* API, import dependency

```
<dependency>	
	<groupId>org.sylrsykssoft</groupId>
	<artifactId>coreapi-framework.api</artifactId>
	<version>${coreapi-framework.api.version</version>
</dependency>
```

* Database, import dependency

```
<dependency>	
	<groupId>org.sylrsykssoft</groupId>
	<artifactId>coreapi-framework.database</artifactId>
	<version>${coreapi-framework.database.version</version>
</dependency>
```

* Service, import dependency

```
<dependency>	
	<groupId>org.sylrsykssoft</groupId>
	<artifactId>coreapi-framework.service</artifactId>
	<version>${coreapi-framework.service.version</version>
</dependency>
```

* Web, import dependency

```
<dependency>	
	<groupId>org.sylrsykssoft</groupId>
	<artifactId>coreapi-framework.web</artifactId>
	<version>${coreapi-framework.web.version</version>
</dependency>
```

* Audit, import dependency

```
<dependency>
	<groupId>org.sylrsykssoft</groupId>
	<artifactId>coreapi-framework.audit</artifactId>
	<version>${coreapi-framework.audit.version}</version>
</dependency>
```

* Mail, import dependency

```
<dependency>	
	<groupId>org.sylrsykssoft</groupId>
	<artifactId>coreapi-framework.mail</artifactId>
	<version>${coreapi-framework.mail.version</version>
</dependency>
```

* Boot, import dependency

```
<parent>
	<groupId>org.sylrsykssoft</groupId>
  	<artifactId>coreapi-framework.boot</artifactId>
  	<version>0.0.1-SNAPSHOT</version>
  	<relativePath/>
</parent>
```

## Built with

* [Maven](https://mvnrepository.com/) Dependency manager
* [Spring Framework](https://github.com/spring-projects/spring-framework)
* [Apache Commons](https://github.com/apache/commons-lang)
* [Apache Commons Collections](https://github.com/apache/commons-collections/)
* [Lombok](https://projectlombok.org/)
* [Modelmapper](http://modelmapper.org/getting-started/)

## Version

0.0.1-SNAPSHOT. We use [SemVer](https://semver.org/) for versioning. For all available versions, see the [tags](https://github.com/sylarsykes/coreapi-framework/tags) in this repository.

## Authors

* Juan González Fernández [juan.gonzalez.fernandez.jgf](https://github.com/sylarsykes)


## Related projects

*  [Musbands](https://github.com/sylarsykes/java-musbands)
*  [Musbands Admin](https://github.com/sylarsykes/java-musbands-admin)

## License

The Core API Framework is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
