package kr.or.ddit.yguniv.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import kr.or.ddit.yguniv.log.service.LogService;
import kr.or.ddit.yguniv.vo.LogVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ControllerInterceptor implements HandlerInterceptor {
    
	private final LogService service;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	LogVO logVo = new LogVO();
        
        if (handler instanceof HandlerMethod) {
        	 // Spring Security의 Authentication 객체에서 사용자 정보 가져오기
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userId = "unknown";
            if (authentication != null && authentication.isAuthenticated()) {
                userId = authentication.getName(); // 인증된 사용자 ID 가져오기
            } 
            //ResourceHttpRequestHandler handlerMethod = (ResourceHttpRequestHandler) handler; 정적 요청의 경우
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String method = request.getMethod();
            String controllerName = handlerMethod.getBeanType().getSimpleName();
            if(controllerName.equals("MyCalendarController")||controllerName.equals("NotificationController")) {
            	return true;            	
            }
            logVo.setId(userId);
            logVo.setLogContNm(controllerName);
            logVo.setLogMethod(method);
            service.mergeIntoLog(logVo);
        }
        
        return true;
    }
}

















