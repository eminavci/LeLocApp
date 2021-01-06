package com.webproject.rest.mvc.exception;

import java.util.Map;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.webproject.core.common.api.ErrKeys;
import com.webproject.core.services.exceptions.LeLocAppCoreException;
import com.webproject.rest.exceptions.ApiRestException;
import com.webproject.spring.common.LELOCAPP;
import com.webproject.spring.service.MessageByLocaleService;


@Controller
@ControllerAdvice
public class ExceptionHandlerController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String APIERRCODE 		= "apiErrCode";
	private static final String APIMESSAGE 		= "apiMessage";
	private static final String APIMSGPARAMS 	= "apiMsgParams"; 
	@Autowired
	MessageByLocaleService messageService;
	
//	@RequestMapping("/app-error")
//	public @ResponseBody void WebError(HttpServletRequest request, HttpServletResponse response, Exception exception) throws Exception
//	{
//		if (request.getAttribute(WebAttributes.ACCESS_DENIED_403) != null){
//			throw ((Exception) request.getAttribute(WebAttributes.ACCESS_DENIED_403));
//		}else if (request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) != null){
//			throw ((Exception) request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION));
//		}else{
//			switch (response.getStatus())
//			{
//				case 404: throw new NotFoundException();
//				case 405: throw new NotFoundException();
//				default: throw new Exception();
//			}
//		}
//	}
	
	@ExceptionHandler({Exception.class, UsernameNotFoundException.class})
	public ResponseEntity<Map<String, Object>> handleException(Exception exception, HttpServletRequest httpRequest){
		logger.error("> handleException");
		logger.error("ERROR", exception);
		
		ResponseEntity<Map<String, Object>> response;
		Throwable t = exception.getCause();
		ApiRestException ex;
		if(t != null)
			if(t instanceof LeLocAppCoreException)
				response = initExceptionResponse(new ApiRestException(t), httpRequest);
			else if(t instanceof ApiRestException)
				response = initExceptionResponse(((ApiRestException)t), httpRequest);
			else
				response = initExceptionResponse(new ApiRestException(exception), httpRequest);
		else
			response = initExceptionResponse(new ApiRestException(exception), httpRequest);

		
		logger.error("< handleException");
		return response;
	}
	
	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<Map<String, Object>> handleNoResultException(NoResultException exception, HttpServletRequest httpRequest){
		logger.info("> handleNoResultException");
		logger.info("ERROR : " + exception.toString());
		
		ExceptionAttributes exceptionAttr = new DefaultExceptionAttributes();
		
		Map<String, Object> responseBody = exceptionAttr.getExceptionAttributes(exception, httpRequest, HttpStatus.NOT_FOUND);
		responseBody.put(APIERRCODE, ErrKeys.GENERAL_NOT_FOUND_ERROR);
		responseBody.put(APIMSGPARAMS, new Object[0]);
		
		// TODO add appropriate localized message here
		responseBody.put(APIMESSAGE, "Now just default message!");
		
		logger.info("< handleNoResultException");
		return new ResponseEntity<Map<String,Object>>(responseBody, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ApiRestException.class})
	public ResponseEntity<Map<String, Object>> handleCustomException(ApiRestException exception, HttpServletRequest httpRequest){
		
		logger.error("> handleCustomException");
		logger.error("ERROR", exception);
		
		ResponseEntity<Map<String,Object>> responseBody = initExceptionResponse(exception, httpRequest);
		
		logger.error("< handleCustomException");
		return responseBody;
	}
	
	@ExceptionHandler({LeLocAppCoreException.class})
	public ResponseEntity<Map<String, Object>> handleServiceException(LeLocAppCoreException exception, HttpServletRequest httpRequest){
		logger.error("> handleServiceException");
		logger.error("ERROR", exception);
		
		ResponseEntity<Map<String,Object>> responseBody = initExceptionResponse(new ApiRestException(exception), httpRequest);
		
		logger.error("< handleServiceException");
		return responseBody;
	}
	
	private ResponseEntity<Map<String, Object>> initExceptionResponse(ApiRestException exception, HttpServletRequest httpRequest){
		ExceptionAttributes exceptionAttr = new DefaultExceptionAttributes();
		Map<String, Object> responseBody = exceptionAttr.getExceptionAttributes(exception, httpRequest, exception.getHttpStatus());
		int errCode = exception.getErrCode();
		Object params[] = exception.getParams();
		
		responseBody.put(APIERRCODE, errCode);
		// TODO add appropriate localized message here
		if(LELOCAPP.env.isDEV())
			responseBody.put(APIMESSAGE, "An error Accoured try again later : " + exception.getMessage());
		else
			responseBody.put(APIMESSAGE, messageService.getMessage(String.valueOf(errCode), params));
		
		return new ResponseEntity<Map<String,Object>>(responseBody, exception.getHttpStatus());
	}	
}
