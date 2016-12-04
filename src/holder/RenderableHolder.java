package holder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {
	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public final static RenderableHolder instance = new RenderableHolder();
	
	//Constructor
	public RenderableHolder(){
		this.entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())return 1;
			else if(o1.getZ() < o2.getZ())return -1;
			else return 0;
		};
	}
	
	static {
		loadResource();
	}
	
	//load image
	private static void loadResource(){
		
	}
	
	//sort method
	public void sort(){
		Collections.sort(entities,comparator);
	}
	//add and sort
	public void add(IRenderable entity){
		entities.add(entity);
		sort();
	}
	
	//remove
	public void remove(int index){
		entities.remove(index);
	}
	public void removeAll(){
		entities.clear();
	}
	
	
	//getter
	public synchronized RenderableHolder getInstance(){
		return instance;
	}
	public synchronized List<IRenderable> getEntities(){
		return entities;
	}
}
