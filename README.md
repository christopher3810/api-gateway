# API Gateway

API Gateway 모듈은 모든 클라이언트 요청의 진입점 역할을 하는 Spring Cloud Gateway 기반 서비스입니다.\ 
이 모듈은 인증/인가 검증, 요청 라우팅, 그리고 분산 추적을 담당합니다.

## 주요 기능

- **중앙화된 라우팅**: 모든 마이크로서비스로의 요청을 단일 진입점에서 관리
- **인증/인가 필터**: JWT 토큰 기반 인증 및 권한 검증
- **분산 추적**: micrometer 를 통한 요청 추적 및 모니터링

## 기술 스택

- Spring Cloud Gateway 2023.0.1
- Spring Boot 3.4.3
- micrometer (분산 추적)
- Kotlin 2.0.0
- JWT (JSON Web Token) 0.12.6

## redis 활용
고민중