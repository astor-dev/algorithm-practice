package datastructures.linear;


import datastructures.common.DataStructure;

/**
 * 리스트 ADT 인터페이스
 * @param <T> 데이터 타입
 */
public interface List<T> extends DataStructure<T> {
    /**
     * 지정된 위치에 요소를 추가
     * @param index 추가할 위치
     * @param element 추가할 요소
     * @throws IndexOutOfBoundsException 인덱스가 범위를 벗어난 경우
     */
    void add(int index, T element);
    
    /**
     * 리스트 끝에 요소를 추가
     * @param element 추가할 요소
     * @return 추가 성공 시 true
     */
    boolean add(T element);
    
    /**
     * 지정된 위치의 요소를 제거
     * @param index 제거할 위치
     * @return 제거된 요소
     * @throws IndexOutOfBoundsException 인덱스가 범위를 벗어난 경우
     */
    T remove(int index);
    
    /**
     * 특정 요소를 제거
     * @param element 제거할 요소
     * @return 제거 성공 시 true
     */
    boolean remove(T element);
    
    /**
     * 지정된 위치의 요소를 반환
     * @param index 조회할 위치
     * @return 해당 위치의 요소
     * @throws IndexOutOfBoundsException 인덱스가 범위를 벗어난 경우
     */
    T get(int index);
    
    /**
     * 지정된 위치의 요소를 변경
     * @param index 변경할 위치
     * @param element 새로운 요소
     * @return 이전 요소
     * @throws IndexOutOfBoundsException 인덱스가 범위를 벗어난 경우
     */
    T set(int index, T element);
    
    /**
     * 특정 요소의 첫 번째 인덱스를 반환
     * @param element 찾을 요소
     * @return 요소의 인덱스, 없으면 -1
     */
    int indexOf(T element);
    
    /**
     * 특정 요소의 마지막 인덱스를 반환
     * @param element 찾을 요소
     * @return 요소의 인덱스, 없으면 -1
     */
    int lastIndexOf(T element);
} 