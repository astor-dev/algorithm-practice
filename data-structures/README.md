# 자바 자료구조 패키지 (Java Data Structures Package)

## 📋 개요

이 패키지는 자바 기반의 포괄적인 자료구조 라이브러리입니다. 상속과 다형성을 활용하여 확장 가능한 구조로 설계되었습니다.

## 📁 패키지 구조

```
datastructures/
├── common/                 # 공통 클래스 및 인터페이스
│   ├── DataStructure.java  # 기본 자료구조 인터페이스
│   ├── Node.java          # 기본 노드 클래스
│   └── TreeNode.java      # 트리 노드 클래스
│
├── linear/                 # 선형 자료구조
│   ├── List.java          # 리스트 ADT
│   ├── ArrayList.java     # 배열 기반 리스트
│   ├── LinkedList.java    # 연결 리스트
│   ├── Stack.java         # 스택 ADT
│   ├── ArrayStack.java    # 배열 기반 스택
│   ├── LinkedStack.java   # 연결 리스트 기반 스택
│   ├── Queue.java         # 큐 ADT
│   ├── ArrayQueue.java    # 배열 기반 큐
│   └── LinkedQueue.java   # 연결 리스트 기반 큐
│
├── tree/                   # 트리 자료구조
│   ├── Tree.java          # 트리 ADT
│   └── binary/            # 이진 트리
│       ├── BinaryTree.java        # 이진 트리 ADT
│       └── BinarySearchTree.java  # 이진 탐색 트리 ADT
│   └── balanced/          # 균형 트리
│       ├── RedBlackTreeNode.java  # 레드-블랙 트리 노드
│       └── RedBlackTree.java      # 레드-블랙 트리 ADT
│
├── graph/                  # 그래프 자료구조
│   └── Graph.java         # 그래프 ADT
│
└── hash/                   # 해시 자료구조
    └── HashTable.java     # 해시 테이블 ADT
```

## 🏗️ 아키텍처 설계

### 1. 상속 계층 구조

```
DataStructure<T> (인터페이스)
├── List<T> extends DataStructure<T>
├── Stack<T> extends DataStructure<T>
├── Queue<T> extends DataStructure<T>
├── Tree<T> extends DataStructure<T>
│   └── BinaryTree<T> extends Tree<T>
│       └── BinarySearchTree<T> extends BinaryTree<T>
│           └── RedBlackTree<T> extends BinarySearchTree<T>
└── Graph<V,E> extends DataStructure<V>
```

### 2. 노드 클래스 계층

```
Node<T> (기본 노드)
TreeNode<T> (트리 노드)
└── RedBlackTreeNode<T> extends TreeNode<T>
```

## 🔧 주요 특징

### 1. **확장성**

- 모든 ADT는 인터페이스로 정의되어 다양한 구현체 지원
- 상속을 통한 기능 확장 용이

### 2. **다형성**

- 공통 인터페이스를 통한 일관된 API 제공
- 런타임에 구현체 변경 가능

### 3. **재사용성**

- 공통 노드 클래스 활용
- 템플릿 메서드 패턴 적용

### 4. **타입 안전성**

- 제네릭을 활용한 타입 안전성 보장
- 컴파일 타임 타입 체크

## 📚 자료구조별 상세 설명

### 🔗 선형 자료구조 (Linear Data Structures)

#### List 인터페이스

```java
public interface List<T> extends DataStructure<T> {
    void add(int index, T element);
    boolean add(T element);
    T remove(int index);
    T get(int index);
    // ... 기타 메서드
}
```

**구현체:**

- `ArrayList<T>`: 동적 배열 기반 구현
- `LinkedList<T>`: 연결 리스트 기반 구현

#### Stack 인터페이스

```java
public interface Stack<T> extends DataStructure<T> {
    void push(T element);
    T pop();
    T peek();
    int search(T element);
}
```

**구현체:**

- `ArrayStack<T>`: 배열 기반 스택
- `LinkedStack<T>`: 연결 리스트 기반 스택

#### Queue 인터페이스

```java
public interface Queue<T> extends DataStructure<T> {
    boolean enqueue(T element);
    T dequeue();
    T front();
    T rear();
}
```

**구현체:**

- `ArrayQueue<T>`: 원형 배열 기반 큐
- `LinkedQueue<T>`: 연결 리스트 기반 큐

### 🌳 트리 자료구조 (Tree Data Structures)

#### Tree 인터페이스

