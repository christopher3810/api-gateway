package com.tars.gateway.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.UUID

/**
 * 요청 추적을 위한 글로벌 필터
 * 
 * 모든 요청에 대해 traceId를 확인하고, 없는 경우 생성하여 추가합니다.
 * 해당 traceId는 로깅 및 마이크로서비스 간 요청 추적에 사용됩니다.
 */
@Component
class TraceFilter : GlobalFilter, Ordered {
    private val logger = LoggerFactory.getLogger(TraceFilter::class.java)
    
    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val request = exchange.request
        
        // X-B3-TraceId 헤더가 있는지 확인
        val traceId = request.headers.getFirst("X-B3-TraceId") ?: generateTraceId()
        
        // traceId가 없는 경우 새로 생성하여 추가
        val modifiedRequest = request.mutate()
            .header("X-B3-TraceId", traceId)
            .build()
        
        // 요청 로깅
        logger.debug("Request traceId: $traceId, path: ${request.path}")
        
        // 수정된 요청으로 필터 체인 계속 진행
        return chain.filter(exchange.mutate().request(modifiedRequest).build())
    }
    
    /**
     * 새로운 traceId를 생성합니다.
     */
    private fun generateTraceId(): String {
        return UUID.randomUUID().toString().replace("-", "")
    }
    
    /**
     * 필터 순서를 설정합니다. 
     * 낮은 값일수록 먼저 실행됩니다.
     */
    override fun getOrder(): Int = Ordered.HIGHEST_PRECEDENCE
} 