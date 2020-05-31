import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        Graph graph = new Graph(N);
        
        for(int i=0; i<road.length; i++){
            graph.addEdge(road[i][0],road[i][1],road[i][2]);
        }

        return graph.startDFS(K);
    }
}

class Graph {
    int vertex;
    ArrayList<Edge>[] adjacent;
    int total;
    ArrayList<Integer> checked;
    
    Graph(int v){
        this.vertex = v+1;
        adjacent = new ArrayList[this.vertex];
        for(int i = 0; i<adjacent.length; i++)
            adjacent[i] = new ArrayList<Edge>();
    }
    
    public void addEdge(int v,int w,int weight){
        adjacent[v].add(new Edge(w, weight));
        adjacent[w].add(new Edge(v , weight));
    }
    
    public int startDFS(int max){
        total = 0;
        boolean[] isVisited = new boolean[vertex+1];
        checked=new ArrayList<>();
        goDFS(1,0,max,isVisited);
        return this.total;
    }
    
    public void goDFS(int vertex,int currentNum,int max,boolean[] isVisited){
        if(currentNum>max) return;
        if(currentNum<=max) {
            if(!checked.contains(vertex)){
                total++;
                checked.add(vertex);
            }
        }
        isVisited[vertex] = true;
        
        for(Edge edge : adjacent[vertex]){
            if(!isVisited[edge.vertex]){
                boolean[] newVisited = isVisited.clone();
                goDFS(edge.vertex,currentNum+edge.weight,max,newVisited);
            }
        }
    }
    
}

class Edge{
    int vertex;
    int weight;
    
    Edge(int vertex,int weight){
        this.vertex = vertex;
        this.weight = weight;
    }
}