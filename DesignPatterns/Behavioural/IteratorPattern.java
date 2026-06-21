import java.util.*;
import java.lang.*;
import java.io.*;

interface Iterator<T>{
    boolean hasNext();
    T next();
}

class Book{
    String name;
    public Book(String name){
        this.name = name;
    }
    public String toString(){
        return "Book: "+this.name;
    }
}

class BookIterator implements Iterator<Book>{
    Book[] books;
    int idx;
    public BookIterator(Book[] books){
        this.books = books;
        this.idx = 0;
    }
    @Override
    public boolean hasNext(){
        return idx<books.length;
    }
    @Override 
    public Book next(){
        if(!hasNext()) throw new NoSuchElementException("No More Books");
        return books[idx++];
    }
    
}

interface IteratorCreator<T>{
    Iterator<T> createIterator();
}

class IteratorCreatorConcrete implements IteratorCreator<Book>{
    Book[] books;
    public IteratorCreatorConcrete(Book[] books){
        this.books = books;
    }
    
    @Override
    public Iterator<Book> createIterator(){
        return new BookIterator(books);
    }
}
public class IteratorPattern
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Book[] books = {new Book("Novel"), new Book("Story"), new Book("Romantic")};
		IteratorCreator<Book> iteratorCreator = new IteratorCreatorConcrete(books);
		Iterator<Book> iterator = iteratorCreator.createIterator();
		
		while(iterator.hasNext()){
		    System.out.println(iterator.next());
		}

	}
}

/*

Iterator Design Pattern (Behavioral)
Definition

Iterator pattern provides a way to sequentially access elements of a collection without exposing its internal representation.

Example:

Array
List
Tree
Graph

Client should traverse them uniformly.

Problem It Solves

Without iterator:

for(int i=0; i<books.length; i++)

Problem:

Client knows collection internals (array)
If implementation changes (array → linked list), client code breaks

Iterator solves this by hiding traversal logic.

Core Idea

Instead of:

books[i]

Client uses:

iterator.hasNext()
iterator.next()

Client doesn’t care how data is stored.

Components
1. Iterator Interface

Defines traversal methods.

interface Iterator<T>{
    boolean hasNext();
    T next();
}

Responsibilities:

Check next element exists
Return next element
2. Concrete Iterator

Implements traversal logic.

Example:

class BookIterator implements Iterator<Book>

Stores traversal state:

int idx;

Responsibilities:

Maintain current position
Move to next element
3. Aggregate / Collection Interface

Creates iterator.

interface IteratorCreator<T>{
    Iterator<T> createIterator();
}

Responsibilities:

Return iterator for collection
4. Concrete Aggregate

Actual collection.

Example:

class Library implements IteratorCreator<Book>

Responsibilities:

Store elements
Create iterator
Flow
Client creates collection
Collection creates iterator
Iterator tracks current position
Client traverses using:
hasNext()
next()
Benefits
Encapsulation

Internal structure hidden.

Single Responsibility

Traversal logic separated from collection.

Uniform Traversal

Same interface for arrays, trees, linked lists.

Multiple Traversal Strategies

Same collection can have multiple iterators.

Example tree:

Inorder
Preorder
Postorder
Level order
Drawbacks
Extra objects
Slightly more code
Can be overkill for simple collections
Real Java Example

Java already uses iterator in Java Collections Framework.

List<String> list = new ArrayList<>();
Iterator<String> it = list.iterator();

while(it.hasNext()){
    System.out.println(it.next());
}
Important Exception

When no elements remain:

Use:

throw new NoSuchElementException();

Not:

throw new Exception();

Reason:

Exception is checked
NoSuchElementException is unchecked
Java iterator uses this
Interview Questions
Why not use for-loop?

For loop needs internal structure knowledge.

Example:

Array → index
Linked list → node traversal
Tree → DFS/BFS

Iterator abstracts traversal.

Main Advantage?

Best answer:

Iterator allows traversal without exposing collection internals.

Can one collection have multiple iterators?

Yes.

Example:
Tree:

DFS iterator
BFS iterator
Iterator vs Observer
Iterator	Observer
Traversal	Notification
Pull model	Push model
Client asks next	Subject sends update
Common Interview Problems
Music Playlist
Browser History
File Explorer
Tree Traversal
Social Media Feed
Custom Collection Framework
One-line Interview Definition

Memorize this:

Iterator pattern provides a way to access collection elements sequentially without exposing the internal representation of the collection.

*/

