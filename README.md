# Shaded Jar for ElasticSearch 2.3.2

## What is this?

This is the shaded jar for ElasticSearch 2.3.2. I embed this jar to [my Storm uber jar](https://github.com/desp0916/LearnStorm/). I encountered the shaded problem about ElasticSearch 2.3.2 for several days, and after many try-and-errors, I have finally figured out the solution. 

If you are facing the same problem I met, this project might help you. You can modify `pom.xml` for your needs or resolve the problem about dependency conflicts.

I recommend you to use JDK 8, which can save you a lot of time .

You are also encouraged to read the following references:

  * [Elastic: To shade or not to shade](https://www.elastic.co/blog/to-shade-or-not-to-shade)
  * [What is the maven-shade-plugin used for, and why would you want to relocate java packages?](http://stackoverflow.com/questions/1362.3.1/what-is-the-maven-shade-plugin-used-for-and-why-would-you-want-to-relocate-java)
  * [STORM : How do I fix the google.guava dependency while running mvn clean install -DskipTests=true ?](https://community.hortonworks.com/questions/14998/storm-how-do-i-fix-the-googleguava-dependency-whil.html)
  * [Apache Maven Shade Plugin](https://maven.apache.org/plugins/maven-shade-plugin/shade-mojo.html)
  * [Using Elasticsearch Java Clients with Search Guard](https://github.com/floragunncom/search-guard/issues/53)
 
## How to use?

Just clone the project, edit the `pom.xml`(if needed), build with Maven, and install to your local repository (I use Maven 3.2.5): 

```bash
git clone https://github.com/desp0916/es-shaded.git
cd es-shaded
mvn clean install
```

The jar file will be installed to your local repository (`~/.m2/repository/my/elasticsearch/test/es-shaded/`). Then you can embeded it to your uber jar's `pom.xml` by inserting the following block:

```xml
<dependency>
	<groupId>my.elasticsearch.test</groupId>
	<artifactId>es-shaded</artifactId>
	<version>1.0-SNAPSHOT</version>
</dependency>
```

Finally, rebuild your uber jar again like this:

```bash
maven clean package
```
Happy Enjoying!
