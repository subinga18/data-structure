package datastructure3;

import java.util.*;

public class Destination
{
	private int start;
	private int end;
	
	public void setStart(int start)
	{
		this.start = start;
	}
	
	public void setEnd(int end)
	{
		this.end = end;
	}
	
	public int getStart()
	{
		return start;
	}
	
	public int getEnd()
	{
		return end;
	}

	public void read(int start, int end) {
		// TODO Auto-generated method stub
		this.start = start;
		this.end = end;
	}
}
