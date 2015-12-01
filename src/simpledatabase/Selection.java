package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;
	private String childtable;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();
		
	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		childtable = ((Table)child).getFrom();
		Tuple a = child.next();
		if(childtable.equals(whereTablePredicate)){
			while(a!=null){			
				ArrayList<Attribute> attrib = a.getAttributeList();	
				for(int i=0; i<attrib.size(); i++){
					if(attrib.get(i).getAttributeName().equals(whereAttributePredicate)){
						if(attrib.get(i).getAttributeValue().equals(whereValuePredicate)){
							return a;
						}
					}
				}			
				a=child.next();
			}
		}
		return a;
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){	
		return(child.getAttributeList());
	}

	
}