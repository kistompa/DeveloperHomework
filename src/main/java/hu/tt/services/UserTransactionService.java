package hu.tt.services;

import java.util.List;

import hu.tt.model.UserTransaction;

public interface UserTransactionService {
	
	List<UserTransaction> getHistory(long id);

}
