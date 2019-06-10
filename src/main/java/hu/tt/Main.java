package hu.tt;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import hu.tt.controller.MyEntityManager;
import hu.tt.model.TransactionType;
import hu.tt.model.User;
import hu.tt.services.UserService;
import hu.tt.services.UserServiceImpl;
import hu.tt.services.UserTransactionService;
import hu.tt.services.UserTransactionServiceImpl;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			TransactionType command;
			if (args.length > 0) {
				command = TransactionType.valueOf(args[0]);
			} else {
				command = TransactionType.User;
			}
			UserService us = new UserServiceImpl();
			switch (command) {
			case Withdrawal:

				if (us.Withdrawal(us.getUser(Long.valueOf(args[1])), BigDecimal.valueOf(Long.valueOf(args[2])))) {
					System.out.println("Withdrawal \n" + us.getUser(Long.valueOf(args[1])) + "\n Completed");

				} else {
					System.out.println("Withdrawal \n" + us.getUser(Long.valueOf(args[1])) + "\n Faild");
				}

				break;

			case Deposit:
				if (us.deposit(us.getUser(Long.valueOf(args[1])), BigDecimal.valueOf(Long.valueOf(args[2])))) {
					System.out.println("Deposit \n" + us.getUser(Long.valueOf(args[1])) + "\n Completed");
				} else {
					System.out.println("Deposit \n" + us.getUser(Long.valueOf(args[1])) + "\n Faild");
				}
				break;

			case Transfer:
				if (us.transfer(us.getUser(Long.valueOf(args[1])), us.getUser(Long.valueOf(args[2])),
						BigDecimal.valueOf(Long.valueOf(args[3])))) {
					System.out.println("Transfer \n" + "From user" + us.getUser(Long.valueOf(args[1])) + "To user"
							+ us.getUser(Long.valueOf(args[2])) + "\n Completed");
				} else {
					System.out.println("Transfer \n" + "From user" + us.getUser(Long.valueOf(args[1])) + "To user"
							+ us.getUser(Long.valueOf(args[2])) + "\n Faild");
				}
				break;

			case History:
				UserTransactionService ut = new UserTransactionServiceImpl();
				System.out.println(ut.getHistory(Long.valueOf(args[1])).stream().map(item->item.toString()).collect(Collectors.joining("\n")));
				break;

			default:
				User u = new User();
				u.setName("Test Egy");
				u.setDebit(BigDecimal.valueOf(0L));
				System.out.println(us.saveUser(u));

				u = new User();
				u.setName("Test Ketto");
				u.setDebit(BigDecimal.valueOf(0L));
				System.out.println(us.saveUser(u));

				u = new User();
				u.setName("Test Harom");
				u.setDebit(BigDecimal.valueOf(0L));
				System.out.println(us.saveUser(u));

				break;
			}
		} finally {

			MyEntityManager.closeEntyyManaggerFactroy();
		}

	}

}
