package library;

import java.util.Scanner;

public class AddBook implements IOOperation{
	@Override
	public void oper(Database database,User user) {
		
		Scanner s = new Scanner(System.in);
		Book book = new Book();
		System.out.println("Enter book name: ");
		book.setName(s.next());
		System.out.println("Enter book Author: ");
		book.setAuthor(s.next());
		System.out.println("Enter book publisher: ");
		book.setPublisher(s.next());
		System.out.println("Enter book Collection Location: ");
		book.setAddress(s.next());
		System.out.println("Enter qty: ");
		book.setQty(s.nextInt());
		System.out.println("Enter price: ");
		book.setPrice(s.nextInt());
		System.out.println("Enter borrowing copies: ");
		book.setBrwcopies(s.nextInt());
		database.AddBook(book);
		System.out.println("Book Added successfully\n ");
		user.menu(database,user);
		s.close();
	}
	
}