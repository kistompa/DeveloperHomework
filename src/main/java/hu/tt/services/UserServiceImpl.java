package hu.tt.services;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import hu.tt.controller.MyEntityManager;
import hu.tt.model.TransactionType;
import hu.tt.model.User;
import hu.tt.model.UserTransaction;

public class UserServiceImpl implements UserService {

	@Override
	public User saveUser(User user) {
		EntityManager em = MyEntityManager.getEmf().createEntityManager();
		EntityTransaction t = em.getTransaction();
		User usr = null;
		try {
			t.begin();
			em.persist(user);
			t.commit();
			usr = em.find(User.class, user.getId());
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			throw new RuntimeException(e);
		} finally {
			em.close();
		}

		return usr;
	}

	@Override
	public boolean transfer(User user, User toUser, BigDecimal value) {
		EntityManager em = MyEntityManager.getEmf().createEntityManager();
		EntityTransaction t = em.getTransaction();

		try {
			t.begin();

			user = em.find(User.class, user.getId());
			user.setDebit(user.getDebit().subtract(value));
			
			toUser = em.find(User.class, toUser.getId());
			toUser.setDebit(toUser.getDebit().add(value));

			UserTransaction userTransaction = new UserTransaction();
			userTransaction.setUser(user.getId());
			userTransaction.setTransactionType(TransactionType.Transfer.toString());
			userTransaction.setTransactionDate(new Date());
			userTransaction.setValue((value.multiply(BigDecimal.valueOf(Long.valueOf("-1")))));
			userTransaction.setToUser(toUser.getId());

			em.persist(user);
			em.persist(toUser);
			em.persist(userTransaction);
			t.commit();

			return true;

		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			throw new RuntimeException(e);

		} finally {
			em.close();
		}
	}

	@Override
	public boolean deposit(User user, BigDecimal value) {

		EntityManager em = MyEntityManager.getEmf().createEntityManager();
		EntityTransaction t = em.getTransaction();

		try {
			t.begin();

			user = em.find(User.class, user.getId());
			user.setDebit(user.getDebit().add(value));

			UserTransaction userTransaction = new UserTransaction();
			userTransaction.setUser(user.getId());
			userTransaction.setTransactionType(TransactionType.Deposit.toString());
			userTransaction.setTransactionDate(new Date());
			userTransaction.setValue(value);

			//em.persist(user);
			em.persist(userTransaction);
			t.commit();

			return true;

		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			throw new RuntimeException(e);

		} finally {
			em.close();
		}

	}

	@Override
	public boolean deposit(User user, int i) {

		return deposit(user, new BigDecimal(i));
	}

	@Override
	public boolean Withdrawal(User user, BigDecimal value) {
		EntityManager em = MyEntityManager.getEmf().createEntityManager();
		EntityTransaction t = em.getTransaction();

		try {
			t.begin();

			user = em.find(User.class, user.getId());
			user.setDebit(user.getDebit().subtract(value));

			UserTransaction userTransaction = new UserTransaction();
			userTransaction.setUser(user.getId());
			userTransaction.setTransactionType(TransactionType.Withdrawal.toString());
			userTransaction.setTransactionDate(new Date());
			userTransaction.setValue(value.multiply(BigDecimal.valueOf(Long.valueOf("-1"))));

			em.persist(user);
			em.persist(userTransaction);
			t.commit();

			return true;

		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			throw new RuntimeException(e);

		} finally {
			em.close();
		}
	}

	@Override
	public User getUser(Long id) {

		EntityManager em = MyEntityManager.getEmf().createEntityManager();
		User usr = null;// em.find(User.class, id);

		javax.persistence.Query query = em.createQuery("SELECT u FROM User u where u.id = :id");
		query.setParameter("id", id);
		usr = (User) query.getSingleResult();

		em.close();

		return usr;
	}

}
