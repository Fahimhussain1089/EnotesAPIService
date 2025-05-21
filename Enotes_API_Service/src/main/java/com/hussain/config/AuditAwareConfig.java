package com.hussain.config;

import java.util.Optional;

import org.hibernate.annotations.Comment;
import org.springframework.data.domain.AuditorAware;

import com.hussain.entity.User;
import com.hussain.util.CommonUtil;

public class AuditAwareConfig implements AuditorAware<Integer>  {
	@Override
	public Optional<Integer> getCurrentAuditor() {
		User loggedInUser = CommonUtil.getLoggedInUser();
		return Optional.of(loggedInUser.getId());
	}

}
