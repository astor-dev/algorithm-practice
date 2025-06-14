package datastructures.hash;

import java.util.List;

/**
 * 해시 테이블 ADT 인터페이스
 * @param <K> 키 타입
 * @param <V> 값 타입
 */
public interface HashTable<K, V> {
    /**
     * 키-값 쌍을 삽입
     * @param key 키
     * @param value 값
     * @return 이전 값, 없으면 null
     */
    V put(K key, V value);
    
    /**
     * 키에 해당하는 값을 반환
     * @param key 키
     * @return 해당 값, 없으면 null
     */
    V get(K key);
    
    /**
     * 키에 해당하는 키-값 쌍을 제거
     * @param key 키
     * @return 제거된 값, 없으면 null
     */
    V remove(K key);
    
    /**
     * 특정 키가 포함되어 있는지 확인
     * @param key 키
     * @return 포함되어 있으면 true
     */
    boolean containsKey(K key);
    
    /**
     * 특정 값이 포함되어 있는지 확인
     * @param value 값
     * @return 포함되어 있으면 true
     */
    boolean containsValue(V value);
    
    /**
     * 해시 테이블의 크기를 반환
     * @return 키-값 쌍의 개수
     */
    int size();
    
    /**
     * 해시 테이블이 비어있는지 확인
     * @return 비어있으면 true
     */
    boolean isEmpty();
    
    /**
     * 해시 테이블을 비움
     */
    void clear();
    
    /**
     * 모든 키들을 반환
     * @return 키들의 리스트
     */
    List<K> keys();
    
    /**
     * 모든 값들을 반환
     * @return 값들의 리스트
     */
    List<V> values();
    
    /**
     * 모든 키-값 쌍들을 반환
     * @return 키-값 쌍들의 리스트
     */
    List<Entry<K, V>> entrySet();
    
    /**
     * 해시 테이블의 용량을 반환
     * @return 내부 배열의 크기
     */
    int capacity();
    
    /**
     * 로드 팩터를 반환
     * @return size / capacity
     */
    double loadFactor();
    
    /**
     * 해시 함수 값을 반환
     * @param key 키
     * @return 해시 값
     */
    int hash(K key);
    
    /**
     * 해시 테이블을 재해싱
     */
    void rehash();
    
    /**
     * 키-값 쌍 내부 클래스
     * @param <K> 키 타입
     * @param <V> 값 타입
     */
    class Entry<K, V> {
        private K key;
        private V value;
        
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public K getKey() { return key; }
        public V getValue() { return value; }
        public void setValue(V value) { this.value = value; }
        
        @Override
        public String toString() {
            return key + "=" + value;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            
            Entry<?, ?> entry = (Entry<?, ?>) obj;
            return key != null ? key.equals(entry.key) : entry.key == null;
        }
        
        @Override
        public int hashCode() {
            return key != null ? key.hashCode() : 0;
        }
    }
} 