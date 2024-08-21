package library;

import java.util.ArrayList;

public class ViewBooks implements IOOperation{

	@Override
	public void oper(Database database,User user) {
		ArrayList<Book> books = database.getAllBooks();
		System.out.println("Name\t Author \t Publisher \tCollection Location Address \t Status \t Qty \t Price\t Borrowing Copies");
		for(Book b: books) {
			System.out.println(b.getName()+ "\t" + b.getAuthor() + "\t" +b.getPublisher()+ "\t" + b.getAddress()  +"\t" + b.getQty() + "\t" +b.getPrice()+"\t" +b.getBrwcopies());
		}
	}
		
}
