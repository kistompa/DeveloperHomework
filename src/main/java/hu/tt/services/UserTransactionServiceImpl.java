package hu.tt.services;

import java.util.List;

import javax.persistence.EntityManager;

import hu.tt.controller.MyEntityManager;
import hu.tt.model.User;
import hu.tt.model.UserTransaction;

public class UserTransactionServiceImpl implements UserTransactionService {

	public List<UserTransaction> getHistory(long id) {
		
		EntityManager em = MyEntityManager.getEmf().createEntityManager();
		List<UserTransaction> hist = null;// em.find(User.class, id);

		javax.persistence.Query query = em.createQuery("SELECT u FROM UserTransaction u where u.user = :id");
		query.setParameter("id", id);
		hist =  query.getResultList();


		
		
		return hist;
	}

}
