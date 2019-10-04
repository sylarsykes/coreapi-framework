# Coreapi-Framework Audit

Library with audit basic classes.

## How to use library?

* Add repository in pom

```
<dependency>
	<groupId>org.sylrsykssoft</groupId>
	<artifactId>coreapi-framework.audit</artifactId>
	<version>${coreapi-framework.audit.version}</version>
</dependency>
```

* Configuration class, add annotations in configuration class

```java
import static org.sylrsykssoft.coreapi.framework.audit.configuration.BaseAdminAuditConstants.AUDITORAWARE_COMPONENT_NAME;

@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@EnableJpaAuditing(auditorAwareRef = AUDITORAWARE_COMPONENT_NAME)
```

* Configuration class, inject auditorAwareImpl bean

```java
import org.sylrsykssoft.coreapi.framework.audit.configuration.CoreApiFrameworkAuditAuditorAwareImpl;

@Bean(AUDITORAWARE_COMPONENT_NAME)
@Scope(value = "prototype")
@Lazy(value = true)
public AuditorAware<String> defaultAuditorAwareImpl() {
	return new CoreApiFrameworkAuditAuditorAwareImpl();
}
```

* Entity class, extends your class of BaseAdminAudit and set Audited

```java
import org.sylrsykssoft.coreapi.framework.audit.domain.BaseAdminAudit;

@Entity(name = "example")
@Audited
@AuditOverrides(value = {
		@AuditOverride(forClass = BaseAdminAudit.class, isAudited = true),
		@AuditOverride(forClass = BaseAdmin.class, isAudited = true),
		@AuditOverride(forClass = BaseAdminSimple.class, isAudited = true),
		@AuditOverride(forClass = Base.class, isAudited = true) })
public class Example extends BaseAdminAudit
```

* application.properties configure rest base path

```
coreapi.framework.audit.rest.basePath = /api/admin/audit/v1
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

## License

The Core API Framework is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
