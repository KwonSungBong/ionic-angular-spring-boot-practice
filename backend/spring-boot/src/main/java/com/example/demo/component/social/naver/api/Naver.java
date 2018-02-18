package com.example.demo.component.social.naver.api;

import com.example.demo.component.social.naver.api.abstracts.UserOperation;
import org.springframework.social.ApiBinding;

public interface Naver extends ApiBinding {
	UserOperation userOperation();
}
