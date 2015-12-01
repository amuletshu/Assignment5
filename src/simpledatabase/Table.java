package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;
	private String attributeLine, dataTypeLine, tupleLine;
	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}


	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		
		if(getAttribute == false){
			try{
				attributeLine = br.readLine();
				dataTypeLine = br.readLine();
				tupleLine = br.readLine();
				tuple = new Tuple(attributeLine, dataTypeLine, tupleLine);
				tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
				getAttribute = true;
			}catch(Exception e){		
				e.printStackTrace();
			}
		}else if(getAttribute == true){
			try{
				tupleLine = br.readLine();
				if(tupleLine != null){
					tuple = new Tuple(attributeLine, dataTypeLine, tupleLine);
					tuple.setAttributeName();
					tuple.setAttributeType();
					tuple.setAttributeValue();
				}else{
					return null;
				}
			}catch(Exception e){		
				e.printStackTrace();
			}		
		}
		return tuple;
	}
	
	public String getFrom(){
		return from;
	}

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}