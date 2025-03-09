![gateway](https://github.com/user-attachments/assets/89147d58-d624-42b0-8fb6-729dc51da412)

# API Gateway

API Gateway 는 Micro Service 들의 진입점 역할을 하는 Spring Cloud Gateway 기반 서비스.
 
이 모듈은 요청 라우팅, 로드 밸런싱, 서킷 브레이킹, 토큰 전달 및 분산 추적을 담당.

## 주요 기능

- **요청 라우팅**: 각 마이크로서비스로 요청을 적절히 전달
- **로드 밸런싱**: 서비스 인스턴스 간 부하 분산 (Eureka 의존성 없이 구현)
- **서킷 브레이커**: 장애 격리 및 복원력 제공 (Resilience4j 활용)
- **토큰 확인 및 전달**: 최소한의 토큰 유효성 확인 및 하위 서비스로 전달
- **분산 추적**: Micrometer Tracing을 통한 TraceID 생성 및 전파
- **간단한 요청 제한**: 서비스별 기본 제한 설정

## Redis 활용

Redis 는 다음 용도로 제한적으로 사용할까 고민중
- 요청 제한(Rate Limiting) 정보 저장 (필요시)
- 서킷 브레이커 상태 공유 (선택적)
