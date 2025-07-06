public class Box {
	@SuppressWarnings("rawtypes")
	private Comparable item;
	boolean full;
	
	public Box()	{
		full=false;
	}
	
	public void store(@SuppressWarnings("rawtypes") Comparable a){
		this.item = a;
		full=true;
	}
   
   @SuppressWarnings("rawtypes")
public Comparable retrieve()   {
      return item;
   }
	
	public void remove()	{
		item = null;
		full=false;
	}
	
	public String toString()	{
		if (full)
			return item.toString();
		else
			return "nothing";
	}
}
