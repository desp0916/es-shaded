# Shaded Jar for ElasticSearch 2.2.1

## What is this?

This is the shaded jar for ElasticSearch 2.2.1. I embed this jar to my [Storm](http://storm.apache.org/) uber jar. You can modify `pom.xml` to resolve the problem about dependency conflicts. Before using it, please modify any settings in `pom.xml` to suit your needs.

You are also encouraged to read the following references:

  * [Elastic: To shade or not to shade](https://www.elastic.co/blog/to-shade-or-not-to-shade)
  * [What is the maven-shade-plugin used for, and why would you want to relocate java packages?](http://stackoverflow.com/questions/13620281/what-is-the-maven-shade-plugin-used-for-and-why-would-you-want-to-relocate-java)
  * [STORM : How do I fix the google.guava dependency while running mvn clean install -DskipTests=true ?](https://community.hortonworks.com/questions/14998/storm-how-do-i-fix-the-googleguava-dependency-whil.html)
  * [Apache Maven Shade Plugin](https://maven.apache.org/plugins/maven-shade-plugin/shade-mojo.html)
  
## How to use?

Just clone the project and install to your local Maven repository (I use Maven 3.2.5): 

```bash
git clone https://github.com/desp0916/es-shaded.git
cd es-shaded
mvn clean install
```
You will install the result jar file to your local Maven repository (`~/.m2/repository/my/elasticsearch/test/es-shaded/`). Then you can just embeded this jar to your uber jar's `pom.xml` like this:

```xml
<dependency>
	<groupId>my.elasticsearch.test</groupId>
	<artifactId>es-shaded</artifactId>
	<version>1.0-SNAPSHOT</version>
</dependency>
```

Finally, rebuild your uber jar like this:

```bash
maven clean package
```
Happy Enjoying!