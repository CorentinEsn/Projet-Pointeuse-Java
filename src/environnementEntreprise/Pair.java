package environnementEntreprise;

import java.io.Serializable;

public class Pair<L,R> implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private L l;
    private R r;
    
    public Pair(L l, R r){
        this.l = l;
        this.r = r;
    }
    
    public L getL(){ return l; }
    public R getR(){ return r; }
    public void setL(L l){ this.l = l; }
    public void setR(R r){ this.r = r; }
}


class PairTab<L,R> {
	
	private Pair<L,R> tabPair[];
	private int size;
	
	/**
	 * create an empty array
	 */
	public PairTab(){
		size = 0;
	}
	
	/**
	 * Create a array of pair with 1 entry
	 * @param l the value of the left entry
	 * @param r the value of the right entry
	 */
	public PairTab(L l, R r){
        tabPair[0].setL(l);
        tabPair[0].setR(r);
        size++;
    }
	
	/**
	 * add an entry to the tab. the tab can have duplicates so be careful
	 * @param l the value of the left entry
	 * @param r the value of the right entry
	 */
	public void add(L l,R r) {
		tabPair[size].setL(l);
		tabPair[size].setR(r);
		size++;
	}
	
	/**
	 * remove an entry of the tab. the entry has to match with the 2 params
	 * only 1 entry will be removed, even if there are duplicates
	 * @param l the value of the left entry
	 * @param r the value of the right entry
	 */
	public void remove(L l, R r) {
		boolean skip=false;
		
		for(int i=0; i<size; i++) {
			
			if(skip) {
        		tabPair[i-1] = tabPair[i];
        	}
			
			if((tabPair[i].getL() == l) && (tabPair[i].getR() == r)) {
            	skip = true;
            }
		}
		
	}
	
}