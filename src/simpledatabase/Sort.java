package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();		
	}
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple tupleA = child.next();
		int indexA=0;
		while(tupleA !=null){
			indexA = 0;
			ArrayList<Attribute> attriarrA = new ArrayList<Attribute>();			
			ArrayList<Attribute> attriarrB = tupleA.getAttributeList(); 
			for(int i=0; i<attriarrB.size(); i++){
				Attribute attriC = new Attribute();
				attriC.attributeName = attriarrB.get(i).getAttributeName();
				attriC.attributeType = attriarrB.get(i).getAttributeType();
				attriC.attributeValue = attriarrB.get(i).getAttributeValue();
				attriarrA.add(attriC);
			}
			for(int j=0; j<attriarrB.size(); j++){ 
				if(attriarrB.get(j).getAttributeName().equals(orderPredicate)) break;
				else indexA++;
			}
			tuplesResult.add(new Tuple(attriarrA));
			ArrayList<Tuple> tuplearrA = new ArrayList<Tuple>();
			tuplearrA.add(tuplesResult.remove(0));			
			for(int k = 0; k < tuplesResult.size(); k++){
				int x = 0;
				for(int l = 0; l < tuplearrA.size(); l++){
					if(tuplesResult.get(k).getAttributeValue(indexA).toString().compareTo(tuplearrA.get(l).getAttributeValue(indexA).toString())>0){
						x++;
					}
				}
				tuplearrA.add(x, tuplesResult.get(k));
			}
			tuplesResult = tuplearrA;
			tupleA = child.next();
		}	
		if(tuplesResult.isEmpty()) return null;
		else return tuplesResult.remove(0);		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}