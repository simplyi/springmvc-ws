package com.appsdeveloperblog.app.ws.ui.contoller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
import com.appsdeveloperblog.app.ws.ui.model.request.LoginRequestModel;

@RestController
public class AuthenticationController {
 
	@PostMapping("/users/login")
	public void theFakeLogin(@RequestBody LoginRequestModel loginRequestModel)
	{
		throw new IllegalStateException("This method should not be called. This method is implemented by Spring Security");
	}
}
