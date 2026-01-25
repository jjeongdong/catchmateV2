# 1. Build Stage
FROM openjdk:17-jdk-slim AS builder

WORKDIR /app

# Gradle Wrapper 복사 (권한 부여 포함)
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# 각 모듈의 build.gradle 복사 (멀티 모듈 구조 반영)
COPY catchmate-api/build.gradle catchmate-api/
COPY catchmate-application/build.gradle catchmate-application/
COPY catchmate-boot/build.gradle catchmate-boot/
COPY catchmate-common/build.gradle catchmate-common/
COPY catchmate-domain/build.gradle catchmate-domain/
COPY catchmate-infrastructure/build.gradle catchmate-infrastructure/

# 종속성 다운로드 (소스 코드 복사 전에 수행하여 캐싱 활용)
RUN chmod +x ./gradlew
RUN ./gradlew dependencies --no-daemon

# 전체 소스 코드 복사 및 빌드
COPY . .
# catchmate-boot 모듈의 bootJar 실행
RUN ./gradlew :catchmate-boot:bootJar --no-daemon

# 2. Run Stage
FROM openjdk:17-jdk-slim

WORKDIR /app

# 빌드 스테이지에서 생성된 JAR 파일 복사
# catchmate-boot 모듈의 build/libs 경로에서 가져옵니다.
COPY --from=builder /app/catchmate-boot/build/libs/*.jar app.jar

# 포트 노출
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
