import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Created by jennyguo on 5/1/17.
 */
public class Graph {
  String src;
  String des;
  int cityNum;
  int edgeNum;
  String[] cities;
  String[] M_i;
  Map<String, Integer> map;
  Map<String, Integer> edges;

  public Graph(String f) {
    File file = new File(f);
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(file));
      this.cityNum = Integer.parseInt(reader.readLine());
      this.edgeNum = Integer.parseInt(reader.readLine());

      this.cities = reader.readLine().split(" ");
      this.M_i = reader.readLine().split(" ");

      this.src = cities[0];
      this.des = cities[cities.length - 1];

      map = new HashMap<>();
      for (int i = 0; i < cityNum; i++) {
        map.put(cities[i], Integer.parseInt(M_i[i]));
      }

      String text;
      edges = new HashMap<>();
      while ((text = reader.readLine()) != null) {
        String[] edge = text.split(" ");
        String twoCities = edge[0] + " " + edge[1];
        edges.put(twoCities, Integer.parseInt(edge[2]));
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
      }
    }
  }

  void BellmanFord(Graph graph) {
    int vertNum = graph.cityNum;
    int edgeNum = graph.edgeNum;
    Map<String, Integer> dist = new HashMap<>();

    // Step 1: Initialize distances from src to all other
    // vertices as INFINITE
    for (String s : graph.cities) {
      dist.put(s, Integer.MAX_VALUE);
    }
    dist.put(graph.src, 0);

    // Step 2: Relax all edges |V| - 1 times. A simple
    // shortest path from src to any other vertex can
    // have at-most |V| - 1 edges
    for (int i = 0; i < vertNum-1; i++) {
      for (String s : graph.edges.keySet()) {
        String u = s.split(" ")[0];
        String v = s.split(" ")[1];
        int weight = graph.edges.get(s);
        if (dist.get(u) != Integer.MAX_VALUE && dist.get(u) + weight + graph.map.get(u) < dist.get(v)) {
          dist.put(v, dist.get(u) + weight);
        }
      }
    }

    // Step 3: check for negative-weight cycles. The above
    // step guarantees shortest distances if graph doesn't
    // contain negative weight cycle. If we get a shorter
    //  path, then there is a cycle.
    for (String s : graph.edges.keySet()) {
      String u = s.split(" ")[0];
      String v = s.split(" ")[1];
      int weight = graph.edges.get(s);
      if (dist.get(u) != Integer.MAX_VALUE && dist.get(u) + weight < dist.get(v))
        System.out.println("Graph contains negative weight cycle");
    }
    System.out.println(dist.get(graph.des));
  }

  void dijkstra(Graph graph) {
    int vertNum = graph.cityNum;
    int edgeNum = graph.edgeNum;
    Map<String, Integer> dist = new HashMap<>();
    // The output array.  dist[i] will hold the shortest
    // distance from src to i

    HashSet sptSet; // sptSet[i] will true if vertex i is included in shortest
    // path tree or shortest distance from src to i is finalized

    // Initialize all distances as INFINITE and stpSet[] as false
    for (String s : graph.cities) {
      dist.put(s, Integer.MAX_VALUE);
    }
    dist.put(graph.src, 0);

    // Find shortest path for all vertices
    for (int count = 0; count < graph.; count++)
    {
      // Pick the minimum distance vertex from the set of vertices not
      // yet processed. u is always equal to src in first iteration.
      int u = minDistance(dist, sptSet);

      // Mark the picked vertex as processed
      sptSet[u] = true;

      // Update dist value of the adjacent vertices of the picked vertex.
      for (int v = 0; v < vertNum; v++)

        // Update dist[v] only if is not in sptSet, there is an edge from
        // u to v, and total weight of path from src to  v through u is
        // smaller than current value of dist[v]
        if (!sptSet[v] && graph[u][v] && dist[u] != INT_MAX
                && dist[u]+graph[u][v] < dist[v])
          dist[v] = dist[u] + graph[u][v];
    }
  }

  public int minDistance(Graph graph, String minSet, HashSet sptSet) {
    for (String s : graph.edges.keySet()) {
      int minDis = Integer.MAX_VALUE;
      if (s.contains(minSet)) {
        String u = s.split(" ")[0];
        String v = s.split(" ")[1];
        if (u.equals(s)) {
        }
        int weight = graph.edges.get(s);
      }
    }
  }

  public static void main(String[] args) {
    Graph trail = new Graph("graph_input.txt");
    trail.BellmanFord(trail);
  }

}
