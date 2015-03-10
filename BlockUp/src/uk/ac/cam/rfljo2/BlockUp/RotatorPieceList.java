package uk.ac.cam.rfljo2.BlockUp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a list of pieces, the thing that makes it special
 * is that its iterator will dynamically rotate pieces as they are iterated through.
 * @author Tom Read-Cutting
 *
 */
public class RotatorPieceList extends LinkedList<Piece> {
	private int mRotationState = 0;
	
	public RotatorPieceList() {
		super();
	}
	
	public RotatorPieceList(Piece[] ps) {
		super(java.util.Arrays.asList(ps));
	}
	
	public void setRotationState(int r) { mRotationState = r; }
	
	
	@Override
	public Iterator<Piece> iterator() {
		final Iterator<Piece> old = super.iterator();
		Iterator<Piece> it = new Iterator<Piece>() {
			@Override
			public boolean hasNext() {
				return old.hasNext();
			}
	
			@Override
			public Piece next() {
			    Piece next = (Piece) old.next();
			    switch(mRotationState) {
			    	case 1:
			    		next = new Piece(-next.getY(), next.getX(), next.getType());
			    		break;
			    	case 2:
			    		next = new Piece(-next.getX(), -next.getY(), next.getType());
			    		break;
			    	case 3:
			    		next = new Piece(next.getY(), -next.getX(), next.getType());
			    		break;
			    }
			    return next;
			}
			
			@Override
			public void remove() {
			    old.remove();
			}
		};
		return it;
	}
}
