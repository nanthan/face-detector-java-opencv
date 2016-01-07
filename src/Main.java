import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;


/**
 * Created by benvo_000 on 7/1/2559.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0);
        Thread.sleep(1000);
        camera.open(0);

        if(!camera.isOpened())
            System.out.println("Camera Error");
        else
            System.out.println("Camera now Open");

        Mat mat = new Mat();
        camera.read(mat);
        MyFrame jFrame = new MyFrame();

        CascadeClassifier faceDetector = new CascadeClassifier();
        faceDetector.load("C:/opencv/sources/data/haarcascades/haarcascade_frontalface_default.xml");

        while (true){
            camera.read(mat);
            if(mat != null){
                MatOfRect faceDetected = new MatOfRect();
                faceDetector.detectMultiScale(mat, faceDetected);
                for (Rect rect : faceDetected.toArray()){
                    Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x+rect.width, rect.y+rect.width), new Scalar(0, 255, 0));
                }
                jFrame.render(mat);
            }else{
                System.out.println("No Captured Frame");
                camera.release();
                break;
            }
        }
    }

}
