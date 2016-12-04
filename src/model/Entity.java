package model;

import holder.IRenderable;

public abstract class Entity implements IRenderable{
	protected int x,y,z;
	
	public Entity(int x,int y){
		this.x = x;
		this.y = y;
		this.z = 1;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
}