```java
public interface Tree<T> extends DataStructure<T> {
    TreeNode<T> getRoot();
    boolean addChild(TreeNode<T> parent, TreeNode<T> child);
    List<T> preOrderTraversal();
    List<T> inOrderTraversal();
    List<T> postOrderTraversal();
    List<T> levelOrderTraversal();
    // ... 기타 메서드
}
```

#### BinaryTree 인터페이스

```java
public interface BinaryTree<T> extends Tree<T> {
    boolean addLeftChild(TreeNode<T> parent, TreeNode<T> leftChild);
    boolean addRightChild(TreeNode<T> parent, TreeNode<T> rightChild);
    boolean isComplete();
    boolean isFull();
    boolean isBalanced();
    // ... 기타 메서드
}
```

#### BinarySearchTree 인터페이스

```java
public interface BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    boolean insert(T value);
    boolean delete(T value);
    TreeNode<T> search(T value);
    TreeNode<T> findMin();
    TreeNode<T> findMax();
    // ... 기타 메서드
}
```

#### RedBlackTree 인터페이스

```java
public interface RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    void fixAfterInsertion(RedBlackTreeNode<T> node);
    void fixAfterDeletion(RedBlackTreeNode<T> node);
    void rotateLeft(RedBlackTreeNode<T> node);
    void rotateRight(RedBlackTreeNode<T> node);
    boolean isValidRedBlackTree();
    // ... 기타 메서드
}
```

### 📊 그래프 자료구조 (Graph Data Structures)

#### Graph 인터페이스

```java
public interface Graph<V, E> extends DataStructure<V> {
    boolean addVertex(V vertex);
    boolean addEdge(V from, V to, E weight);
    List<V> getNeighbors(V vertex);
    List<V> depthFirstSearch(V startVertex);
    List<V> breadthFirstSearch(V startVertex);
    boolean isDirected();
    boolean isWeighted();
    // ... 기타 메서드
}
```

### 🔗 해시 자료구조 (Hash Data Structures)

#### HashTable 인터페이스

```java
public interface HashTable<K, V> {
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean containsKey(K key);
    double loadFactor();
    void rehash();
    // ... 기타 메서드
}
```

## 🚀 사용 예시

### 리스트 사용 예시

```java
// 다형성을 활용한 사용
List<Integer> arrayList = new ArrayList<>();
List<Integer> linkedList = new LinkedList<>();

// 공통 인터페이스 사용
arrayList.add(1);
arrayList.add(2);
System.out.println(arrayList.get(0)); // 1
```

### 스택 사용 예시

```java
Stack<String> stack = new ArrayStack<>();
stack.push("첫 번째");
stack.push("두 번째");
System.out.println(stack.pop()); // "두 번째"
```

### 트리 사용 예시

```java
BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
bst.insert(5);
bst.insert(3);
bst.insert(7);
List<Integer> inOrder = bst.inOrderTraversal(); // [3, 5, 7]
```

## 🔧 구현 가이드라인

### 1. 새로운 구현체 추가

기존 ADT 인터페이스를 구현하여 새로운 구현체를 쉽게 추가할 수 있습니다.

```java
public class MyCustomList<T> implements List<T> {
    // 인터페이스 메서드 구현
    @Override
    public void add(int index, T element) {
        // 사용자 정의 구현
    }
    // ... 기타 메서드 구현
}
```

### 2. 기존 자료구조 확장

상속을 통해 기존 자료구조를 확장할 수 있습니다.

```java
public interface AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    int getBalance(TreeNode<T> node);
    void rebalance(TreeNode<T> node);
}
```

## 🧪 테스트 방법

각 자료구조에 대한 단위 테스트를 작성하여 기능을 검증할 수 있습니다:

```java
@Test
public void testArrayListBasicOperations() {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    assertEquals(2, list.size());
    assertEquals(Integer.valueOf(1), list.get(0));
}
```

## 🤝 기여 방법

1. 새로운 자료구조 추가 시 해당 ADT 인터페이스를 먼저 정의
2. 기존 인터페이스를 확장하여 특화된 기능 추가
3. 각 구현체에 대한 적절한 테스트 케이스 작성
4. 문서화 및 예시 코드 제공

## 📝 라이선스

이 프로젝트는 교육 목적으로 제작되었습니다.

---

## 📞 참고 자료

- [Frontend Fundamentals - Code Quality](https://frontend-fundamentals.com/code-quality/)
- 자료구조 및 알고리즘 이론
- 자바 제네릭 및 상속 개념
