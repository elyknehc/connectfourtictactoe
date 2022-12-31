/**
 * Node class used to represent a node
 * Stores a record
 * @author kylechen
 *
 * @param <E>
 */
public class Node<E>
{
	//Element and next instance variables
    private Node<E> next;
    private E element;

    /**
     * Constructor to make a null node
     */
    public Node()
    {
        next = null;
        element = null;
    }
    /**
     * Constructor for node with an object or element
     */
    public Node (E elem)
    {
        next = null;
        element = elem;
    }
    
    //Returns next node
    public Node<E> getNext()
    {
        return next;
    }
    
    //Sets the next node

    public void setNext (Node<E> node)
    {
        next = node;
    }
    //Returns element

    public E getElement()
    {
        return element;
    }
    
    //Sets element as something
    public void setElement (E elem)
    {
        element = elem;
    }
}

