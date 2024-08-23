package library;

import java.util.ArrayList;

public class ViewBooks implements IOOperation{

	@Override
	public void oper(Database database,User user) {
		ArrayList<Book> books = database.getAllBooks();
		System.out.println("Name \t Author \tPublisher \tCollection Location Address \tStatus \tQty \tPrice \tBorrowing Copies");
		for(Book b: books) {
			System.out.println(b.getName()+ " \t " + b.getAuthor() + "\t\t" +b.getPublisher()+ "\t\t" + b.getAddress() + "\t\t\t\t" + b.getStatus() +"\t" + b.getQty() + "\t" +b.getPrice()+"\t" +b.getBrwcopies());
		}

		user.menu(database,user);
	}
		
}
