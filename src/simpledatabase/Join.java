package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		Tuple lTuple = leftChild.next();		
		while(lTuple !=null){
			ArrayList<Attribute> attriarrA = new ArrayList<Attribute>();
			ArrayList<Attribute> attriarrB = lTuple.getAttributeList();
			for(int i =0; i<attriarrB.size();i++){
				Attribute arriA = new Attribute();
				arriA.attributeName = attriarrB.get(i).getAttributeName();
				arriA.attributeType = attriarrB.get(i).getAttributeType();
				arriA.attributeValue = attriarrB.get(i).getAttributeValue();
				attriarrA.add(arriA);
			}
			tuples1.add(new Tuple(attriarrA));
			lTuple = leftChild.next();
		}
		
		int index1 = -1;
		int index2 = -2;
		Tuple rTuple = rightChild.next();
		if(rTuple == null) return null;
		ArrayList<Attribute> arrtiarrC = rTuple.getAttributeList();
		for(int j =0; j<arrtiarrC.size();j++){
			index1++;
			ArrayList<Attribute> attriarrD = tuples1.get(0).getAttributeList();
			for(int k =0; k<attriarrD.size();k++){
				if(attriarrD.get(k).getAttributeName().equals(arrtiarrC.get(j).getAttributeName())) break;
				index2++;
			}
			break;
		}
		ArrayList<Tuple> tuplearrA = tuples1;
		for(int l=0; l <tuplearrA.size();l++ ){
			if(tuplearrA.get(l).getAttributeName(index1).equals(rTuple.getAttributeName(index2))){
				if(tuplearrA.get(l).getAttributeValue(index1).equals(rTuple.getAttributeValue(index2))){
					ArrayList<Attribute> attriarrE = new ArrayList<Attribute>();			
					ArrayList<Attribute> attriarrF = tuplearrA.get(l).getAttributeList();
					for(int m = 0; m < attriarrF.size(); m++){
						Attribute attriB = new Attribute();
						attriB.attributeName = attriarrF.get(m).getAttributeName();
						attriB.attributeType = attriarrF.get(m).getAttributeType();
						attriB.attributeValue = attriarrF.get(m).getAttributeValue();
						attriarrE.add(attriB);
					}
					ArrayList<Attribute> attriarrG = rTuple.getAttributeList();
					for(int n =0; n<attriarrG.size();n++){
						Attribute attriC = new Attribute();
						attriC.attributeName = attriarrG.get(n).getAttributeName();
						attriC.attributeType = attriarrG.get(n).getAttributeType();
						attriC.attributeValue = attriarrG.get(n).getAttributeValue();
						if(!attriC.getAttributeName().equals(rTuple.getAttributeName(index2))) attriarrE.add(attriC);
					}
					tuples1.remove(tuplearrA);
					return new Tuple(attriarrE);
				}
				
			}
		}
		return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}