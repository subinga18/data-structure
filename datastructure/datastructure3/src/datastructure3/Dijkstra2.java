package datastructure3;

//shortest distance

public class Dijkstra2 extends Destination
{
	String path = "";
	String cost = "";
	String total2="";
	static final int V = 8;
	static int route [] = new int[V];
	int matrix[][] = new int[][] {
		{ 0, 500, 100, 70, 0, 0, 0, 0 },
		{ 80, 0, 0, 100, 0, 40, 0, 0 },
		{ 230, 0, 0, 20, 50, 0, 0, 0 },
		{ 28, 36, 20, 0, 10, 60, 70, 0 },
		{ 0, 0, 550, 120, 0, 0, 7, 0 },
		{ 0, 500, 0, 25, 0, 0, 87, 30 },
		{ 0, 0, 0, 310, 10, 20, 0, 60 },
		{ 0, 0, 0, 0, 0, 150, 86, 0 } };
	
	int minCost(int dist[], Boolean sptSet[])
	{
		
		int min = Integer.MAX_VALUE;	//infinite
		int min_index = -1; 
		
		for (int v = 0; v < V; v++) 
			if (sptSet[v] == false && dist[v] <= min) 
			{ 
				min = dist[v]; 
				min_index = v; 
			} 

		return min_index; 
	}
	
	public void dijkstra(int graph[][], int src ,int end) 
	{ 
		//System.out.println("진입");

		int dist[] = new int[V]; // The output array. dist[i] will hold 
								// the shortest distance from src to i 
		int stop[] = new int [V];
		// sptSet[i] will true if vertex i is included in shortest 
		// path tree or shortest distance from src to i is finalized 
		Boolean sptSet[] = new Boolean[V]; 

		// Initialize all distances as INFINITE and stpSet[] as false 
		for (int i = 0; i < V; i++) 
		{ 
			dist[i] = Integer.MAX_VALUE; 
			sptSet[i] = false; 
		} 

		// Distance of source vertex from itself is always 0 
		dist[src] = 0; 

		// Find shortest path for all vertices 
		for (int count = 0; count < V-1; count++) 
		{ 
			// Pick the minimum distance vertex from the set of vertices 
			// not yet processed. u is always equal to src in first 
			// iteration. 
			int u = minCost(dist, sptSet); 

			// Mark the picked vertex as processed 
			sptSet[u] = true; 
			
			// Update dist value of the adjacent vertices of the 
			// picked vertex. 
			for (int v = 0; v < V; v++) {

				// Update dist[v] only if is not in sptSet, there is an 
				// edge from u to v, and total weight of path from src to 
				// v through u is smaller than current value of dist[v] 
				if (!sptSet[v] && graph[u][v]!=0 && 
						dist[u] != Integer.MAX_VALUE && 
						dist[u] + graph[u][v] < dist[v]) {
					dist[v] = dist[u] + graph[u][v];
					route[v]=u;
			
				}
			
			}
				
		}
		print(dist, src, end, sptSet, stop);
	}
	
	
	public void print(int dist[], int start, int end, Boolean[] sptSet,int stop[])
	{
		String[] nation = { "Vancouver", "Calgary", "Edmonton", "Ottawa", "Winnipeg", "Toronto", "Montreal", "Quebec"};
		

		System.out.println("Your point of departure : " + nation[start]);
		System.out.println("Destination : " + nation[end]);
		
		//경유해야하는 나라 출력........................
		int temp = route[end];
		int find[] = new int[V];
		int count = 1;
		
		find[0] = end;
		find[count] = temp;
		
		while(temp != start)
		{
			find[++count] = route[temp];
			temp = route[temp];
		}

		System.out.println("=========== it goes throw ==========");
		for (int j = count; j > -1 ; j--)
		{
			if(j==0)
			{
				System.out.print("(" + nation[find[j]] + ")");
				path = path + "(" + nation[find[j]] + ")";
			}
			else
			{
				System.out.print("(" + nation[find[j]] + ") -> ");
				path = path + "(" + nation[find[j]] + ") -> ";			
			}
		}
		total2=total2+("total transfer is:"+count);
		System.out.println();
		System.out.println("====================================");
		System.out.println("The shortest distance \nfrom " + nation[start] + " to " + nation[end] + " is \"" + dist[end] + "00km\"");
		cost = cost + "The shortest distance from " + nation[start] + " to " + nation[end] + " is \"" + dist[end] + "00km\"";
	}
}
