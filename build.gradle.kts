import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.9"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	jacoco
}

group = "com.chaos"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

jacoco {
	toolVersion = "0.8.7"
	reportsDirectory.set(layout.buildDirectory.dir("jacocoReports"))
}

val excludePackages: Iterable<String> = listOf(
	"**/com/chaos/messageorchestrator/application/config/**",
	"**/com/chaos/messageorchestrator/domain/entities/**"
)

extra["excludePackages"] = excludePackages

tasks.jacocoTestReport {
	reports {
		xml.required.set(false)
		csv.required.set(false)
		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
	}

	classDirectories.setFrom(
		sourceSets.main.get().output.asFileTree.matching {
			exclude(excludePackages)
		}
	)
}

tasks.jacocoTestCoverageVerification {
	violationRules {
		rule {
			limit {
				minimum = 0.6.toBigDecimal()
			}
		}
	}

	classDirectories.setFrom(
		sourceSets.main.get().output.asFileTree.matching {
			exclude(excludePackages)
		}
	)

	mustRunAfter(tasks["jacocoTestReport"])
}

dependencies {
	// Kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Validation
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// Health-check
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	// Cache
	implementation("org.springframework.boot:spring-boot-starter-data-redis")

	// Spring
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Kafka
	implementation("org.apache.kafka:kafka-streams")
	implementation("org.springframework.kafka:spring-kafka")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
	testImplementation("com.ninja-squad:springmockk:3.1.1")
	testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.25")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
