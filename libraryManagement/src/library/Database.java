package library;

import java.io.File;
import java.io.*;
import java.util.ArrayList;
public class Database {
	
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<String> usernames = new ArrayList<String>();
	private ArrayList<Book> books = new ArrayList<Book>();
	private ArrayList<String> booknames = new ArrayList<String>();
	
	private File usersfile = new File("C:\\Coding Practice\\Java\\Eclipse Projects\\libraryManagement\\data\\Users");
	private File booksfile = new File("C:\\Coding Practice\\Java\\Eclipse Projects\\libraryManagement\\data\\Books");
	private File folder = new File("C:\\Coding Practice\\Java\\Eclipse Projects\\libraryManagement\\data");
	public Database() {
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		if(!usersfile.exists()) {
			try {
			usersfile.createNewFile();
			}catch(Exception e) {}
		}
		if(!booksfile.exists()) {
			try {
			booksfile.createNewFile();
			}catch(Exception e) {}
		}
		this.getUsers();
		this.getBooks();

	}
	
	public void  AddUser(User s) {
		users.add(s);
		usernames.add(s.getName());
		saveUsers();
	}
	
	public int login(String phonenumber, String email) {
		int n = -1;
		for(User s: users) {
			if(s.getPhoneNumber().matches(phonenumber) && s.getEmail().matches(email)) {
				n  = users.indexOf(s);
				break;
			}
		}
		return n;
	}
	public User getUser(int n) {
		return users.get(n);
	}
	
	public void AddBook(Book book) {
		books.add(book);
		booknames.add(book.getName());
		saveBooks();
	}
	private void getUsers() {
		String text1 = "";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(usersfile));
			String s1;
			while((s1 = br1.readLine())  != null) {
				text1 = text1 +s1;
			}
			br1.close();
		}catch (Exception e){
			System.err.println(e.toString());
		}
		
		if(!text1.matches("")|| !text1.isEmpty()) {
			String[] a1 = text1.split("<NewUser/>");
			for(String s:a1) {
				String[] a2 = s.split("<N/>");
				if(a2[3].matches("Admin")) {
					User user = new Admin(a2[0], a2[1],a2[2]);
					users.add(user);
					usernames.add(user.getName());
				}else {
					User user = new NormalUser(a2[0], a2[1], a2[2]);
					users.add(user);
					usernames.add(user.getName());
				}
			}
		}
	}
	
	private void saveUsers() {
		String text1 = "";
		for(User user : users) {
			text1 = text1 + user.toString() + "<NewUser/>\n";
		}
		try {
			PrintWriter pw = new PrintWriter(this.usersfile);
			pw.print(text1);
			pw.close();
		}catch(Exception e) {
			System.err.println(e.toString());
		}
	}
	private void saveBooks() {
		String text1 = "";
		for(Book book : books) {
			text1 = text1 + book.toString() + "<NewBook/>\n";
		}
		try {
			PrintWriter pw = new PrintWriter(this.booksfile);
			pw.print(text1);
			pw.close();
		}catch(Exception e) {
			System.err.println(e.toString());
		}
	}
	

	private void getBooks() {
	    String text1 = "";
	    try {
	        BufferedReader br1 = new BufferedReader(new FileReader(booksfile));
	        String s1;
	        while ((s1 = br1.readLine()) != null) {
	            text1 += s1; // Accumulate lines
	        }
	        br1.close();
	    } catch (Exception e) {
	        System.err.println(e.toString());
	    }

	    if (!text1.isEmpty()) {
	        // Split by <NewBook/> and process each part
	        String[] bookEntries = text1.split("<NewBook/>");
	        for (String entry : bookEntries) {
	            Book book = parseBook(entry.trim());
	            if (book != null) {
	                books.add(book);
	                booknames.add(book.getName());
	            }
	        }
	    }
	}
//	public Book parseBook(String s) {
//	    
//
//	    try {
//	    	 String[] lines = s.split("\n");
//
//		if (lines.length != 8) {
//            System.err.println("Skipping malformed input: " + s);
//            return null;
//        }
//
//        // Extracting the values based on the labels
//        String name = extractValue(lines[0], "Book Name: ");
//        String author = extractValue(lines[1], "Book Author: ");
//        String publisher = extractValue(lines[2], "Book Publisher: ");
//        String address = extractValue(lines[3], "Book Collection Address: ");
//        String status = extractValue(lines[4], "Book status: ");
//        int qty = Integer.parseInt(extractValue(lines[5], "Qty: "));
//        double price = Double.parseDouble(extractValue(lines[6], "Price: "));
//        int brwcopies = Integer.parseInt(extractValue(lines[7], "Borrowing Copies: "));
//
//        // Creating the Book object with extracted values
//        Book book = new Book();
//        book.setName(name.trim());
//        book.setAuthor(author.trim());
//        book.setPublisher(publisher.trim());
//        book.setAddress(address.trim());
//        book.setStatus(status.trim());
//        book.setQty(qty);
//        book.setPrice(price);
//        book.setBrwcopies(brwcopies);
//
//        return book;
//	    } catch (NumberFormatException e) {
//	        System.err.println("Skipping malformed input (NumberFormatException): " + s);
//	        return null;
//	    }
//	}
//
//		private String extractValue(String line, String label) {
//   		 return line.substring(line.indexOf(label) + label.length()).trim();
//		}
	
	
	public Book parseBook(String s) {
	    try {
	        // Split the string by semicolon (;) since that's the delimiter used in the file
	        String[] fields = s.split(";");

	        // Ensure there are exactly 8 fields
	        if (fields.length != 8) {
	            System.err.println("Skipping malformed input: " + s);
	            return null;
	        }

	        // Create a Book object with extracted values
	        Book book = new Book();
	        book.setName(fields[0].trim());
	        book.setAuthor(fields[1].trim());
	        book.setPublisher(fields[2].trim());
	        book.setAddress(fields[3].trim());
	        book.setStatus(fields[4].trim());
	        book.setQty(Integer.parseInt(fields[5].trim()));
	        book.setPrice(Double.parseDouble(fields[6].trim()));
	        book.setBrwcopies(Integer.parseInt(fields[7].trim()));

	        return book;
	    } catch (NumberFormatException e) {
	        System.err.println("Skipping malformed input (NumberFormatException): " + s);
	        return null;
	    }
	}



	public ArrayList<Book> getAllBooks(){
		return books;
	}
	public int getBook(String Bookname) {
		int i =-1;
		for(Book book: books) {
			if(book.getName().matches(Bookname)) {
				i = books.indexOf(book);
			}
		}
		return i;
	}

	public Book getBook(int i) {
		return books.get(i);
	}
	
	public void deleteBook(int i) {
		books.remove(i);
		booknames.remove(i);
		saveBooks();
	}
	

}
