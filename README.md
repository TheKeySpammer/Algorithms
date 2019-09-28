# Algorithms
A collection of algorithms for java

## Usage
Install the project into your local maven repository by running the following root folder of project.
```shell
mvn install
```
Add it to your porject dependency by appending this to your pom file.
```xml
<dependency>
    <groupId>com.tks</groupId>
    <artifactId>Algorithm</artifactId>
</dependency>
```

## Library Structure
* com.tks (Main package)
    * general (contains Searching and Sorting algorithm)
    * ds (contains Data Structures)
    * util (contains Array utilities)


## Algorithms Available:
 
### Searching
* Linear Search
* Binary Search

### Sorting
* Selection Sort
* Bubble Sort
* Merge Sort
* Quick Sort
* Heap Sort

### Data Structures
* Binary Heap (Priority Queue)
    * Creation of pq
    * Insertion in pq
    * Deletion from pq
    * Replacement in pq
* Graph
    * Creation of graph (from file or from user)
    * Purifying graph (Removing parallel and loop edges)
    * Depth First Search in graph
    * Kruskal's Minimum spanning tree
    * Prim's Minimum spanning tree
* UnionFind:
    * Provides disjoint set data structure and allows Union and find operations on them.
    * Used in cycle detection
 
      
 

