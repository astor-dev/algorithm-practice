import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static int indexCounter = 0;
    public static class Photo implements Comparable<Photo> {
        int recommendationCount;
        int createdIndex;
        int studentNumber;

        Photo(int studentNumber){
            this.recommendationCount = 1;
            this.createdIndex = indexCounter++;
            this.studentNumber = studentNumber;
        }

        public void recommend(){
            this.recommendationCount++;
        }

        @Override
        public int compareTo(Photo photo) {
            if(this.recommendationCount == photo.recommendationCount) return this.createdIndex - photo.createdIndex;
            return this.recommendationCount - photo.recommendationCount;
        }

    //        @Override
    //        public String toString(){
    //            return "[" + this.studentNumber + "]" + recommendationCount + " / " + createdIndex;
    //        }
    }

    public static void main(String[] args) throws Exception {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine()), k = Integer.parseInt(br.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

            Queue<Photo> gallery = new PriorityQueue<>(n);
            Photo[] galleryRef = new Photo[101];
            boolean[] isInGallery = new boolean[101];

            while (stringTokenizer.hasMoreTokens()) {
                int recommendationNumber = Integer.parseInt(stringTokenizer.nextToken());
                if (!isInGallery[recommendationNumber]) {
                    if(gallery.size() == n) {
                        Photo toBeRemovedPhoto = gallery.poll();
                        isInGallery[toBeRemovedPhoto.studentNumber] = false;
                    }
                    Photo photo = new Photo(recommendationNumber);
                    gallery.add(photo);
                    galleryRef[recommendationNumber] = photo;
                    isInGallery[photo.studentNumber] = true;
                } else {
                    galleryRef[recommendationNumber].recommend();
                    gallery.remove(galleryRef[recommendationNumber]);
                    gallery.add(galleryRef[recommendationNumber]);
                }
//                System.out.println("현재 pq 상태: " + gallery.toString());

            }
            List<Photo> sortedPhotos = new ArrayList<>(gallery);
            sortedPhotos.sort(Comparator.comparingInt(photo -> photo.studentNumber));
            StringBuilder stringBuilder = new StringBuilder();
            for (Photo photo : sortedPhotos) {
                stringBuilder.append(photo.studentNumber).append(" ");
            }
            System.out.print(stringBuilder);

        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
}
