package hu.tt.services;

import java.math.BigDecimal;

import hu.tt.model.User;

public interface UserService {
	
	User saveUser(User user);
	
	boolean transfer(User user, User toUser, BigDecimal value );
	boolean deposit(User user, BigDecimal value);
	boolean Withdrawal(User user, BigDecimal value);
	
	User getUser(Long id);

	boolean deposit(User user, int i);

}
